package hu.pbes.blog.junitextensions.store;

import org.junit.jupiter.api.extension.*;

public class StoreExtension implements BeforeAllCallback, AfterAllCallback, ParameterResolver {

    private static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(StoreExtension.class);
    private static final String TESTING_STATE_KEY = "testingState";

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        ExtensionContext.Store store = context.getRoot().getStore(NAMESPACE);
        store.put(TESTING_STATE_KEY, new TestingState());
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        TestingState testingState = getTestingState(context);
        if (testingState != null) {
            testingState.report();
        }
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == TestingState.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return getTestingState(extensionContext);
    }

    private TestingState getTestingState(ExtensionContext context) {
        ExtensionContext.Store store = context.getRoot().getStore(NAMESPACE);
        return store.getOrComputeIfAbsent(TESTING_STATE_KEY, key -> new TestingState(), TestingState.class);
    }
}
