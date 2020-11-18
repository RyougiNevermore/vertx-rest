package org.pharosnet.vertx.rest;

import lombok.Data;

@Data
public class HttpConfig {

    public HttpConfig() {
    }

    private String host;

    private Integer port;

    // 4MB
    private String requestBodyLimit;

    private Boolean enableLogActivity;

    private Integer backlog;

    private HttpCompressConfig compress;

    private HttpSSLConfig ssl;

}
