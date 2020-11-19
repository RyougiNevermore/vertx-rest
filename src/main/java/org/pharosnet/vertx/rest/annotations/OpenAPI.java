package org.pharosnet.vertx.rest.annotations;

import org.pharosnet.vertx.rest.openapi.Tag;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public  @interface OpenAPI {



    Tag[] tags() default {};

}
