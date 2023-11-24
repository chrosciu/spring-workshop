package eu.chrost.examples.prototype;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        try (var applicationContext = new AnnotationConfigApplicationContext("eu.chrost.examples.prototype")) {
            var prototypeBean = applicationContext.getBean(PrototypeBean.class);
        }
    }
}
