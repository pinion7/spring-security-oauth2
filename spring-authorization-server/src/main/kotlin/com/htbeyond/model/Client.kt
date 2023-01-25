package com.htbeyond.model

import java.time.Instant
import java.util.function.Consumer
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.MongoId
import org.springframework.security.oauth2.core.AuthorizationGrantType
import org.springframework.security.oauth2.core.ClientAuthenticationMethod
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings

@Document("CLIENT")
class Client(
    @MongoId
    val id: ObjectId = ObjectId.get(),

    @Indexed(unique = true, sparse = true)
    val clientId: String,

    val clientIdIssuedAt: Instant? = Instant.now(),

    val clientSecret: String?,

    val clientSecretExpiresAt: Instant? = Instant.now().takeIf { clientSecret != null },

    val clientName: String,

    val clientAuthenticationMethods: Set<ClientAuthenticationMethod>,

    val authorizationGrantTypes: Set<AuthorizationGrantType>,

    val redirectUris: Set<String>,

    val scopes: Set<String>,

    val clientSettings: ClientSettings?,

    val tokenSettings: TokenSettings?
) {

    fun toRegisteredClient(): RegisteredClient {
        return RegisteredClient
            .withId(id.toString())
            .clientId(clientId)
            .clientIdIssuedAt(clientIdIssuedAt)
            .clientSecret(clientSecret)
            .clientSecretExpiresAt(clientSecretExpiresAt)
            .clientName(clientName)
            .clientAuthenticationMethods(Consumer { clientAuthenticationMethods })
            .authorizationGrantTypes { authorizationGrantTypes }
            .redirectUris { redirectUris }
            .scopes { scopes }
            .clientSettings(clientSettings)
            .tokenSettings(tokenSettings)
            .build()
    }

    companion object {
        fun fromRegisteredClient(registeredClient: RegisteredClient): Client {
            return Client(
                id = ObjectId(registeredClient.id),
                clientId = registeredClient.clientId,
                clientSecret = registeredClient.clientSecret,
                clientName = registeredClient.clientName,
                clientAuthenticationMethods = registeredClient.clientAuthenticationMethods,
                authorizationGrantTypes = registeredClient.authorizationGrantTypes,
                redirectUris = registeredClient.redirectUris,
                scopes = registeredClient.scopes,
                clientSettings = registeredClient.clientSettings,
                tokenSettings = registeredClient.tokenSettings
            )
        }
    }
}