package com.example.employees.dto.general;

import com.example.employees.entity.Employee;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class EmployeeDto {

    /**
     * Unique identifier
     */
    private Long id;

    /**
     * Employee first name
     */
    @NotBlank
    private String firstName;

    /**
     * Employee first name
     */
    @NotBlank
    private String lastName;

    /**
     * Employee salary
     */
    @Min(value = 0L)
    private Integer salary;

    /**
     * Is active
     */
    @NotNull
    private Boolean active;

    /**
     * Employee department id
     */
    @Min(value = 1L)
    private Long departmentId;

    /**
     * Employee department name
     */
    private String departmentName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDto that = (EmployeeDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "EmployeeDto id=" + id;
    }

    public static EmployeeDto build(Employee employee) {

        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.id = employee.getId();
        employeeDto.firstName = employee.getFirstName();
        employeeDto.lastName = employee.getLastName();
        employeeDto.salary = employee.getSalary();
        employeeDto.active = employee.getActive();
        employeeDto.departmentId = employee.getDepartmentId();
        employeeDto.departmentName = employee.getDepartment().getName();

        return employeeDto;
    }
}
