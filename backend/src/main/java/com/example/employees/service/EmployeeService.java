package com.example.employees.service;

import com.example.employees.criteria.EmployeeCriteria;
import com.example.employees.dto.general.EmployeeDto;
import com.example.employees.dto.general.wrapper.EmployeeDtoWrapper;
import com.example.employees.repository.EmployeeRepository;
import com.example.employees.entity.Employee;
import com.example.employees.exception.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository employeeRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDtoWrapper getAll(EmployeeCriteria criteria) {

        LOG.debug("Search employees...");

        Page<Employee> employees = employeeRepository.findAll(
                PageRequest.of(
                        criteria.getPage() - 1,
                        criteria.getSize(),
                        new Sort(Sort.Direction.DESC, "updated"))
        );

        LOG.info(String.format("Founded [ %s ] employees", employees.getSize()));

        return EmployeeDtoWrapper.build(employees, modelMapper);
    }

    public EmployeeDto get(Long id) {

        LOG.debug(String.format("Search employee by id [ %s ] ...", id));

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));

        LOG.info(String.format("Founded employee by id [ %s ]", id));

        return modelMapper.map(employee, EmployeeDto.class);
    }

    public EmployeeDto add(EmployeeDto model) {

        LOG.debug("Create employee...");

        model.setId(null);

        Employee employee = employeeRepository.save(modelMapper.map(model, Employee.class));

        LOG.info(String.format("Created employee [ %s ]", employee.toString()));

        return modelMapper.map(employee, EmployeeDto.class);
    }

    public void update(EmployeeDto model) {

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
