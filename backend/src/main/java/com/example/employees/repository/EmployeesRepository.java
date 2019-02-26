package com.example.employees.repository;

import com.example.employees.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeesRepository extends JpaRepository<Employee, Long> {

    Page<Employee> findByFirstNameStartingWith(String firstName, Pageable pageable);
}
