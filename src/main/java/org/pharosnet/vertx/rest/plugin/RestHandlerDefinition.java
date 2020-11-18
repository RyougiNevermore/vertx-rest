package org.pharosnet.vertx.rest.plugin;

import lombok.Data;

import java.util.List;

@Data
public class RestHandlerDefinition {

    private String path;
    private String tag;

    private List<RestHandleDefinition> handleDefinitions;

}
