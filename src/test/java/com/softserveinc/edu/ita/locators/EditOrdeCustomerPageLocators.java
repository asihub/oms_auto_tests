package com.softserveinc.edu.ita.locators;

import com.softserveinc.edu.ita.locators.interfaces.ILocator;
import org.openqa.selenium.By;

public enum EditOrdeCustomerPageLocators implements ILocator {

    ORDER_NUMBER_FIELD(
            "Change order number in order",
            LocatorsType.BY_XPATH,
            ".//*[@id='orderNum']"),
    SAVE_ORDER_BUTTON(
            "Button save a changes ib Order",
            LocatorsType.BY_XPATH,
            ".//*[@id='save']"),
    ASSIGNEE_LIST_USERS(
            "List of assignee",
            LocatorsType.BY_XPATH,
            ".//*[@id='assignee']"),
    ASSIGNEE_FIRST_USER(
            "Assignee first user in list",
            LocatorsType.BY_XPATH,
            ".//*[@id='assignee']/option[2]");


    private String name;
    private LocatorsType locatorsType;
    private String rawLocator;
    private String modifiedLocator;
    private By byLocator;

    //This constructor sets only 3 fields of object. The rest are prepared separately.
    EditOrdeCustomerPageLocators(String name, LocatorsType locatorsType, String rawLocator) {
        this.name = name;
        this.locatorsType = locatorsType;
        this.rawLocator = rawLocator;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    //This method prepares locator using additional parameter by means of so called "string-format" method.
    public EditOrdeCustomerPageLocators modify(String parameter) {
        this.modifiedLocator = String.format(this.rawLocator, parameter);
        return this;
    }

    @Override
//This method converts locator into "By" format.
    public By getBy() {
        //This block of code is used to leave raw locator intact giving a possibility to use parameterized locator again.
        if (this.modifiedLocator == null) {
            this.byLocator = this.locatorsType.getBy(this.rawLocator);
        } else {
            this.byLocator = this.locatorsType.getBy(this.modifiedLocator);
        }
        return this.byLocator;
    }
}

