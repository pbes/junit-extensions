package hu.pbes.blog.junitextensions.store;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.Assert.assertTrue;

@ExtendWith(StoreExtension.class)
class TestWithStore {

    @Test
    void test(TestingState testingState) {
        // do test
        assertTrue(true);
        testingState.addResult("test", "SUCCESS");
    }
}