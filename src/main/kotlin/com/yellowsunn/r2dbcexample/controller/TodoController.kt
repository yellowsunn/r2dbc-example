package com.yellowsunn.r2dbcexample.controller

import com.yellowsunn.r2dbcexample.dto.TodosDto
import com.yellowsunn.r2dbcexample.service.TodoCoroutineService
import com.yellowsunn.r2dbcexample.service.TodoService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class TodoController(
    private val todoService: TodoService,
    private val todoCoroutineService: TodoCoroutineService,
) {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/users/{userId}/todos")
    fun getPhotoByReactor(@PathVariable userId: Long): Mono<ResponseEntity<TodosDto>> {
        return todoService.getTodosByUserId(userId)
            .map { ResponseEntity(it, HttpStatus.OK) }
            .defaultIfEmpty(ResponseEntity(HttpStatus.NOT_FOUND))
            .doOnError { e -> logger.error("message={}", e.message, e) }
    }

    @GetMapping("/users/{userId}/todos/coroutine")
    suspend fun getPhotoByCoroutine(@PathVariable userId: Long): ResponseEntity<TodosDto> {
        val todosDto: TodosDto? = todoCoroutineService.getTodosByUserId(userId)
        return if (todosDto == null) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } else {
            ResponseEntity(todosDto, HttpStatus.OK)
        }
    }
}