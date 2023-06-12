package com.hvn.employeesevrvice.command.event;

import com.hvn.employeesevrvice.command.data.Employee;
import com.hvn.employeesevrvice.command.data.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeEventsHandler {
    private final EmployeeRepository employeeRepository;

    @EventHandler
    public void on(EmployeeCreatedEvent event) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(event, employee);
        employeeRepository.save(employee);
    }
    @EventHandler
    public void on(EmployeeUpdatedEvent event) {
        Employee employee = employeeRepository.getEmployeeByEmployeeId(event.getEmployeeId());
        employee.setFirstName(event.getFirstName());
        employee.setLastName(event.getLastName());
        employee.setKin(event.getKin());
        employee.setIsDisciplined(event.getIsDisciplined());
        employeeRepository.save(employee);
    }

    @EventHandler
    public void on(EmployeeDeletedEvent event) {
        Employee employee = employeeRepository.getEmployeeByEmployeeId(event.getEmployeeId());
        employeeRepository.delete(employee);
    }


}
