package com.hvn.employeesevrvice;

import com.hvn.employeesevrvice.command.data.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EntityScan(basePackageClasses = {Employee.class}, basePackages = {
		"org.axonframework.eventsourcing.eventstore.jpa",
		"org.axonframework.modelling.saga.repository.jpa",
		"org.axonframework.eventhandling.tokenstore.jpa"}
)
public class EmployeesevrviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeesevrviceApplication.class, args);
	}

}
