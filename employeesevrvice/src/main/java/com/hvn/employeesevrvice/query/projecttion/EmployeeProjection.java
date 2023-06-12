package com.hvn.employeesevrvice.query.projecttion;

import com.hvn.employeesevrvice.command.data.Employee;
import com.hvn.employeesevrvice.command.data.EmployeeRepository;
import com.hvn.employeesevrvice.query.model.EmployeeResponseModel;
import com.hvn.employeesevrvice.query.queries.GetEmployeeQuery;
import com.hvn.employeesevrvice.query.queries.SearchEmployeesQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EmployeeProjection {
    private final EmployeeRepository employeeRepository;

    @QueryHandler
    public EmployeeResponseModel handle(GetEmployeeQuery getEmployeeQuery) {
        EmployeeResponseModel model = new EmployeeResponseModel();
        Employee employee = employeeRepository.getEmployeeByEmployeeId(getEmployeeQuery.getEmployeeId());
        BeanUtils.copyProperties(employee, model);
        return model;
    }

    @QueryHandler
    public List<EmployeeResponseModel> handle(SearchEmployeesQuery query) {
        List<EmployeeResponseModel> modelList = new ArrayList<>();
        List<Employee> employees = employeeRepository.findAll();

        employees.stream().forEach(s -> {
            EmployeeResponseModel model = new EmployeeResponseModel();
            BeanUtils.copyProperties(s, model);
            modelList.add(model);
        });

        return modelList;
    }

}
