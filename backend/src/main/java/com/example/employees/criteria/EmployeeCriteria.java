package com.example.employees.criteria;

import java.util.Objects;

public class EmployeeCriteria {

    private int page = 1;
    private int size;
    private String search;

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

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeCriteria)) return false;
        EmployeeCriteria that = (EmployeeCriteria) o;
        return page == that.page &&
                size == that.size &&
                Objects.equals(search, that.search);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, size, search);
    }

    @Override
    public String toString() {
        return "EmployeeCriteria{" +
                "page=" + page +
                ", size=" + size +
                ", search='" + search + '\'' +
                '}';
    }
}
