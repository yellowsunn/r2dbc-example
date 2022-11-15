package com.yellowsunn.r2dbcexample.service

import com.yellowsunn.r2dbcexample.dto.TodosDto
import com.yellowsunn.r2dbcexample.entity.Todo
import com.yellowsunn.r2dbcexample.entity.User
import com.yellowsunn.r2dbcexample.repository.todo.TodoRepository
import com.yellowsunn.r2dbcexample.repository.user.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.reactive.TransactionalOperator
import reactor.core.publisher.Mono
import kotlin.streams.toList

interface TodoService {
    fun getTodosByUserId(userId: Long): Mono<TodosDto>
}

@Service
class TodoServiceImpl(
    private val userRepository: UserRepository,
    private val todoRepository: TodoRepository,
    private val transactionalOperator: TransactionalOperator,
) : TodoService {
    override fun getTodosByUserId(userId: Long): Mono<TodosDto> {
        return Mono.zip(
            userRepository.findById(userId),
//                .subscribeOn(Schedulers.boundedElastic()),
            todoRepository.findByUserId(userId)
                .collectList()
//                .subscribeOn(Schedulers.boundedElastic())
        ) { user: User, todos: List<Todo> ->
            val todoDtos = todos.stream()
                .map { todo -> TodosDto.Todo(todo.id, todo.title, todo.body) }
                .toList()

            TodosDto(
                userId = user.id,
                name = "${user.firstName} ${user.lastName}",
                todos = todoDtos
            )
        }.`as` { transactionalOperator.transactional(it) }
    }
}