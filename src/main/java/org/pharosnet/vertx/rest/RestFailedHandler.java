package org.pharosnet.vertx.rest;

import io.vertx.ext.web.RoutingContext;

public interface RestFailedHandler {

    public void handle(RoutingContext context, Throwable throwable);

}
