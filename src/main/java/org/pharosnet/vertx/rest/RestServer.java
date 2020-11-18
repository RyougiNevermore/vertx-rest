package org.pharosnet.vertx.rest;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.net.JdkSSLEngineOptions;
import io.vertx.core.net.JksOptions;
import io.vertx.core.net.OpenSSLEngineOptions;
import io.vertx.core.net.SSLEngineOptions;
import lombok.extern.slf4j.Slf4j;
import org.pharosnet.vertx.rest.commons.ByteUtils;

import java.util.List;
import java.util.Optional;

@Slf4j
public class RestServer {

    public RestServer(Vertx vertx, HttpConfig config) {
        this.vertx = vertx;
        this.config = config;
    }

    private final Vertx vertx;
    private final HttpConfig config;

    private HttpServer httpServer;

    public Future<Void> run(AbstractRestRouterBuilder routerBuilder) {
        Promise<Void> promise = Promise.promise();

        if (routerBuilder == null) {
            promise.fail(new IllegalArgumentException("rest http run failed, routerBuilder is null"));
            return promise.future();
        }

        HttpServerOptions options = new HttpServerOptions();
        if (vertx.isNativeTransportEnabled()) {
            options.setTcpFastOpen(true)
                    .setTcpCork(true)
                    .setTcpQuickAck(true)
                    .setReusePort(true);
        }
        options.setPort(this.config.getPort());
        options.setHost(this.config.getHost());

        String defaultRequestBodyLimit = Optional.ofNullable(this.config.getRequestBodyLimit()).orElse("").trim();
        if (defaultRequestBodyLimit.length() > 0) {
            try {
                routerBuilder.setDefaultRequestBodyLimit(ByteUtils.parseByteSize(defaultRequestBodyLimit));
            } catch (Exception e) {
                promise.fail(e);
                return promise.future();
            }
        }

        options.setLogActivity(Optional.ofNullable(this.config.getEnableLogActivity()).orElse(false));
        options.setAcceptBacklog(Optional.ofNullable(this.config.getBacklog()).orElse(1024));
        if (this.config.getCompress() != null) {
            if (log.isDebugEnabled()) {
                log.debug("set compress options. noted!");
            }
            options.setCompressionSupported(Optional.ofNullable(this.config.getCompress().getCompression()).orElse(false));
            options.setDecompressionSupported(Optional.ofNullable(this.config.getCompress().getDecompression()).orElse(false));
        }
        if (this.config.getSsl() != null) {
            if (log.isDebugEnabled()) {
                log.debug("set ssl options. noted!");
            }
            HttpSSLConfig sslConfig = this.config.getSsl();
            String keystore = Optional.ofNullable(sslConfig.getKeystore()).orElse("").trim();
            if (keystore.length() == 0) {
                log.error("无法创建SSL, keystore 不能为空");
                promise.fail(new Exception("无法创建SSL, keystore 不能为空"));
                return promise.future();
            }
            String password = Optional.ofNullable(sslConfig.getPassword()).orElse("").trim();
            if (password.length() == 0) {
                log.error("无法创建SSL, password 不能为空");
                promise.fail(new Exception("无法创建SSL, password 不能为空"));
                return promise.future();
            }
            options.removeEnabledSecureTransportProtocol("TLSv1");
            options.addEnabledSecureTransportProtocol("TLSv1.3");
            options.setSsl(true);
            SSLEngineOptions sslEngineOptions;
            if (OpenSSLEngineOptions.isAvailable()) {
                sslEngineOptions = new OpenSSLEngineOptions();
            } else {
                sslEngineOptions = new JdkSSLEngineOptions();
            }
            options.setSslEngineOptions(sslEngineOptions);
            JksOptions jksOptions = new JksOptions().setPath(keystore).setPassword(password);
            if (Optional.ofNullable(sslConfig.getTrust()).orElse(false)) {
                options.setTrustStoreOptions(jksOptions);
            } else {
                options.setKeyStoreOptions(jksOptions);
            }

            if (Optional.ofNullable(sslConfig.getHttp2()).orElse(false)) {
                options.setTrustStoreOptions(jksOptions);
                if (Optional.ofNullable(sslConfig.getHttp2UseAlpn()).orElse(false)) {
                    options.setUseAlpn(true);
                    options.setAlpnVersions(List.of(HttpVersion.HTTP_2, HttpVersion.HTTP_1_1));
                }
                int http2WindowSize = Optional.ofNullable(sslConfig.getHttp2WindowSize()).orElse(-1);
                if (http2WindowSize > 0) {
                    options.setHttp2ConnectionWindowSize(http2WindowSize);
                }
            }
        }

        vertx.createHttpServer(options).requestHandler(routerBuilder.build()).listen(r -> {
            if (r.failed()) {
                log.error("无法启动 HTTP 服务,", r.cause());
                promise.fail(r.cause());
                return;
            }
            if (log.isDebugEnabled()) {
                log.debug("启动 HTTP 服务 成, {}:{}", options.getHost(), options.getPort());
            }
            this.httpServer = r.result();
            promise.complete();
        });

        return promise.future();
    }

    public Future<Void> close() {
        Promise<Void> promise = Promise.promise();
        this.httpServer.close(promise);
        return promise.future();
    }

}
