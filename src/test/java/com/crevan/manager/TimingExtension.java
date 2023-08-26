package com.crevan.manager;

import org.junit.jupiter.api.extension.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

public class TimingExtension implements
        BeforeTestExecutionCallback, AfterTestExecutionCallback, BeforeAllCallback, AfterAllCallback {

    private static final Logger log = LoggerFactory.getLogger("result");

    private StopWatch stopWatch;

    @Override
    public void afterAll(final ExtensionContext extensionContext) {
        log.info('\n' + stopWatch.prettyPrint() + '\n');
    }

    @Override
    public void afterTestExecution(final ExtensionContext extensionContext) {
        stopWatch.stop();
    }

    @Override
    public void beforeAll(final ExtensionContext extensionContext) {
        stopWatch = new StopWatch("Execution time of " + extensionContext.getRequiredTestClass().getSimpleName());
    }

    @Override
    public void beforeTestExecution(final ExtensionContext extensionContext) {
        String testName = extensionContext.getDisplayName();
        log.info("\nStart " + testName);
        stopWatch.start(testName);
    }
}
