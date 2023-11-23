package eu.chrost.examples.circular;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class Second {
    private final First first;
//    private First first;
//
//    @Autowired
//    public void setFirst(First first) {
//        this.first = first;
//    }

    public void foo() {
        log.info("foo");
    }

    public void bar() {
        first.bar();
    }
}
