package org.pharosnet.vertx.rest;

import io.vertx.core.Vertx;

public abstract class AbstractRestHandler {

    public AbstractRestHandler(Vertx vertx) {
        this.vertx = vertx;
    }

    private final Vertx vertx;

    protected Vertx vertx() {
        return this.vertx;
    }

}
