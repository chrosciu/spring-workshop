package eu.chrost.examples.users;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = "logging.level.org.springframework.test.context=DEBUG")
public class StatefulUserServiceTest {
    @Autowired
    private StatefulUserService userService;

    @AfterEach
    void cleanUp() {
        userService.cleanUp();
    }

    @Configuration
    static class UserServiceConfiguration {
        @Bean
        public StatefulUserService userService() {
            return new StatefulUserService();
        }
    }

    @Test
    void shouldAddMarcinUser() {
        //given
        var marcin = new User("Marcin");

        //when
        userService.addUser(marcin);

        //then
        assertThat(userService.getUsers()).hasSize(1);
    }

    @Test
    void shouldAddTomaszUser() {
        //given
        var tomasz = new User("Tomasz");

        //when
        userService.addUser(tomasz);

        //then
        assertThat(userService.getUsers()).hasSize(1);
    }
}
