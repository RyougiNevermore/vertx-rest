package org.pharosnet.vertx.rest.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface OpenAPI {

    // swagger ui url
    String path();

    String title();

    String description();

    String version();

    OpenAPIContact contact();

    OpenAPITag[] tags() default {};

    // todo global response

}
