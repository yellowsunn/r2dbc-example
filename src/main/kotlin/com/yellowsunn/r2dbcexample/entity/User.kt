package com.yellowsunn.r2dbcexample.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import javax.persistence.Entity

@Entity
@Table(name = "users")
data class User(
    @Id val id: Long,
    val firstName: String,
    val lastName: String,
)