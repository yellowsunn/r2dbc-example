package com.yellowsunn.r2dbcexample.repository.user

import com.yellowsunn.r2dbcexample.Tables.USERS
import com.yellowsunn.r2dbcexample.entity.User
import org.jooq.DSLContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface UserRepository : ReactiveCrudRepository<User, Long>, UserRepositoryCustom {
    fun findByLastName(lastName: String): Flux<User>
}

interface UserRepositoryCustom {
    fun findByLastNameCustom(lastName: String): Flux<User>
}

class UserRepositoryCustomImpl(
    private val dslContext: DSLContext,
) : UserRepositoryCustom {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    override fun findByLastNameCustom(lastName: String): Flux<User> {
        return Flux.from(
            dslContext.select(USERS.asterisk())
                .from(USERS)
                .where(USERS.LAST_NAME.eq(lastName))
        ).map { it -> it.into(User::class.java) }
    }
}
