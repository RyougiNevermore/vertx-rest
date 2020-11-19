package org.pharosnet.vertx.rest.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface OpenAPIHandler {

    String name();

    String description() default "";

    String[] tags() default "";

    String produces() default "";

    String consumes() default "";

}
