package org.pharosnet.vertx.rest.plugin;

import com.google.auto.service.AutoService;
import org.pharosnet.vertx.rest.annotations.RestHandler;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

@SupportedAnnotationTypes({"org.pharosnet.vertx.rest.annotations.RestHandler"})
@SupportedSourceVersion(SourceVersion.RELEASE_11)
@AutoService(Processor.class)
public class RestHandlerGenerateProcessor extends AbstractProcessor {

    private Messager messager;
    private Elements elementUtils;
    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.elementUtils = processingEnv.getElementUtils();
        this.filer = processingEnv.getFiler();
        this.messager = processingEnv.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(RestHandler.class);
        CountDownLatch countDownLatch = new CountDownLatch(elements.size());
        AtomicInteger count = new AtomicInteger(0);
        elements.stream()
                .map(e -> new RestHandlerGenerator((TypeElement) e, this.filer, this.elementUtils, this.messager))
                .forEach(generator -> {
                    if (generator.generate()) {
                        count.incrementAndGet();
                    }
                    countDownLatch.countDown();
                });
        try {
            countDownLatch.await();
        } catch (InterruptedException exception) {
            this.messager.printMessage(Diagnostic.Kind.ERROR, "创建 VERTX REST HANDLER 失败, " + exception.getMessage());
        }
        if (count.get() != elements.size()) {
            return false;
        }
        // todo generate rest router builder
        return true;
    }
}
