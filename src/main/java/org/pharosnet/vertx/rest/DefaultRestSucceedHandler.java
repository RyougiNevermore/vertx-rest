package org.pharosnet.vertx.rest;

import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

public class DefaultRestSucceedHandler implements RestSucceedHandler {
    @Override
    public void handle(RoutingContext context, Object data) {
        if (data == null) {
            context.response()
                    .setChunked(true)
                    .setStatusCode(200)
                    .end();
            return;
        }
        context.response()
                .setChunked(true)
                .setStatusCode(200)
                .write(Json.encode(data), "UTF-8")
                .end();
    }
}
