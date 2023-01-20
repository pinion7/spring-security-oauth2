package com.htbeyond.repository

import com.htbeyond.model.Member
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface MemberRepository : MongoRepository<Member, ObjectId> {

    fun findByUsername(username: String): Member?
}