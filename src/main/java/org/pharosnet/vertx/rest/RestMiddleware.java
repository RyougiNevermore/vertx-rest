package org.pharosnet.vertx.rest;

import io.vertx.ext.web.RoutingContext;

public interface RestMiddleware {

    public void handle(RoutingContext context);

}
