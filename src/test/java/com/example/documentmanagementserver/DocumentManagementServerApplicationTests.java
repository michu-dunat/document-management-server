package com.example.documentmanagementserver;

import com.example.documentmanagementserver.models.User;
import com.example.documentmanagementserver.repositories.UserRepository;
import com.example.documentmanagementserver.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class DocumentManagementServerApplicationTests {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void testGetUserWithNoPasswordIfUserIsPresent() {
        user.setPassword("Encoded password");
        when(userRepository.findById(anyInt())).thenReturn(java.util.Optional.of(user));
        assertThat(userService.getUserWithNoPassword(1).getPassword()).isEqualTo("");
    }

    @Test
    void testGetUserWithNoPasswordIfUserIsNotPresent() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThat(userService.getUserWithNoPassword(1)).isEqualTo(null);
    }

    @Test
    void testWasPasswordChangedIfPasswordNotEmpty() {
        user.setPassword("New password");
        assertThat(userService.wasPasswordChanged(user)).isTrue();
    }

    @Test
    void testWasPasswordChangedIfPasswordEmpty() {
        user.setPassword("");
        assertThat(userService.wasPasswordChanged(user)).isFalse();
    }

    @Test
    void testSetPasswordOfUserToBeUpdatedIfWasNotChanged() {
        when(passwordEncoder.encode(anyString())).thenReturn("Encoded Password");
        user.setPassword("");
        User userSavedInDatabase = new User();
        userSavedInDatabase.setPassword("Password previously encoded");
        userService.setPasswordOfUserToBeUpdated(user, Optional.of(userSavedInDatabase));
        assertThat(user.getPassword()).isEqualTo("Password previously encoded");
    }

    @Test
    void testSetPasswordOfUserToBeUpdatedIfWasChanged() {
        when(passwordEncoder.encode(anyString())).thenReturn("Encoded Password");
        user.setPassword("New raw password");
        User userSavedInDatabase = new User();
        userSavedInDatabase.setPassword("Password previously encoded");
        userService.setPasswordOfUserToBeUpdated(user, Optional.of(userSavedInDatabase));
        assertThat(user.getPassword()).isEqualTo("Encoded Password");
    }

    void createSecurityContext() {
        SecurityContextHolder.setContext(new SecurityContext() {
            @Override
            public Authentication getAuthentication() {
                return new Authentication() {
                    @Override
                    public Collection<? extends GrantedAuthority> getAuthorities() {
                        return null;
                    }

                    @Override
                    public Object getCredentials() {
                        return null;
                    }

                    @Override
                    public Object getDetails() {
                        return null;
                    }

                    @Override
                    public Object getPrincipal() {
                        return "example@gmail.com";
                    }

                    @Override
                    public boolean isAuthenticated() {
                        return false;
                    }

                    @Override
                    public void setAuthenticated(boolean b) throws IllegalArgumentException {

                    }

                    @Override
                    public String getName() {
                        return null;
                    }
                };
            }

            @Override
            public void setAuthentication(Authentication authentication) {

            }
        });
    }

    @Test
    void testIsUserTryingToDeleteItselfWhenPrincipalContainsSameEmail() {
        createSecurityContext();
        user.setEmailAddress("example@gmail.com");
        assertThat(userService.isUserTryingToDeleteItself(Optional.of(user))).isTrue();
    }

    @Test
    void testIsUserTryingToDeleteItselfWhenPrincipalContainsDifferentEmail() {
        createSecurityContext();
        user.setEmailAddress("other_example@gmail.com");
        assertThat(userService.isUserTryingToDeleteItself(Optional.of(user))).isFalse();
    }
}
