package com.htbeyond.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain


@EnableWebSecurity
@Configuration
class ResourceServerConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .securityMatcher("/beyond/**")
            .authorizeHttpRequests()
            .requestMatchers("/beyond/**")
            .hasAnyAuthority("SCOPE_beyond.read", "SCOPE_openid")
            .and()
            .oauth2ResourceServer()
            .jwt()
        return http.build()
    }
}