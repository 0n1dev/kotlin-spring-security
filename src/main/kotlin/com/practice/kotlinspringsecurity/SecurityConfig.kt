package com.practice.kotlinspringsecurity

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.SecurityFilterChain
import javax.servlet.http.HttpSession

@Configuration
@EnableWebSecurity
class SecurityConfig @Autowired constructor(
    private val userDetailsService: UserDetailsService
) {

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

        http.rememberMe()
            .rememberMeParameter("remember-me")
            .tokenValiditySeconds(3600)
            .userDetailsService(userDetailsService)

        return http.build()
    }
}