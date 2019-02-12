package com.example.employees.controller;

import com.example.employees.model.EmployeeModel;
import com.example.employees.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity getAll() {

        LOG.info("GET [ /employees ]");

        return internalGetAll();
    }

    @PostMapping("/employee/add")
    public ResponseEntity add(@RequestBody EmployeeModel model) {

        LOG.info("POST [ /employee/add ]");

        employeeService.add(model);

        return internalGetAll();
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {

        LOG.info(String.format("GET [ /employees/%s]", id));

        return ResponseEntity.ok(employeeService.get(id));
    }

    @PutMapping("/employee/update")
    public ResponseEntity update(@RequestBody EmployeeModel model) {

        LOG.info("PUT [ /employee/update ]");

        employeeService.update(model);

        return internalGetAll();
    }

    @DeleteMapping("/employee/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {

        LOG.info("DELETE [ /employee/delete ]");

        employeeService.delete(id);

        return internalGetAll();
    }


    private ResponseEntity internalGetAll() {

        List<EmployeeModel> employees = employeeService.getAll();

        return ResponseEntity.ok(employees);
    }
}
