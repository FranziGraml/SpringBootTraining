package trainingSpringBoot.training.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import trainingSpringBoot.training.entity.Role;

@RequiredArgsConstructor
@Configuration
public class UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    @Bean
    public InMemoryUserDetailsManager users() {
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("userPassword"))
                .authorities(Role.MEMBER.getGrantedAuthorities())
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("adminPassword"))
                .authorities(Role.ADMIN.getGrantedAuthorities())
                .build();

        UserDetails analyst = User.builder()
                .username("analyst")
                .password(passwordEncoder.encode("analystPassword"))
                .authorities(Role.ANALYST.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(user, admin, analyst);

    }


}
