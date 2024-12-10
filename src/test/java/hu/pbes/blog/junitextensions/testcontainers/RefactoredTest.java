package hu.pbes.blog.junitextensions.testcontainers;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(PostgreSQLExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
        "spring.main.lazy-initialization=true"
})
class RefactoredTest {
    @Autowired
    EntityManager entityManager;

    @Test
    void test() {
        // Query data
        var result = entityManager.createNativeQuery("SELECT COUNT(*) FROM public.user").getSingleResult();
        assertThat(result).isEqualTo(2L);
    }
}
