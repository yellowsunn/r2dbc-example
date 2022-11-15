package com.yellowsunn.r2dbcexample.config

import org.flywaydb.core.Flyway
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.core.env.Environment

@Profile("local | test")
@Configuration
class FlywayConfiguration(
    private val env: Environment,
) {
    @Bean(initMethod = "migrate")
    fun flyway(): Flyway {
        return Flyway(Flyway.configure()
            .dataSource(
                env.getRequiredProperty("spring.flyway.url"),
                env.getRequiredProperty("spring.flyway.user"),
                env.getRequiredProperty("spring.flyway.password")
            )
        )
    }
}