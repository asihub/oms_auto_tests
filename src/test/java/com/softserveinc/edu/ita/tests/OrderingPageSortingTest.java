package com.softserveinc.edu.ita.tests;

import com.softserveinc.edu.ita.dao_jdbc.domains.Order;
import com.softserveinc.edu.ita.dao_jdbc.domains.User;
import com.softserveinc.edu.ita.dataproviders.DataProviders;
import com.softserveinc.edu.ita.enums.OrdersTable;
import com.softserveinc.edu.ita.locators.HomePageLocators;
import com.softserveinc.edu.ita.page_object.HomePage;
import com.softserveinc.edu.ita.page_object.OrderingPage;
import com.softserveinc.edu.ita.page_object.UserInfoPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

/**
 * This class is used to test sorting actions in 'Ordering' table of 'Ordering' page.
 */
public class OrderingPageSortingTest extends TestRunner {
    //Test sorting by Order Name
    @Test(dataProvider = "getMerchandisers", dataProviderClass = DataProviders.class)
    public void testOrderNameColumn(User user) {
        HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        OrderingPage orderingPage = userInfoPage.clickOrderingTab();

        List<Order> tableFromView = orderingPage.getTableFromView();
        tableFromView.sort(Comparator.comparing(Order::getOrderName));

        orderingPage.clickOrdersTableColumn(OrdersTable.ORDER_NAME.toString());
        List<Order> sortedTableByOrderNameAsc = orderingPage.getTableFromView();
        orderingPage.clickOrdersTableColumn(OrdersTable.ORDER_NAME.toString());
        List<Order> sortedTableByOrderNameDesc = orderingPage.getTableFromView();

        for (int i = 0; i < tableFromView.size(); i++) {
            Assert.assertTrue(sortedTableByOrderNameAsc.get(i).getOrderName().equals(tableFromView.get(i).getOrderName()));
        }
        for (int i = 0, j = tableFromView.size() - 1; i < tableFromView.size(); i++, j--) {
            Assert.assertTrue(sortedTableByOrderNameDesc.get(i).getOrderName().equals(tableFromView.get(j).getOrderName()));
        }
    }

    //Test sorting by Total Price
    @Test(dataProvider = "getMerchandisers", dataProviderClass = DataProviders.class)
    public void testTotalPriceColumn(User user) {
        HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        OrderingPage orderingPage = userInfoPage.clickOrderingTab();

        List<Order> tableFromView = orderingPage.getTableFromView();
        tableFromView.sort(Comparator.comparing(Order::getTotalPrice));

        orderingPage.clickOrdersTableColumn(OrdersTable.TOTAL_PRICE.toString());
        List<Order> sortedTableByTotalPriceAsc = orderingPage.getTableFromView();
        orderingPage.clickOrdersTableColumn(OrdersTable.TOTAL_PRICE.toString());
        List<Order> sortedTableByTotalPriceDesc = orderingPage.getTableFromView();

        for (int i = 0; i < tableFromView.size(); i++) {
            Assert.assertTrue(sortedTableByTotalPriceAsc.get(i).getTotalPrice().equals(tableFromView.get(i).getTotalPrice()));
        }
        for (int i = 0, j = tableFromView.size() - 1; i < tableFromView.size(); i++, j--) {
            Assert.assertTrue(sortedTableByTotalPriceDesc.get(i).getTotalPrice().equals(tableFromView.get(j).getTotalPrice()));
        }
    }

    //Test sorting by Max Discount
    @Test(dataProvider = "getMerchandisers", dataProviderClass = DataProviders.class)
    public void testMaxDiscountColumn(User user) {
        HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        OrderingPage orderingPage = userInfoPage.clickOrderingTab();

        List<Order> tableFromView = orderingPage.getTableFromView();
        tableFromView.sort(Comparator.comparing(Order::getMaxDiscount));

        orderingPage.clickOrdersTableColumn(OrdersTable.MAX_DISCOUNT.toString());
        List<Order> sortedTableByMaxDiscountAsc = orderingPage.getTableFromView();
        orderingPage.clickOrdersTableColumn(OrdersTable.MAX_DISCOUNT.toString());
        List<Order> sortedTableByMaxDiscountDesc = orderingPage.getTableFromView();

        for (int i = 0; i < tableFromView.size(); i++) {
            Assert.assertTrue(sortedTableByMaxDiscountAsc.get(i).getMaxDiscount().equals(tableFromView.get(i).getMaxDiscount()));
        }
        for (int i = 0, j = tableFromView.size() - 1; i < tableFromView.size(); i++, j--) {
            Assert.assertTrue(sortedTableByMaxDiscountDesc.get(i).getMaxDiscount().equals(tableFromView.get(j).getMaxDiscount()));
        }
    }

    //Test sorting by Delivery Date
    @Test(dataProvider = "getMerchandisers", dataProviderClass = DataProviders.class)
    public void testDeliveryDateColumn(User user) {
        HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        OrderingPage orderingPage = userInfoPage.clickOrderingTab();

        List<Order> tableFromView = orderingPage.getTableFromView();
        tableFromView.sort(Comparator.comparing(Order::getDeliveryDate));

        orderingPage.clickOrdersTableColumn(OrdersTable.DELIVERY_DATE.toString());
        List<Order> sortedTableByDeliveryDateAsc = orderingPage.getTableFromView();
        orderingPage.clickOrdersTableColumn(OrdersTable.DELIVERY_DATE.toString());
        List<Order> sortedTableByDeliveryDateDesc = orderingPage.getTableFromView();

        for (int i = 0; i < tableFromView.size(); i++) {
            Assert.assertTrue(sortedTableByDeliveryDateAsc.get(i).getDeliveryDate().equals(tableFromView.get(i).getDeliveryDate()));
        }
        for (int i = 0, j = tableFromView.size() - 1; i < tableFromView.size(); i++, j--) {
            Assert.assertTrue(sortedTableByDeliveryDateDesc.get(i).getDeliveryDate().equals(tableFromView.get(j).getDeliveryDate()));
        }
    }

    //Test sorting by Status
    @Test(dataProvider = "getMerchandisers", dataProviderClass = DataProviders.class)
    public void testStatusColumn(User user) {
        HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        OrderingPage orderingPage = userInfoPage.clickOrderingTab();

        List<Order> tableFromView = orderingPage.getTableFromView();
        tableFromView.sort(Comparator.comparing(Order::getStatus));

        orderingPage.clickOrdersTableColumn(OrdersTable.STATUS.toString());
        List<Order> sortedTableByStatusAsc = orderingPage.getTableFromView();
        orderingPage.clickOrdersTableColumn(OrdersTable.STATUS.toString());
        List<Order> sortedTableByStatusDesc = orderingPage.getTableFromView();

        for (int i = 0; i < tableFromView.size(); i++) {
            Assert.assertTrue(sortedTableByStatusAsc.get(i).getStatus().equals(tableFromView.get(i).getStatus()));
        }
        for (int i = 0, j = tableFromView.size() - 1; i < tableFromView.size(); i++, j--) {
            Assert.assertTrue(sortedTableByStatusDesc.get(i).getStatus().equals(tableFromView.get(j).getStatus()));
        }
    }

    //Test sorting by Assignee
    @Test(dataProvider = "getMerchandisers", dataProviderClass = DataProviders.class)
    public void testAssigneeColumn(User user) {
        HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        OrderingPage orderingPage = userInfoPage.clickOrderingTab();

        List<Order> tableFromView = orderingPage.getTableFromView();
        tableFromView.sort(Comparator.comparing(Order::getAssignee));

        orderingPage.clickOrdersTableColumn(OrdersTable.ASSIGNEE.toString());
        List<Order> sortedTableByAssigneeAsc = orderingPage.getTableFromView();
        orderingPage.clickOrdersTableColumn(OrdersTable.ASSIGNEE.toString());
        List<Order> sortedTableByAssigneeDesc = orderingPage.getTableFromView();

        for (int i = 0; i < tableFromView.size(); i++) {
            Assert.assertTrue(sortedTableByAssigneeAsc.get(i).getAssignee().equals(tableFromView.get(i).getAssignee()));
        }

        for (int i = 0, j = tableFromView.size() - 1; i < tableFromView.size(); i++, j--) {
            Assert.assertTrue(sortedTableByAssigneeDesc.get(i).getAssignee().equals(tableFromView.get(j).getAssignee()));
        }
    }

    //Test sorting by Role
    @Test(dataProvider = "getMerchandisers", dataProviderClass = DataProviders.class)
    public void testRoleColumn(User user) {
        HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        OrderingPage orderingPage = userInfoPage.clickOrderingTab();

        List<Order> tableFromView = orderingPage.getTableFromView();
        tableFromView.sort(Comparator.comparing(Order::getRole));

        orderingPage.clickOrdersTableColumn(OrdersTable.ROLE.toString());
        List<Order> sortedTableByRoleAsc = orderingPage.getTableFromView();
        orderingPage.clickOrdersTableColumn(OrdersTable.ROLE.toString());
        List<Order> sortedTableByRoleDesc = orderingPage.getTableFromView();

        for (int i = 0; i < tableFromView.size(); i++) {
            Assert.assertTrue(sortedTableByRoleAsc.get(i).getRole().equals(tableFromView.get(i).getRole()));
        }

        for (int i = 0, j = tableFromView.size() - 1; i < tableFromView.size(); i++, j--) {
            Assert.assertTrue(sortedTableByRoleDesc.get(i).getRole().equals(tableFromView.get(j).getRole()));
        }
    }

    @AfterMethod
    public void logOut() {
        driver.findElement(HomePageLocators.LOG_OUT_BUTTON).click();
    }

}
