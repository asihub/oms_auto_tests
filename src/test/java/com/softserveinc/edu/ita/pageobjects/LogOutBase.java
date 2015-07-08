package com.softserveinc.edu.ita.pageobjects;

import com.softserveinc.edu.ita.locators.interfaces.ILogOutAble;
import com.softserveinc.edu.ita.locators.CommonLocators;
import org.openqa.selenium.WebDriver;

/**
 * PageObject class that represents pages that have log out functionality.
 */
public class LogOutBase extends PageObjectBase implements ILogOutAble {

    public LogOutBase(final WebDriver driver) {
        super(driver);
    }

    public HomePage clickLogOutButton() {
        click(CommonLocators.LOG_OUT_BUTTON);
        return new HomePage(driver);
    }
}



