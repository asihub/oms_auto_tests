package com.softserveinc.edu.ita.locators;

import com.softserveinc.edu.ita.locators.interfaces.ILocator;
import org.openqa.selenium.By;

public enum CommonLocators implements ILocator {

    USER_INFO_TAB(
            "User info tab",
            LocatorsType.BY_XPATH,
            ".//*[@id='nav']//a[@href='/OMS/userInfo.htm']"),
    ITEM_MANAGEMENT_TAB(
            "Item management tab",
            LocatorsType.BY_XPATH,
            ".//*[@id='nav']//a[@href='/OMS/itemManagement.htm']"),
    ADMINISTRATION_TAB(
            "Administration tab",
            LocatorsType.BY_XPATH,
            ".//*[@id='nav']//a[@href='/OMS/users.htm']"),
    ORDERING_TAB(
            "Ordering tab",
            LocatorsType.BY_XPATH,
            ".//*[@id='nav']//a[@href='/OMS/order.htm']"),
    LOG_OUT_BUTTON(
            "Logout button",
            LocatorsType.BY_XPATH,
            "//img[@alt='logout']"),
    ACTIVE_TAB(
            "Active tab",
            LocatorsType.BY_XPATH,
            ".//a[parent::li[@class='cur']]"),
    EN_LINK(
            "English language link",
            LocatorsType.BY_XPATH,
            ".//a[@href='?lang=en_US']"),
    UA_LINK(
            "Ukrainian language link",
            LocatorsType.BY_XPATH,
            ".//a[@href='?lang=uk_UA']");

    private String name;
    private LocatorsType locatorsType;
    private String rawLocator;
    private String modifiedLocator;
    private By byLocator;

    //This constructor sets only 3 fields of object. The rest are prepared separately.
    CommonLocators(final String name, final LocatorsType locatorsType, final String rawLocator) {
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

    /**
     * Modifies the locator by inserting the given string.
     *
     * @param parameter - modifier that will be inserted into the locator.
     */
    public CommonLocators modify(final String parameter) {
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