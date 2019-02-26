package com.example.employees.controller;

import com.example.employees.criteria.EmployeeCriteria;
import com.example.employees.dto.general.EmployeeDto;
import com.example.employees.dto.general.wrapper.EmployeeDtoWrapper;
import com.example.employees.service.DepartmentService;
import com.example.employees.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
public class EmployeeController {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @PostMapping("/employees")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity getAll(@RequestBody EmployeeCriteria criteria) {

        LOG.info("GET [ /employees ]");

        EmployeeDtoWrapper employees = employeeService.getAll(criteria);

        return ResponseEntity.ok(employees);
    }

    @PostMapping("/employee/add")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity add(@Valid @RequestBody EmployeeDto model) {

        LOG.info("POST [ /employee/add ]");

        employeeService.add(model);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/employee/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity get(@PathVariable("id") Long id) {

        LOG.info(String.format("GET [ /employees/%s]", id));

        return ResponseEntity.ok(employeeService.get(id));
    }

    @PutMapping("/employee/update")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity update(@Valid @RequestBody EmployeeDto model) {

        LOG.info("PUT [ /employee/update ]");

        employeeService.update(model);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/employee/delete/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity delete(@PathVariable("id") Long id) {

        LOG.info("DELETE [ /employee/delete ]");

        employeeService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/departments")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity getAllDepartments() {

        LOG.info("GET [ /departments ]");

        return ResponseEntity.ok(departmentService.getAll());
    }
}
