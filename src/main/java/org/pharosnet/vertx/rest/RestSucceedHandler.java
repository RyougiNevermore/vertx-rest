package org.pharosnet.vertx.rest;

import io.vertx.ext.web.RoutingContext;

public interface RestSucceedHandler {

    void handle(RoutingContext context, Object data);

}
