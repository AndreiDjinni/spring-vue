package com.example.employees.dto.general;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class DepartmentDto {
    /**
     * Department id
     */

    private Long id;

    /**
     * Department name
     */
    @NotBlank
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DepartmentDto)) return false;
        DepartmentDto that = (DepartmentDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "DepartmentDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
