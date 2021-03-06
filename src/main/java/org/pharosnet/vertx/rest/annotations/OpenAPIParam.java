package org.pharosnet.vertx.rest.annotations;

import org.pharosnet.vertx.rest.RequestParamType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface OpenAPIParam {

    String name();

    String description();

    String defaultValue() default "";

    String allowableValues() default "";

    boolean required() default false;

    RequestParamType paramType() default RequestParamType.UNDEFINED;
}
