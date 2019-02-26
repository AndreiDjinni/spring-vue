package com.example.employees.service;

import com.example.employees.criteria.EmployeeCriteria;
import com.example.employees.dto.general.EmployeeDto;
import com.example.employees.dto.general.wrapper.EmployeeDtoWrapper;
import com.example.employees.entity.Employee;
import com.example.employees.exception.exceptions.ResourceNotFoundException;
import com.example.employees.repository.EmployeesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeesRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeesRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDtoWrapper getAll(EmployeeCriteria criteria) {

        LOG.debug("Search employees...");

        Page<Employee> employees;

        PageRequest pageRequest = PageRequest.of(
                criteria.getPage() - 1,
                criteria.getSize(),
                new Sort(Sort.Direction.DESC, "updated"));

        if (criteria.getSearch() != null) {

            employees = employeeRepository.findByFirstNameStartingWith(criteria.getSearch(), pageRequest);
        }
        else {

            employees = employeeRepository.findAll(pageRequest);
        }

        LOG.info(String.format("Founded [ %s ] employees", employees.getSize()));

        return EmployeeDtoWrapper.build(employees);
    }

    public EmployeeDto get(Long id) {

        LOG.debug(String.format("Search employee by id [ %s ] ...", id));

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));

        LOG.info(String.format("Founded employee by id [ %s ]", id));

        return EmployeeDto.build(employee);
    }

    public void add(EmployeeDto dto) {

        LOG.debug("Create employee...");

        dto.setId(null);

        Employee employee = employeeRepository.save(Employee.build(dto));

        LOG.info(String.format("Created employee [ %s ]", employee.toString()));
    }

    public void update(EmployeeDto dto) {

        LOG.debug(String.format("Updating employee by id [ %s ] ...", dto.getId()));

        Employee employee = employeeRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", dto.getId()));

        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setSalary(dto.getSalary());
        employee.setActive(dto.getActive());
        employee.setDepartmentId(dto.getDepartmentId());

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
