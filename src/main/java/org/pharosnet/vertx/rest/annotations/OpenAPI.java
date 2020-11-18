package org.pharosnet.vertx.rest.annotations;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface OpenAPI {

    String name();

    String description() default "";

    String[] tags() default "";

    String produces() default "";


    String consumes() default "";
}
