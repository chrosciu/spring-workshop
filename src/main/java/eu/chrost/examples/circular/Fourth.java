package eu.chrost.examples.circular;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class Fourth {
    private final Third third;

//    public Fourth(@Lazy Third third) {
//        this.third = third;
//    }

    public void foo() {
        log.info("foo");
    }

    public void bar() {
        third.bar();
    }
}
