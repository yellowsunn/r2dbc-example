package com.yellowsunn.r2dbcexample.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(name = "todo")
class Todo(
    @Id val id: Long,
    val userId: Long,
    val title: String,
    val body: String,
)