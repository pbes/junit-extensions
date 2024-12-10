package hu.pbes.blog.junitextensions.logging;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.Optional;

public class LoggingExtension implements TestWatcher, BeforeEachCallback {

    private long startMilliTime;

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        startMilliTime = System.currentTimeMillis();
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        System.out.println(context.getDisplayName() + " DISABLED" + reason.map(r -> ": " + r).orElse(""));
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        long duration = System.currentTimeMillis() - startMilliTime;
        System.out.println(context.getDisplayName() + " SUCCESSFUL in " + duration + "ms");
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        System.out.println(context.getDisplayName() + " ABORTED");
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        System.out.println(context.getDisplayName() + " FAILED with " + cause.getMessage());
    }
}
