package br.nicolas.remembering;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

public abstract class AbstractIntegrationTestConfig {

    static final MySQLContainer<?> mysql;

    static {
        mysql = new MySQLContainer<>("mysql:8.4.0")
                .withDatabaseName("ItTestDb")
                .withUsername("ItUserDb")
                .withPassword("ItPasswordDb");

        mysql.start();
    }

    @DynamicPropertySource
    static void dbProps (DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
        registry.add("spring.datasource.driver-class-name", () -> "com.mysql.cj.jdbc.Driver");
        registry.add("spring.database-platform", () -> "org.hibernate.dialect.MySQLDialect");
    }
}
