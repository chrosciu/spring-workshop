package eu.chrost.shop.common.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class InMemoryUserDetailsService implements UserDetailsService {
    private static final List<UserData> USERS = List.of(
            new UserData("chrosciu", "Maja")
    );

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return USERS.stream()
                .filter(userData -> userData.getUsername().equals(username))
                .findFirst()
                .map(userData -> new User(userData.getUsername(), userData.getPassword(), List.of()))
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User with login %s does not exist", username)));
    }

    @Data
    @AllArgsConstructor
    private static class UserData {
        private String username;
        private String password;
    }
}
