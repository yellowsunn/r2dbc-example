package com.yellowsunn.r2dbcexample.repository.user

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
internal class UserCoroutineRepositoryIntegrationTest {
    @Autowired
    lateinit var userCoroutineRepository: UserCoroutineRepository

    @Test
    fun findByLastNameTest() = runTest {
        //given
        val lastName = "world"

        //when
        val users = userCoroutineRepository.findByLastName(lastName)

        //then
        assertThat(users[0].lastName).isEqualTo(lastName)
    }

    @Test
    fun findByLastNameCustomTest() = runTest {
        //given
        val lastName = "world"

        //when
        val users = userCoroutineRepository.findByLastNameCustom(lastName)

        //then
        assertThat(users[0].lastName).isEqualTo(lastName)
    }
}