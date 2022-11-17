package com.yellowsunn.r2dbcexample.repository.todo

import com.yellowsunn.r2dbcexample.entity.Todo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@ExperimentalCoroutinesApi
@ActiveProfiles("test")
@SpringBootTest
internal class TodoCoroutineRepositoryIntegrationTest {
    @Autowired
    lateinit var todoCoroutineRepository: TodoCoroutineRepository

    @Test
    fun findByUserIdTest() = runTest {
        //given
        val userId = 1L

        //when
        val todos: List<Todo> = todoCoroutineRepository.findByUserId(userId)

        //then
        assertThat(todos).hasSize(2)
    }
}