package com.hvn.employeesevrvice.command.controller;

import com.hvn.employeesevrvice.command.command.CreateEmployeeCommand;
import com.hvn.employeesevrvice.command.command.DeleteEmployeeCommand;
import com.hvn.employeesevrvice.command.command.UpdateEmployeeCommand;
import com.hvn.employeesevrvice.command.model.EmployeeRequestModel;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeCommandController {
    public final CommandGateway commandGateway;

    @PostMapping("/add")
    public String addEmployee(@RequestBody EmployeeRequestModel model) {
        CreateEmployeeCommand employeeCommand = new CreateEmployeeCommand(UUID.randomUUID().toString(), model.getFirstName(), model.getLastName(), model.getKin(), false);
        commandGateway.sendAndWait(employeeCommand);
        return "added employee";
    }

    @PostMapping("/update")
    public String updateEmployee(@RequestBody EmployeeRequestModel model) {
        UpdateEmployeeCommand employeeCommand = new UpdateEmployeeCommand(model.getEmployeeId(), model.getFirstName(), model.getLastName(), model.getKin(), model.getIsDisciplined());
        commandGateway.sendAndWait(employeeCommand);
        return "updated employee";
    }

    @DeleteMapping ("/delete/{employeeId}")
    public String deleteEmployee(@PathVariable String employeeId) {
        DeleteEmployeeCommand employeeCommand = new DeleteEmployeeCommand(employeeId);
        commandGateway.sendAndWait(employeeCommand);
        return "deleted employee";
    }
}
