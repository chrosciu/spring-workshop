package eu.chrost.examples.refresh;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class App {
    public static void main(String[] args) {
        try (var applicationContext = new ClassPathXmlApplicationContext("first.xml")) {
            var firstXml = applicationContext.getBean(FirstXml.class);
            firstXml.foo();
            //applicationContext.setConfigLocation("second.xml");
            applicationContext.refresh();
            firstXml.foo();
            var anotherfirstXml = applicationContext.getBean(FirstXml.class);
            log.info("{}", firstXml == anotherfirstXml);
            //var secondXml = applicationContext.getBean(SecondXml.class);

        }
    }
}
