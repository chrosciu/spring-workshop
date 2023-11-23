package eu.chrost.examples.circular;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
//@RequiredArgsConstructor
public class First {
    //private final Second second;

    private Second second;

    @Autowired
    public void setSecond(Second second) {
        this.second = second;
    }

    public void foo() {
        second.foo();
    }

    public void bar() {
        log.info("bar");
    }
}
