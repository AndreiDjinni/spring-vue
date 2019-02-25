package com.example.employees.service;

import com.example.employees.dto.general.DepartmentDto;
import com.example.employees.repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    private ModelMapper mapper = new ModelMapper();

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<DepartmentDto> getAll() {

        return departmentRepository.findAll().stream()
                .map(department -> mapper.map(department, DepartmentDto.class))
                .collect(Collectors.toList());
    }
}
