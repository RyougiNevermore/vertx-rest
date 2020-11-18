package org.pharosnet.vertx.rest;

import lombok.Data;

@Data
public class HttpSSLConfig {

    public HttpSSLConfig() {
    }

    private String keystore;
    private String password;

    private Boolean trust;

    private Boolean http2;
    private Boolean http2UseAlpn;
    private Integer http2WindowSize;

}
