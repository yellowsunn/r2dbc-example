package com.yellowsunn.r2dbcexample.repository.todo

import com.yellowsunn.r2dbcexample.entity.Todo
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface TodoCoroutineRepository : CoroutineCrudRepository<Todo, Long> {
    suspend fun findByUserId(userId: Long): List<Todo>
}