package com.example.employees.service;

import com.example.employees.data.EmployeeRepository;
import com.example.employees.entity.Employee;
import com.example.employees.exception.exceptions.ResourceNotFoundException;
import com.example.employees.model.EmployeeModel;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public List<EmployeeModel> getAll() {

        LOG.debug("Search employees...");

        List<Employee> employees = employeeRepository.findAll();

        LOG.info(String.format("Founded [ %s ] employees", employees.size()));

        return employees.stream()
                .map( employee -> modelMapper.map(employee, EmployeeModel.class))
                .collect(Collectors.toList());
    }

    public EmployeeModel get(Long id) {

        LOG.debug(String.format("Search employee by id [ %s ] ...", id));

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));

        LOG.info(String.format("Founded employee by id [ %s ]", id));

        return modelMapper.map(employee, EmployeeModel.class);
    }

    public EmployeeModel add(EmployeeModel model) {

        LOG.debug("Create employee...");

        model.setId(null);

        Employee employee = employeeRepository.save(modelMapper.map(model, Employee.class));

        LOG.info(String.format("Created employee [ %s ]", employee.toString()));

        return modelMapper.map(employee, EmployeeModel.class);
    }

    public void update(EmployeeModel model) {

        LOG.debug(String.format("Updating employee by id [ %s ] ...", model.getId()));

        Employee employee = employeeRepository.findById(model.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", model.getId()));

        employee.setFirstName(model.getFirstName());
        employee.setLastName(model.getLastName());
        employee.setSalary(model.getSalary());

        employeeRepository.save(employee);

        LOG.info(String.format("Updated employee by id [ %s ]", employee.getId()));
    }

    public void delete(Long id) {

        LOG.debug(String.format("Deleting employee by id [ %s ] ...", id));

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));

        employeeRepository.delete(employee);

        LOG.info(String.format("Deleted employee by id [ %s ]", id));
    }
}
