package com.softserveinc.edu.ita.locators;

import com.softserveinc.edu.ita.locators.interfaces.ILocator;
import org.openqa.selenium.By;

public enum OrderingPageLocators implements ILocator {

    CREATE_NEW_ORDER_LINK(
            "Create new order link",
            LocatorsType.BY_XPATH,
            ".//a[@href='orderItemsCreate.htm']"),
    QUANTITY_OF_ROWS(
            "Quantity of rows",
            LocatorsType.BY_XPATH,
            ".//*[@id='allFoundAndFiltered']"),
    QUANTITY_OF_ROWS_PER_PAGE(
            "Quantity of rows per page",
            LocatorsType.BY_XPATH,
            ".//*[@id='list']/table/tbody/tr/td[1]"),
    CLICK_NEXT_BUTTON(
            "Next button",
            LocatorsType.BY_XPATH,
            ".//*[@name='nextPage']"),
    CLICK_FIRST_BUTTON(
            "First button",
            LocatorsType.BY_XPATH,
            ".//*[@name='firstPage']"),
    SEARCH_FILTER_LABEL(
            "Search filter title label",
            LocatorsType.BY_XPATH,
            "//tr[2]/td[1]"),
    EDIT_TABLE_LABEL(
            "Edit table label",
            LocatorsType.BY_XPATH,
            "//tr[1]/th[8]"),
    FILTER_SELECT(
            "Filter select",
            LocatorsType.BY_XPATH,
            ".//*[@id='filterBy']"),
    FILTER_VALUE_SELECT(
            "Filter value select",
            LocatorsType.BY_XPATH,
            ".//*[@id='filterValue']"),
    SEARCH_CONDITION_SELECT(
            "Search condition select",
            LocatorsType.BY_XPATH,
            ".//*[@id='search']"),
    SEARCH_FIELD(
            "Search field",
            LocatorsType.BY_XPATH,
            ".//*[@id='searchValue']"),
    SELECTED_FILTER(
            "Selected filter",
            LocatorsType.BY_XPATH,
            ".//*[@id='filterBy']//*[@selected='selected']"),
    SELECTED_FILTER_VALUE(
            "Selected filter value",
            LocatorsType.BY_XPATH,
            ".//*[@id='filterValue']//*[@selected='selected']"),
    SELECTED_SEARCH_CONDITION(
            "Selected search condition",
            LocatorsType.BY_XPATH,
            ".//*[@id='search']//*[@selected='selected']"),
    APPLY_BUTTON(
            "Apply button",
            LocatorsType.BY_XPATH,
            ".//input[@name='Apply']"),
    TABLE_ROW_CELL(
            "Table row cell",
            LocatorsType.BY_XPATH,
            ".//div[@id='list']/table/tbody/tr[%s]/td[1]"),
    TABLE_ROW(
            "Table row",
            LocatorsType.BY_XPATH,
            ".//div[@id='list']/table/tbody/tr[%s]/td"),
    TABLE_COLUMN(
            "Table column",
            LocatorsType.BY_XPATH,
            ".//*[@id='list']/table/tbody/tr[1]/th/a[contains(text(), '%s')]"),
    ORDER_NAME_COLUMN(
            "Order name",
            LocatorsType.BY_XPATH,
            "//div[2]/table/tbody/tr/td[1]"),
    ORDER_STATUS_COLUMN(
            "Order status",
            LocatorsType.BY_XPATH,
            "//div[2]/table/tbody/tr/td[5]"),
    ORDER_ASSIGNEE_COLUMN(
            "Order assignee",
            LocatorsType.BY_XPATH,
            "//div[2]/table/tbody/tr/td[6]"),
    ROLE_COLUMN(
            "Role",
            LocatorsType.BY_XPATH,
            "//div[2]/table/tbody/tr/td[7]"),
    EDIT_LINK(
            "Edit link",
            LocatorsType.BY_XPATH,
            "//tr[2]/td[8]/a"),
    DELETE_ORDER_BUTTON(
            "Delete order button",
            LocatorsType.BY_XPATH,
            ".//*[@id='list']/table/tbody/tr[2]/td[9]/a"),
    FIRST_ORDER(
            "First order in the table",
            LocatorsType.BY_XPATH,
            ".//*[@id='list']/table/tbody/tr[2]/td[1]"),
    CANNOT_EDIT_ORDER_MASSEGE(
            "You cant see this order",
            LocatorsType.BY_XPATH,
            ".//*[@id='content']");

    private String name;
    private LocatorsType locatorsType;
    private String rawLocator;
    private String modifiedLocator;
    private By byLocator;

    //This constructor sets only 3 fields of object. The rest are prepared separately.
    OrderingPageLocators(final String name, final LocatorsType locatorsType, final String rawLocator) {
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
    public OrderingPageLocators modify(final String parameter) {
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
