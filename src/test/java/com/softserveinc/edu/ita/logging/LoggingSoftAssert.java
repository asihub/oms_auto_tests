package com.softserveinc.edu.ita.logging;

import org.testng.Reporter;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

/**
 * Wrapper class for TestNG soft asserts.
 */
public final class LoggingSoftAssert extends SoftAssert {

    private String message;

    public LoggingSoftAssert() {
        super();
        message = "";
    }

    /**
     * This method logs successful soft asserts.
     */
    @Override
    public void onAssertSuccess(final IAssert assertCommand) {
        if (!assertCommand.getMessage().equals(message)) {
            Reporter.log(String.format("<br><font color='green'>PASSED</font> - %s", assertCommand.getMessage()));
        }
    }

    /**
     * This method logs failed soft asserts.
     */
    @Override
    public void onAssertFailure(final IAssert assertCommand, final AssertionError ex) {
        message = assertCommand.getMessage();
        Reporter.log(String.format("<br><font color='orange'>FAILED - %s</font>", assertCommand.getMessage()));
    }
}
