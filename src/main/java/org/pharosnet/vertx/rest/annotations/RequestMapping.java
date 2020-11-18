package org.pharosnet.vertx.rest.annotations;


import org.pharosnet.vertx.rest.RequestMethod;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
public @interface RequestMapping {

    String[] path() default {};

    RequestMethod[] method() default {};

    String[] consumes() default {};

    String[] produces() default {};

}
