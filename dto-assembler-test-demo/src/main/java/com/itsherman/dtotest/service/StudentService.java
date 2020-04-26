package com.itsherman.dtotest.service;

import com.itsherman.dtotest.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService {

    void create(Student student);

    Student getById(Long id);

    Page<Student> findAllByPage(Pageable pageable);
}
