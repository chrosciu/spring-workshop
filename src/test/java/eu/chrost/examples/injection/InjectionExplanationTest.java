package eu.chrost.examples.injection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Explanation what happens when you inject prototype or request bean into singleton")
@SpringBootTest(classes = InjectionExampleConfiguration.class)
class InjectionExplanationTest {

    @Autowired
    private OtherSingletonServiceUsingPrototype otherSingletonService;
    @Autowired
    private SingletonServiceUsingPrototype singletonService;
    @Autowired
    private SingletonServiceUsingPrototypeWithObjectProvider singletonServiceUsingPrototypeWithObjectProvider;

    // @formatter:off
    @DisplayName(
        """
         given prototype bean and singleton bean,
         when prototype bean is injected into singleton,
         then singleton uses the same instance all the time
        """
    )
    // @formatter:on
    @Test
    void injectPrototypeTest0() {
        // when
        var idOfPrototypeBeanAfterFirstInvocation = singletonService.getIdOfPrototypeBean();
        var idOfPrototypeBeanAfterSecondInvocation = singletonService.getIdOfPrototypeBean();

        // then
        assertThat(idOfPrototypeBeanAfterFirstInvocation).isEqualTo(idOfPrototypeBeanAfterSecondInvocation);
    }


    // @formatter:off
    @DisplayName(
            """
             given prototype bean and singleton bean,
             when prototype bean is injected into singleton with object provider,
             then singleton gets different instance every time it requests it
            """
    )
    // @formatter:on
    @Test
    void injectPrototypeTest1() {
        // when
        var idOfPrototypeBeanAfterFirstInvocation = singletonServiceUsingPrototypeWithObjectProvider.getIdOfPrototypeBean();
        var idOfPrototypeBeanAfterSecondInvocation = singletonServiceUsingPrototypeWithObjectProvider.getIdOfPrototypeBean();

        // then
        assertThat(idOfPrototypeBeanAfterFirstInvocation).isNotEqualTo(idOfPrototypeBeanAfterSecondInvocation);
    }

    // @formatter:off
    @DisplayName(
        """
         given prototype bean and two singleton beans,
         when prototype bean is injected into both singletons,
         then each singleton uses its own unique instance
        """
    )
    // @formatter:on
    @Test
    void injectPrototypeTest2() {
        // when
        var idOfPrototypeBeanUsedByFirstSingletonService = singletonService.getIdOfPrototypeBean();
        var idOfPrototypeBeanUsedByOtherSingletonService = otherSingletonService.getIdOfPrototypeBean();

        // then
        assertThat(idOfPrototypeBeanUsedByFirstSingletonService).isNotEqualTo(
            idOfPrototypeBeanUsedByOtherSingletonService);
    }
}
