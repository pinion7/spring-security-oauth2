package com.htbeyond.model

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.MongoId
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.server.authorization.util.SpringAuthorizationServerVersion

@Document
class Oauth2ConsentAuthorization(
    @MongoId
    val id: ObjectId = ObjectId.get(),

    val registeredClientId: String? = null,

    val principalName: String? = null,

    val authorities: Set<GrantedAuthority>? = null,
) {

    companion object {
        const val AUTHORITIES_SCOPE_PREFIX: String = "SCOPE_"
    }
}