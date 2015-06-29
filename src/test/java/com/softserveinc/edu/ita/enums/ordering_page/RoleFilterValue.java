package com.softserveinc.edu.ita.enums.ordering_page;


public enum RoleFilterValue {
    NONE("None"),
    CADMINISTRATOR("Administrator"),
    CUSTOMER("Customer"),
    MERCHANDISER("Merchandiser"),
    SUPERVISOR("Supervisor");

    private String filterValue;

    RoleFilterValue(String filterValue) {
        this.filterValue = filterValue;
    }

    @Override
    public String toString() {
        return filterValue;
    }
}