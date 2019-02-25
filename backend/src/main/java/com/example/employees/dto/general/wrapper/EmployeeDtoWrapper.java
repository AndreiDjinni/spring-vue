package com.example.employees.dto.general.wrapper;

import com.example.employees.dto.general.EmployeeDto;
import com.example.employees.entity.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EmployeeDtoWrapper {

    private int totalPages;
    private int page;
    private int size;
    private List<EmployeeDto> employees;

    public EmployeeDtoWrapper() {
    }

    public EmployeeDtoWrapper(int totalPages, int page, int size, List<EmployeeDto> employees) {
        this.totalPages = totalPages;
        this.page = page;
        this.size = size;
        this.employees = employees;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<EmployeeDto> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDto> employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDtoWrapper that = (EmployeeDtoWrapper) o;
        return totalPages == that.totalPages &&
                page == that.page &&
                size == that.size &&
                Objects.equals(employees, that.employees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalPages, page, size, employees);
    }

    @Override
    public String toString() {
        return "EmployeeDtoWrapper{" +
                "totalPages=" + totalPages +
                ", page=" + page +
                ", size=" + size +
                ", employees=" + employees +
                '}';
    }

    public static EmployeeDtoWrapper build(Page<Employee> employees) {

        return new EmployeeDtoWrapper(
                employees.getTotalPages(),
                employees.getPageable().getPageNumber() + 1,
                employees.getPageable().getPageSize(),
                employees
                        .get()
                        .map(EmployeeDto::build)
                        .collect(Collectors.toList()));
    }
}
