package hu.pbes.blog.junitextensions.testcontainers;

import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.IOException;

public class PostgreSQLExtension implements BeforeAllCallback, AfterAllCallback {

    private static PostgreSQLContainer<?> postgres;

    @Override
    @SneakyThrows
    public void beforeAll(ExtensionContext context) throws Exception {
        postgres = new PostgreSQLContainer<>("postgres:latest")
                .withDatabaseName("testdb")
                .withUsername("testuser")
                .withPassword("testpass");
        postgres.start();
        initProperties();
        initData();
    }

    private void initProperties() {
        System.setProperty("spring.datasource.url", postgres.getJdbcUrl());
        System.setProperty("spring.datasource.username", postgres.getUsername());
        System.setProperty("spring.datasource.password", postgres.getPassword());
        System.setProperty("spring.datasource.driver-class-name", "org.postgresql.Driver");
        System.setProperty("spring.jpa.database-platform", "org.hibernate.dialect.PostgreSQLDialect");
    }

    private void initData() throws IOException, InterruptedException {
        postgres.execInContainer("psql", "-U", "testuser", "-d", "testdb", "-c", "CREATE TABLE public.user (id INT PRIMARY KEY, name TEXT)");
        postgres.execInContainer("psql", "-U", "testuser", "-d", "testdb", "-c", "INSERT INTO public.user (id, name) VALUES (1, 'Alice'), (2, 'Bob')");
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        if (postgres != null) {
            postgres.stop();
        }
    }
}
