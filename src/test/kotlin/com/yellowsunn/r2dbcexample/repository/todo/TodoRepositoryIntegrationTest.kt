package com.yellowsunn.r2dbcexample.repository.todo

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import reactor.test.StepVerifier

@ActiveProfiles("test")
@SpringBootTest
internal class TodoRepositoryIntegrationTest {
    @Autowired
    lateinit var todoRepository: TodoRepository

    @Test
    fun findByUserIdTest() {
        StepVerifier.create(todoRepository.findByUserId(1L))
            .expectNextCount(2L)
            .verifyComplete()
    }
}