package org.pharosnet.vertx.rest;

import io.vertx.ext.web.RoutingContext;

public interface RestFailedHandler {

    void handle(RoutingContext context, Throwable throwable);

}
