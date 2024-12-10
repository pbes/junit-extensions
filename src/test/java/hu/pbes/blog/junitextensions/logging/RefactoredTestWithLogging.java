package hu.pbes.blog.junitextensions.logging;

import hu.pbes.blog.junitextensions.testcontainers.PostgreSQLExtension;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith({
        PostgreSQLExtension.class,
        LoggingExtension.class
})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
        "spring.main.lazy-initialization=true"
})
class RefactoredTestWithLogging {

    @Autowired
    EntityManager entityManager;

    @Test
    void test() {
        // Query data
        var result = entityManager.createNativeQuery("SELECT COUNT(*) FROM public.user").getSingleResult();
        assertThat(result).isEqualTo(2L);
    }
}
