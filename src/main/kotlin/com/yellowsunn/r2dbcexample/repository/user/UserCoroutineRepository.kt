package com.yellowsunn.r2dbcexample.repository.user

import com.yellowsunn.r2dbcexample.entity.User
import com.yellowsunn.r2dbcexample.tables.Users.USERS
import kotlinx.coroutines.reactor.awaitSingle
import org.jooq.DSLContext
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import reactor.core.publisher.Flux

interface UserCoroutineRepository : CoroutineCrudRepository<User, Long>, UserCoroutineRepositoryCustom {
    suspend fun findByLastName(lastName: String): List<User>
}

interface UserCoroutineRepositoryCustom {
    suspend fun findByLastNameCustom(lastName: String): List<User>
}

class UserCoroutineRepositoryCustomImpl(
    private val dslContext: DSLContext,
) : UserCoroutineRepositoryCustom {
    override suspend fun findByLastNameCustom(lastName: String): List<User> {
        return Flux.from(
            dslContext.select(USERS.asterisk())
                .from(USERS)
                .where(USERS.LAST_NAME.eq(lastName))
        )
            .map { it.into(User::class.java) }
            .collectList()
            .awaitSingle()
    }
}