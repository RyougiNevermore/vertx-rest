package org.pharosnet.vertx.rest.openapi;

import lombok.Data;

@Data
public class Tag {

    private String name;
    private String description;
    private Integer order;

}
