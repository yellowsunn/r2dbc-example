package com.yellowsunn.r2dbcexample.repository.todo

import com.yellowsunn.r2dbcexample.entity.Todo
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface TodoRepository : ReactiveCrudRepository<Todo, Long> {
    fun findByUserId(userId: Long): Flux<Todo>
}