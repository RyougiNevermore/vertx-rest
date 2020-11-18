package org.pharosnet.vertx.rest;

import io.vertx.ext.web.RoutingContext;

public interface RestSucceedHandler {

    public void handle(RoutingContext context, Object data);

}
