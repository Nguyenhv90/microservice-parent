package com.hvn.employeesevrvice.query.controller;

import com.hvn.employeesevrvice.query.model.EmployeeResponseModel;
import com.hvn.employeesevrvice.query.queries.GetEmployeeQuery;
import com.hvn.employeesevrvice.query.queries.SearchEmployeesQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employees")
@RequiredArgsConstructor
public class EmployeeQueryController {
    private final QueryGateway queryGateway;

    @GetMapping("/{employeeId}")
    public EmployeeResponseModel getBookDetail(@PathVariable String employeeId) {
        GetEmployeeQuery getEmployeeQuery = new GetEmployeeQuery();
        getEmployeeQuery.setEmployeeId(employeeId);

        EmployeeResponseModel employeeResponseModel = queryGateway
                .query(getEmployeeQuery, ResponseTypes.instanceOf(EmployeeResponseModel.class))
                .join();
        return employeeResponseModel;
    }

    @GetMapping("/search")
    public List<EmployeeResponseModel> searchBook(@RequestBody SearchEmployeesQuery searchEmployeesQuery) {

        List<EmployeeResponseModel> employees = queryGateway
                .query(searchEmployeesQuery, ResponseTypes.multipleInstancesOf(EmployeeResponseModel.class))
                .join();
        return employees;
    }
}
