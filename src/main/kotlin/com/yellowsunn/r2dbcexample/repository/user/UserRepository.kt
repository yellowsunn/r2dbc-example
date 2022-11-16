package com.yellowsunn.r2dbcexample.repository.user

import com.yellowsunn.r2dbcexample.entity.User
import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

private const val USER = "users"

interface UserRepository : ReactiveCrudRepository<User, Long>, UserRepositoryCustom {
    fun findByLastName(lastName: String): Flux<User>
}

interface UserRepositoryCustom {
    fun findByLastNameCustom(lastName: String): Flux<User>
    fun findByFirstNameCustom(firstName: String): Flux<User>
}

class UserRepositoryCustomImpl(
    private val entityTemplate: R2dbcEntityTemplate,
    private val dslContext: DSLContext,
) : UserRepositoryCustom {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    override fun findByLastNameCustom(lastName: String): Flux<User> {
        logger.info(dslContext.toString())

        return entityTemplate
            .select(User::class.java)
            .from(USER)
            .matching(
                Query.query(Criteria.where(USER).`is`(lastName))
            )
            .all()
    }

    override fun findByFirstNameCustom(firstName: String): Flux<User> {
        return Flux.from(
            dslContext.select()
                .from(DSL.table(USER))
                .where(DSL.field("first_name").eq(firstName))
        ).map { it.into(User::class.java) }
    }
}
