package com.rost.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.rost.services.PersonDetailsService;
import lombok.RequiredArgsConstructor;

import static com.rost.enums.Role.*;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final PersonDetailsService personDetailsService;

    /**
     * Настраиваем саму логику аутентификации.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(personDetailsService).passwordEncoder(getPasswordEncoder());
    }


    /**
     * Конфигурируем сам SpringSecurity.
     * Конфигурируем авторизацию.
     * Все правила в конфиге читаются сверху вниз от более специфичных к более общим.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //Настраиваем авторизацию
                .authorizeRequests()
                .antMatchers("/admin").hasRole(ROLE_ADMIN.shortName())
                .antMatchers("/auth/login", "/error", "/auth/registration").permitAll() //Неавторизованным пользователям можно только сюда.
                .anyRequest().hasAnyRole(ROLE_USER.shortName(), ROLE_ADMIN.shortName())

                //Настраиваем страничку входа.
                .and()
                .formLogin()
                .loginPage("/auth/login")
                .loginProcessingUrl("/process_login") //Куда отправятся данные с формы?
                .defaultSuccessUrl("/hello", true)
                .failureUrl("/auth/login?error")
                .and()
                .logout().logoutUrl("/logout") //URL, при переходе на который происходит разлогинивание (стираются cookies, удаляется сессия by Spring).
                .logoutSuccessUrl("/auth/login");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        //return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }
}
