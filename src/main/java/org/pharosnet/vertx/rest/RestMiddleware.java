package org.pharosnet.vertx.rest;

import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;

public abstract class RestMiddleware {

    public RestMiddleware(Vertx vertx) {
        this.vertx = vertx;
    }

    private final Vertx vertx;

    public abstract void handle(RoutingContext context);

    protected Vertx vertx() {
        return vertx;
    }

}
