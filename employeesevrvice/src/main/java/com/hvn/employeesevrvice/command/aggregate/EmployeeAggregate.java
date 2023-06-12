package com.hvn.employeesevrvice.command.aggregate;

import com.hvn.employeesevrvice.command.command.CreateEmployeeCommand;
import com.hvn.employeesevrvice.command.command.DeleteEmployeeCommand;
import com.hvn.employeesevrvice.command.command.UpdateEmployeeCommand;
import com.hvn.employeesevrvice.command.event.EmployeeCreatedEvent;
import com.hvn.employeesevrvice.command.event.EmployeeDeletedEvent;
import com.hvn.employeesevrvice.command.event.EmployeeUpdatedEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Getter @Setter
@NoArgsConstructor
@Aggregate
public class EmployeeAggregate {
    @AggregateIdentifier
    private String employeeId;
    private String firstName;
    private String lastName;
    private String kin;
    private Boolean isDisciplined;

    @CommandHandler
    public EmployeeAggregate(CreateEmployeeCommand createEmployeeCommand) {
        EmployeeCreatedEvent employeeCreatedEvent = new EmployeeCreatedEvent();
        BeanUtils.copyProperties(createEmployeeCommand, employeeCreatedEvent);
        AggregateLifecycle.apply(employeeCreatedEvent);
    }

    @EventSourcingHandler
    public void on(EmployeeCreatedEvent event) {
        this.employeeId = event.getEmployeeId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.kin = event.getKin();
        this.isDisciplined = event.getIsDisciplined();
    }

    @CommandHandler
    public void handle(UpdateEmployeeCommand updateEmployeeCommand) {
        EmployeeUpdatedEvent employeeupdatedEvent = new EmployeeUpdatedEvent();
        BeanUtils.copyProperties(updateEmployeeCommand, employeeupdatedEvent);
        AggregateLifecycle.apply(employeeupdatedEvent);
    }

    @EventSourcingHandler
    public void on(EmployeeUpdatedEvent event) {
        this.employeeId = event.getEmployeeId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.kin = event.getKin();
        this.isDisciplined = event.getIsDisciplined();
    }

    @CommandHandler
    public void handle(DeleteEmployeeCommand deleteEmployeeCommand) {
        EmployeeDeletedEvent employeeDeletedEvent = new EmployeeDeletedEvent();
        BeanUtils.copyProperties(deleteEmployeeCommand, employeeDeletedEvent);
        AggregateLifecycle.apply(employeeDeletedEvent);
    }

    @EventSourcingHandler
    public void on(EmployeeDeletedEvent event) {
        this.employeeId = event.getEmployeeId();
    }

}
