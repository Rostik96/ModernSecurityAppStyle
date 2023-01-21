package com.rost.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.rost.services.PersonDetailsService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final PersonDetailsService personDetailsService;

    /**
     * Настраиваем саму логику аутентификации.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(personDetailsService);
    }


    /**
     * Конфигурируем сам SpringSecurity.
     * Конфигурируем авторизацию.
     * Все правила в конфиге читаются сверху вниз от более специфичных к более общим.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                //Настраиваем авторизацию
                .authorizeRequests()
                .antMatchers("/auth/login", "/error").permitAll() //Неавторизованным пользователям можно только сюда.
                .anyRequest().authenticated()

                //Настраиваем страничку входа.
                .and()
                .formLogin()
                .loginPage("/auth/login")
                .loginProcessingUrl("/process_login") //Куда отправятся данные с формы?
                .defaultSuccessUrl("/hello", true)
                .failureUrl("/auth/login?error");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
