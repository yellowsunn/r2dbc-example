package com.yellowsunn.r2dbcexample.repository.user

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import reactor.test.StepVerifier

@ActiveProfiles("test")
@SpringBootTest
internal class UserRepositoryIntegrationTest {
    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    fun findByLastNameTest() {
        //given
        val lastName = "world"

        //when
        //then
        StepVerifier.create(userRepository.findByLastName(lastName))
            .assertNext { assertThat(it.lastName).isEqualTo(lastName) }
            .verifyComplete()
    }

    @Test
    fun findByLastNameCustomTest() {
        //given
        val lastName = "world"

        //when
        //then
        StepVerifier.create(userRepository.findByLastNameCustom(lastName))
            .assertNext { assertThat(it.lastName).isEqualTo(lastName) }
            .verifyComplete()
    }
}