package com.htbeyond.config

import com.htbeyond.enums.Gender.MALE
import com.htbeyond.enums.Role.USER
import com.htbeyond.model.Member
import com.htbeyond.service.CustomUserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration(proxyBeanMethods = false)
@EnableWebSecurity
class DefaultSecurityConfig{

    @Bean
    fun users(userDetailsService: CustomUserDetailsService): UserDetails {
        val member = Member(
            username = "minsoo.park",
            password = "1234",
            phoneNumber = "01071799190",
            email = "bakumando@htbeyond.com",
            gender = MALE,
            birthdate = "1989-01-06",
            nickname = "bakumando",
            role = USER
        )

        return userDetailsService.saveMember(member)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return NoOpPasswordEncoder.getInstance()
    }
}