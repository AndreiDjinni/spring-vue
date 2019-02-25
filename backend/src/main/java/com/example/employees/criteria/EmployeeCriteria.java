package com.example.employees.criteria;

import java.util.Objects;

public class EmployeeCriteria {

    private int page = 1;
    private int size;

    public EmployeeCriteria() {
    }

    public EmployeeCriteria(int page, int size) {
        this.page = page;
        this.size = size;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeCriteria that = (EmployeeCriteria) o;
        return page == that.page &&
                size == that.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, size);
    }

    @Override
    public String toString() {
        return "EmployeeCriteria{" +
                "page=" + page +
                ", size=" + size +
                '}';
    }
}
