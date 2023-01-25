package com.htbeyond.repository

import com.htbeyond.model.Client
import java.util.Optional
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient

interface ClientRepository : MongoRepository<Client, ObjectId> {

    fun findByClientId(clientId: String): Client?
}