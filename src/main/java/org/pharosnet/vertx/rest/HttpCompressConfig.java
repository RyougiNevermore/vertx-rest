package org.pharosnet.vertx.rest;

import lombok.Data;

@Data
public class HttpCompressConfig {

    public HttpCompressConfig() {
    }

    private Boolean compression;
    private Boolean decompression;

}
