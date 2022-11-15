package com.yellowsunn.r2dbcexample.repository.user

import com.yellowsunn.r2dbcexample.entity.User
import com.yellowsunn.r2dbcexample.entity.User_
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
}

class UserRepositoryCustomImpl(
    private val entityTemplate: R2dbcEntityTemplate,
) : UserRepositoryCustom {
    override fun findByLastNameCustom(lastName: String): Flux<User> {
        return entityTemplate
            .select(User::class.java)
            .from(USER)
            .matching(
                Query.query(Criteria.where(User_.LAST_NAME).`is`(lastName))
            )
            .all()
    }
}
