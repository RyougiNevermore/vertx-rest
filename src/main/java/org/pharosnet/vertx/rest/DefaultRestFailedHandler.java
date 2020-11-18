package org.pharosnet.vertx.rest;

import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.impl.HttpStatusException;

public class DefaultRestFailedHandler implements RestFailedHandler {

    @Override
    public void handle(RoutingContext context, Throwable throwable) {
        context.fail(new HttpStatusException(500, throwable));
    }

}
