package com.htbeyond.model

import com.htbeyond.core.CustomUserDetails
import com.htbeyond.enums.Gender
import com.htbeyond.enums.Role
import java.time.OffsetDateTime
import org.bson.types.ObjectId
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.MongoId

@Document("MEMBER")
class Member(
    @MongoId
    val id: ObjectId = ObjectId.get(),

    @Indexed(unique = true, sparse = true)
    val username: String,

    var password: String,

    @Indexed(unique = true, sparse = true)
    var phoneNumber: String? = null,

    @Indexed(unique = true, sparse = true)
    var email: String? = null,

    var gender: Gender?,

    var birthdate: String?,

    @Indexed(unique = true, sparse = true)
    var nickname: String?,

    var role: Role,
) {
    @CreatedDate
    var createdAt: OffsetDateTime? = null

    @LastModifiedDate
    var lastModifiedAt: OffsetDateTime? = null

    private fun getRoleKey(): String {
        return role.name
    }

    fun toUserDetails(): CustomUserDetails {
        return CustomUserDetails(
            username = username,
            password = password,
            phoneNumber = phoneNumber,
            authority = getRoleKey(),
            email = email,
            gender = gender,
            birthdate = birthdate,
            nickname = nickname
        )
    }
}
