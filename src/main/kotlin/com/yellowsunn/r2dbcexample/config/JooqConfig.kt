package com.yellowsunn.r2dbcexample.config

import io.r2dbc.spi.ConnectionFactory
import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JooqConfig {
    @Bean
    fun jooqDslContext(connectionFactory: ConnectionFactory): DSLContext =
        DSL.using(connectionFactory).dsl()
}