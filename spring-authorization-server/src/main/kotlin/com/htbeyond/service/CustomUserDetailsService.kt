package com.htbeyond.service

import com.htbeyond.model.Member
import com.htbeyond.repository.MemberRepository
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.InternalAuthenticationServiceException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val memberRepository: MemberRepository
) : UserDetailsService {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun loadUserByUsername(username: String): UserDetails {
        return memberRepository.findByUsername(username)?.toUserDetails()
            ?: throw InternalAuthenticationServiceException("username 혹은 password가 일치하지 않아 인증에 실패하였습니다.")
    }

    fun saveMember(member: Member): UserDetails {
        return memberRepository.save(member).toUserDetails()
    }
}