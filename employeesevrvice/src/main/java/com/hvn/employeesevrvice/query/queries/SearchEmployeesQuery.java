package com.hvn.employeesevrvice.query.queries;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchEmployeesQuery {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String kin;
    private Boolean isDisciplined;
}
