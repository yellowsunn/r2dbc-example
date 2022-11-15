package com.yellowsunn.r2dbcexample.repository.user

import com.yellowsunn.r2dbcexample.entity.User
import com.yellowsunn.r2dbcexample.entity.User_
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

private const val USER = "users"

interface UserCoroutineRepository : CoroutineCrudRepository<User, Long>, UserCoroutineRepositoryCustom {
    suspend fun findByLastName(lastName: String): List<User>
}

interface UserCoroutineRepositoryCustom {
    suspend fun findByLastNameCustom(lastName: String): List<User>
}

class UserCoroutineRepositoryCustomImpl(
    private val entityTemplate: R2dbcEntityTemplate,
) : UserCoroutineRepositoryCustom {
    override suspend fun findByLastNameCustom(lastName: String): List<User> {
        return entityTemplate
            .select(User::class.java)
            .from(USER)
            .matching(
                Query.query(Criteria.where(User_.LAST_NAME).`is`(lastName))
            )
            .all()
            .collectList()
            .awaitSingle()
    }
}