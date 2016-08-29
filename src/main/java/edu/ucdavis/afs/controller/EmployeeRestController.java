package edu.ucdavis.afs.controller;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "api/employee")
public class EmployeeRestController {
    // Implemented Restful API request handling methods per requirements
	
	private final Employee employee;
	
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<?> add(@PathVariable Long id, @RequestBody Employee input) {
		validateID(id);
		return this.getEmployeeById(id)
				.map(employee -> {
					Employee employee = employeeDAOImpl.saveEmployee(new Employee(employee,
							input.uri, input.description));

					HttpHeaders httpHeaders = new HttpHeaders();
					httpHeaders.setLocation(ServletUriComponentsBuilder
							.fromCurrentRequest().path("/{id}")
							.buildAndExpand(result.getId()).toUri());
					return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
				}).get();

	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	Employee getEmployeeById(@PathVariable Long id, @PathVariable Long employeeId) {
		this.validateID(id);
		return this.employeeDAOImpl.getEmployeeById(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	Collection<Bookmark> readBookmarks(@PathVariable Long id) {
		this.validateID(id);
		return this.employeeDAOImpl.findEmployees(id);
	}

	@Autowired
	EmployeeRestController(EmployeeDAOImpl employeeDAOImpl,
			ProjectDAOImpl projectDAOImpl) {
		this.employeeDAOImpl = employeeDAOImpl;
		this.projectDAOImpl = projectDAOImpl;
	}
	
	private void validateID(String id) {
		this.getEmployeeById(id).orElseThrow(
				() -> new EmployeeNotFoundException(id));
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	class EmployeeNotFoundException extends RuntimeException {

	public EmployeeNotFoundException(String id) {
		super("could not find employee '" + id + "'.");
	}

	
}

