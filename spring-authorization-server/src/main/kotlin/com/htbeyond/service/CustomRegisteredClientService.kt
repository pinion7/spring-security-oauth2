package com.htbeyond.service

import com.htbeyond.model.Client
import com.htbeyond.repository.ClientRepository
import org.bson.types.ObjectId
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository
import org.springframework.stereotype.Repository

@Repository
class CustomRegisteredClientService(
    private val clientRepository: ClientRepository
) : RegisteredClientRepository {

    override fun save(registeredClient: RegisteredClient) {
        clientRepository.save(Client.fromRegisteredClient(registeredClient))
    }

    override fun findById(id: String): RegisteredClient {
        val client = clientRepository.findById(ObjectId(id)).orElseThrow()
        return client.toRegisteredClient()
    }

    override fun findByClientId(clientId: String): RegisteredClient? {
        return clientRepository.findByClientId(clientId)?.toRegisteredClient()
    }
}