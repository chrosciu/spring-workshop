package eu.chrost.examples.circular;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        try (var applicationContext = new AnnotationConfigApplicationContext("eu.chrost.examples.circular")) {
            var first = applicationContext.getBean(First.class);
            var second = applicationContext.getBean(Second.class);
            var third = applicationContext.getBean(Third.class);
            var fourth = applicationContext.getBean(Fourth.class);
            first.foo();
            second.bar();
            third.foo();
            fourth.bar();
        }
    }
}
