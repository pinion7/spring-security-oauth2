package com.htbeyond.core

import com.htbeyond.enums.Gender
import com.htbeyond.enums.Role
import com.htbeyond.model.Member
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails(
    private val username: String,
    private val password: String,
    private val authority: String,
    private val phoneNumber: String?,
    private val email: String?,
    private val gender: Gender?,
    private val birthdate: String?,
    private val nickname: String?,
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(GrantedAuthority { authority })
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }

    fun getPhoneNumber(): String? {
        return phoneNumber
    }

    fun getEmail(): String? {
        return email
    }

    fun getGender(): Gender? {
        return gender
    }

    fun getBirthdate(): String? {
        return birthdate
    }

    fun getNickname(): String? {
        return nickname
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    fun toMember(): Member {
        return Member(
            username = username,
            password = password,
            phoneNumber = phoneNumber,
            email = email,
            gender = gender,
            birthdate = birthdate,
            nickname = nickname,
            role = Role.valueOf(authority)
        )
    }
}