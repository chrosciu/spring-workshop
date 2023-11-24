package eu.chrost.examples.registration;

import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        try (var applicationContext = new AnnotationConfigApplicationContext("eu.chrost.examples.registration")) {
            GenericBeanDefinition bd = new GenericBeanDefinition();
            bd.setBeanClass(SomeBean.class);
            applicationContext.registerBeanDefinition("someBean", bd);
            var someBean = applicationContext.getBean(SomeBean.class);
            someBean.foo();
        }
    }
}
