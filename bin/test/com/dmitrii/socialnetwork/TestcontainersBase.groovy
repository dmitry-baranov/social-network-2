package com.dmitrii.socialnetwork

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Testcontainers
import spock.lang.Shared
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
abstract class TestcontainersBase extends Specification {

    @Shared
    static postgres = new PostgreSQLContainer<>("postgres:16.2-alpine")
            .withDatabaseName("test_db")
            .withUsername("test_user")
            .withPassword("test_pass")
            .withLabel("com.testcontainers.desktop.service", "testcontainers_postgres")

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres.&getJdbcUrl)
        registry.add("spring.datasource.username", postgres.&getUsername)
        registry.add("spring.datasource.password", postgres.&getPassword)
    }

    static {
        postgres.start()
    }
}
