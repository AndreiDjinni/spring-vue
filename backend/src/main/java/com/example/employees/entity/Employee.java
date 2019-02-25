package com.example.employees.entity;

import com.example.employees.dto.general.EmployeeDto;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Employee extends BaseEntity {

    /**
     * Employee first name
     */
    private String firstName;

    /**
     * Employee first name
     */
    private String lastName;

    /**
     * Is active
     */
    private Boolean active;

    /**
     * Employee salary
     */
    private Integer salary;

    @Basic
    @Column(name = "department_id", nullable = false)
    private Long departmentId;

    @ManyToOne
    @JoinColumn(name = "department_id", insertable = false, updatable = false)
    private Department department;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(getId(), employee.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public static Employee build(EmployeeDto dto) {
        Employee employee = new Employee();

        employee.setId(dto.getId());
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setActive(dto.getActive());
        employee.setSalary(dto.getSalary());
        employee.setDepartmentId(dto.getDepartmentId());

        return employee;
    }
}
