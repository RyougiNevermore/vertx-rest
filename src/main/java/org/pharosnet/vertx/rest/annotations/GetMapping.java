package org.pharosnet.vertx.rest.annotations;

import org.pharosnet.vertx.rest.RequestMethod;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
@RequestMapping(method = RequestMethod.GET)
public @interface GetMapping {

    String[] path() default {};

    String[] consumes() default {};

    String[] produces() default {};

}