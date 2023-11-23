package eu.chrost.examples.circular;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Slf4j
//@RequiredArgsConstructor
public class Third {
    private final Fourth fourth;

    public Third(@Lazy Fourth fourth) {
        this.fourth = fourth;
    }

    public void foo() {
        fourth.foo();
    }

    public void bar() {
        log.info("bar");
    }
}
