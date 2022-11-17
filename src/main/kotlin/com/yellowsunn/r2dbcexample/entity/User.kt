package com.yellowsunn.r2dbcexample.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(name = "users")
class User(
    @Id val id: Long,
    val firstName: String,
    val lastName: String,
)