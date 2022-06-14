package com.practice.kotlinspringsecurity

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.authentication.logout.LogoutHandler
import javax.servlet.http.HttpSession

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests()
            .anyRequest()
            .authenticated()

        http.formLogin()

        http.logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login")
            .addLogoutHandler { request, response, authentication ->
                val session: HttpSession = request.session
                session.invalidate()
            }
            .logoutSuccessHandler { request, response, authentication ->
                response.sendRedirect("/login")
            }
            .deleteCookies("remember-me")

        return http.build()
    }
}