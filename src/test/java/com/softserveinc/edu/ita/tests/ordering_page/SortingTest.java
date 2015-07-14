package com.softserveinc.edu.ita.tests.ordering_page;

import com.softserveinc.edu.ita.domains.OrderingsTableRow;
import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.enums.ordering_page.OrdersTableColumns;
import com.softserveinc.edu.ita.pageobjects.HomePage;
import com.softserveinc.edu.ita.pageobjects.OrderingPage;
import com.softserveinc.edu.ita.pageobjects.UserInfoPage;
import com.softserveinc.edu.ita.utils.TestRunner;
import com.softserveinc.edu.ita.utils.DataProviders;
import org.testng.annotations.Test;

import java.util.*;
import java.util.function.Function;

/**
 * Class to test sorting actions
 * in 'Ordering' table of 'Ordering' page.
 */
public class SortingTest extends TestRunner {

    // test fail expected, not all columns can be sorted
    @Test(dataProviderClass = DataProviders.class, dataProvider = "getMerchandiserAndCustomer")
    public void testSorting(final User user) {
        final HomePage homePage = new HomePage(driver);
        final UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        final OrderingPage orderingPage = userInfoPage.clickOrderingTab();
        for (final OrdersTableColumns column : OrdersTableColumns.values()) {
            final List<OrderingsTableRow> baseTableFromView = orderingPage.getTableFromView();

            orderingPage.clickOrdersTableColumn(column);
            final List<OrderingsTableRow> tableFromViewSortedAsc = orderingPage.getTableFromView();

            orderingPage.clickOrdersTableColumn(column);
            final List<OrderingsTableRow> tableFromViewSortedDesc = orderingPage.getTableFromView();

            loggingSoftAssert.assertTrue(isTableIntact(baseTableFromView, tableFromViewSortedAsc),
                    String.format("Table isn't broken after ascendant sorting by '%s'.", column));
            loggingSoftAssert.assertTrue(isTableIntact(baseTableFromView, tableFromViewSortedDesc),
                    String.format("Table's isn't broken after descendant sorting by '%s'.", column));

            sortBaseTableBy(baseTableFromView, column);
            loggingSoftAssert.assertTrue(isTablesEqualsByColumn(baseTableFromView, tableFromViewSortedAsc, column),
                    String.format("Ascendant sorting by '%s' is working.", column));

            Collections.reverse(baseTableFromView);
            loggingSoftAssert.assertTrue(isTablesEqualsByColumn(baseTableFromView, tableFromViewSortedDesc, column),
                    String.format("Descendant sorting by '%s' is working.", column));

            orderingPage.clickLogOutButton();
            loggingSoftAssert.assertAll();
        }
    }

    /**
     * Interface with method used in method "isTablesEqualsByColumn".
     */
    private interface ComparisonCondition {
        String callMethod(OrderingsTableRow method);
    }

    /**
     * A method to verify equality of tables by given column.
     */
    public boolean isTablesEqualsByColumn(final List<OrderingsTableRow> sortedBaseTableFromView, final List<OrderingsTableRow> sortedTableByView, final OrdersTableColumns column) {
        final Map<OrdersTableColumns, ComparisonCondition> sortConditionsMap = new HashMap<>();
        sortConditionsMap.put(OrdersTableColumns.ORDER_NAME, OrderingsTableRow::getOrderName);
        sortConditionsMap.put(OrdersTableColumns.TOTAL_PRICE, OrderingsTableRow::getTotalPrice);
        sortConditionsMap.put(OrdersTableColumns.MAX_DISCOUNT, OrderingsTableRow::getMaxDiscount);
        sortConditionsMap.put(OrdersTableColumns.DELIVERY_DATE, OrderingsTableRow::getDeliveryDate);
        sortConditionsMap.put(OrdersTableColumns.STATUS, OrderingsTableRow::getStatus);
        sortConditionsMap.put(OrdersTableColumns.ASSIGNEE, OrderingsTableRow::getAssignee);
        sortConditionsMap.put(OrdersTableColumns.ROLE, OrderingsTableRow::getRole);

        final Iterator baseTableIterator = sortedBaseTableFromView.iterator();
        final Iterator tableIterator = sortedTableByView.iterator();
        int equalsCells = 0;
        while (baseTableIterator.hasNext() && sortConditionsMap.get(column).callMethod((OrderingsTableRow) baseTableIterator.next())
                .equals(sortConditionsMap.get(column).callMethod((OrderingsTableRow) tableIterator.next()))) {
            equalsCells++;
        }
        return equalsCells == sortedBaseTableFromView.size();
    }

    /**
     * A method to sort base table by given column through comparator.
     */
    public void sortBaseTableBy(final List<OrderingsTableRow> baseTableFromView, final OrdersTableColumns column) {
        final Map<OrdersTableColumns, Function<OrderingsTableRow, String>> sortConditionsMap = new HashMap<>();
        sortConditionsMap.put(OrdersTableColumns.ORDER_NAME, OrderingsTableRow::getOrderName);
        sortConditionsMap.put(OrdersTableColumns.TOTAL_PRICE, OrderingsTableRow::getTotalPrice);
        sortConditionsMap.put(OrdersTableColumns.MAX_DISCOUNT, OrderingsTableRow::getMaxDiscount);
        sortConditionsMap.put(OrdersTableColumns.DELIVERY_DATE, OrderingsTableRow::getDeliveryDate);
        sortConditionsMap.put(OrdersTableColumns.STATUS, OrderingsTableRow::getStatus);
        sortConditionsMap.put(OrdersTableColumns.ASSIGNEE, OrderingsTableRow::getAssignee);
        sortConditionsMap.put(OrdersTableColumns.ROLE, OrderingsTableRow::getRole);
        baseTableFromView.sort(Comparator.comparing(sortConditionsMap.get(column)));
    }

    /**
     * A method to verify integrity of table after sorting.
     * The method says "All of the rows are(true)/aren't(false) intact after sorting".
     */
    public boolean isTableIntact(final List<OrderingsTableRow> baseTable, final List<OrderingsTableRow> tableAfterSorting) {
        int intactRows = 0;
        final Iterator tableIterator = tableAfterSorting.iterator();
        while (tableIterator.hasNext() && baseTable.toString().contains(tableIterator.next().toString())) {
            intactRows++;
        }
        return intactRows == tableAfterSorting.size();
    }
}
