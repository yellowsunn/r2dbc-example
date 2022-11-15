package com.yellowsunn.r2dbcexample.entity

import org.springframework.data.annotation.Id
import javax.persistence.Entity

@Entity
class Todo(
    @Id val id: Long,
    val userId: Long,
    val title: String,
    val body: String,
)