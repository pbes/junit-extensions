package hu.pbes.blog.junitextensions.store;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestingState {
    private final Map<String, String> results = new ConcurrentHashMap<>();

    public void addResult(String testName, String result) {
        results.put(testName, result);
    }

    public void report() {
        System.out.println("---- Test Results ----");
        results.forEach((testName, result) -> {
            System.out.println(testName + ": " + result);
        });
    }

}
