package org.pharosnet.vertx.rest.plugin;

import lombok.Data;
import org.pharosnet.vertx.rest.RequestMethod;

import java.util.List;
import java.util.Map;

@Data
public class RestHandleDefinition {

    private String operatorId;
    private String description;

    private RequestMethod method;
    private String path;

    // request
    private List<RestRequestParamDefinition> paramDefinitions;
    private Class<?> requestBody;

    // response
    private Map<Integer, Class<?>> responseBodyMap;

}
