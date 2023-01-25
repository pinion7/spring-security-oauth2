package com.htbeyond.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.MongoId
import org.springframework.security.oauth2.core.AuthorizationGrantType
import org.springframework.security.oauth2.core.OAuth2Token
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization.Token

@Document
class Oauth2Authorization(
    @MongoId
    val id: ObjectId = ObjectId.get(),

    val registeredClientId: String,

    val principalName: String,

    val authorizationGrantType: AuthorizationGrantType,

    val authorizedScopes: Set<String>,

    val tokens: Map<Class<out OAuth2Token?>, Token<*>>,

    val attributes: Map<String, Any>
) {

}