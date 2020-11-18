package org.pharosnet.vertx.rest.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface PutMapping {

    String operatorId() default "";

    String description() default "";

    String path();

    String consumes() default "";

    String produces() default "";

}
