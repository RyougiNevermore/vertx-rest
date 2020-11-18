package org.pharosnet.vertx.rest.plugin;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

public class RestHandlerGenerator {

    public RestHandlerGenerator(TypeElement element, Filer filer, Elements elementUtils, Messager messager) {
        this.element = element;
        this.filer = filer;
        this.elementUtils = elementUtils;
        this.messager = messager;
    }

    private TypeElement element;
    private Filer filer;
    private Elements elementUtils;
    private Messager messager;


    public boolean generate() {

        return true;
    }

}
