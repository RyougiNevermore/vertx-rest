package org.pharosnet.vertx.rest.plugin;

import lombok.Data;
import org.pharosnet.vertx.rest.RequestParamType;

@Data
public class RestRequestParamDefinition {

    private String name;

    private Class<?> valueType;

    private Object defaultValue;

    private RequestParamType paramType;

    private Boolean required;

    private String description;


}
