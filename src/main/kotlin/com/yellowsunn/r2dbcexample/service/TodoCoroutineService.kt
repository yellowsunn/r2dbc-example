package com.yellowsunn.r2dbcexample.service

import com.yellowsunn.r2dbcexample.dto.TodosDto
import com.yellowsunn.r2dbcexample.entity.Todo
import com.yellowsunn.r2dbcexample.entity.User
import com.yellowsunn.r2dbcexample.repository.todo.TodoCoroutineRepository
import com.yellowsunn.r2dbcexample.repository.user.UserCoroutineRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface TodoCoroutineService {
    suspend fun getTodosByUserId(userId: Long): TodosDto?
}

@Service
class TodoCoroutineServiceImpl(
    private val userCoroutineRepository: UserCoroutineRepository,
    private val todoCoroutineRepository: TodoCoroutineRepository,
) : TodoCoroutineService {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @Transactional(readOnly = true)
    override suspend fun getTodosByUserId(userId: Long): TodosDto? = coroutineScope {
        // TransactionalOperator.executeAndAwait { ... } // 명시적인 트랜잭션을 사용할 수도 있다.
        val deferredUser: Deferred<User?> = async {
            userCoroutineRepository.findById(userId)
        }
        val deferredTodo: Deferred<List<Todo>> = async {
            todoCoroutineRepository.findByUserId(userId)
        }

        deferredUser.await()
            ?.let { user ->
                val todosDto = deferredTodo.await()
                    .map { todo -> TodosDto.Todo(todo.id, todo.title, todo.body) }
                    .toList()

                TodosDto(
                    userId = user.id,
                    name = "${user.firstName} ${user.lastName}",
                    todos = todosDto
                )
            }
    }
}