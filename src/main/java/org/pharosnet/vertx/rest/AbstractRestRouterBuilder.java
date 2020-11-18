package org.pharosnet.vertx.rest;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.pharosnet.vertx.rest.commons.ByteUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractRestRouterBuilder {

    public AbstractRestRouterBuilder(Vertx vertx) {
        this.vertx = vertx;
        this.defaultRequestBodyLimit = ByteUtils.parseByteSize("4mb");
    }

    private final Vertx vertx;
    private long defaultRequestBodyLimit;
    private List<Handler<RoutingContext>> globalMiddlewares;
    private List<RestMiddleware> middlewares;
    private RestSucceedHandler succeedHandler;
    private RestFailedHandler failedHandler;
    private String openApiPath;

    public abstract Router build();

    public Vertx getVertx() {
        return vertx;
    }

    public String getOpenApiPath() {
        this.openApiPath = Optional.ofNullable(this.openApiPath).orElse("").trim();
        if (this.openApiPath.length() == 0) {
            this.openApiPath = "/openapi/ui.html";
        }
        return openApiPath;
    }

    public void setOpenApiPath(String openApiPath) {
        this.openApiPath = openApiPath;
    }

    protected long getDefaultRequestBodyLimit() {
        return defaultRequestBodyLimit;
    }

    protected void setDefaultRequestBodyLimit(long defaultRequestBodyLimit) {
        this.defaultRequestBodyLimit = defaultRequestBodyLimit;
    }

    public AbstractRestRouterBuilder appendGlobalMiddlewares(Handler<RoutingContext> middleware) {
        if (middleware == null) {
            return this;
        }
        if (this.globalMiddlewares == null) {
            this.globalMiddlewares = new ArrayList<>();
        }
        this.globalMiddlewares.add(middleware);
        return this;
    }

    public List<Handler<RoutingContext>> getGlobalMiddlewares() {
        return globalMiddlewares;
    }

    public AbstractRestRouterBuilder appendMiddlewares(RestMiddleware middleware) {
        if (middleware == null) {
            return this;
        }
        if (this.middlewares == null) {
            this.middlewares = new ArrayList<>();
        }
        this.middlewares.add(middleware);
        return this;
    }

    public List<RestMiddleware> getMiddlewares() {
        return middlewares;
    }

    public RestSucceedHandler getSucceedHandler() {
        if (this.succeedHandler == null) {
            this.succeedHandler = new DefaultRestSucceedHandler();
        }
        return succeedHandler;
    }

    public void setSucceedHandler(RestSucceedHandler succeedHandler) {
        this.succeedHandler = succeedHandler;
    }

    public RestFailedHandler getFailedHandler() {
        if (this.failedHandler == null) {
            this.failedHandler = new DefaultRestFailedHandler();
        }
        return failedHandler;
    }

    public void setFailedHandler(RestFailedHandler failedHandler) {
        this.failedHandler = failedHandler;
    }

}
