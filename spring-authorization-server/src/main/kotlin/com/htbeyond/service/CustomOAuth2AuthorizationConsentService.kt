package com.htbeyond.service

import org.springframework.security.oauth2.server.authorization.OAuth2Authorization
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository

class CustomOAuth2AuthorizationService(registeredClientRepository: RegisteredClientRepository) :
    OAuth2AuthorizationService {
    override fun save(authorization: OAuth2Authorization?) {
        TODO("Not yet implemented")
    }

    override fun remove(authorization: OAuth2Authorization?) {
        TODO("Not yet implemented")
    }

    override fun findById(id: String?): OAuth2Authorization? {
        TODO("Not yet implemented")
    }

    override fun findByToken(token: String?, tokenType: OAuth2TokenType?): OAuth2Authorization? {
        TODO("Not yet implemented")
    }

}
