package com.practice.kotlinspringsecurity

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.AuthenticationSuccessHandler

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests()
            .anyRequest()
            .authenticated()

        http.formLogin()
//            .loginPage("/loginPage")
            .defaultSuccessUrl("/")
            .failureUrl("/login")
            .usernameParameter("userId")
            .passwordParameter("passwd")
            .loginProcessingUrl("/login_proc")
            .successHandler { request, response, authentication ->
                println(authentication.name)
                response.sendRedirect("/")
            }
            .failureHandler { request, response, exception ->
                println(exception.message)
                response.sendRedirect("/login")
            }
            .permitAll()

        return http.build()
    }
}