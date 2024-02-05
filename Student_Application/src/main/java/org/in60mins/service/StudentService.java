package org.in60mins.service;

import org.in60mins.dto.StudentDto;

import java.util.List;

public interface StudentService {

    StudentDto createStudent(StudentDto studentDto);
    List<StudentDto> getAll();
    StudentDto findById(Long id);
    StudentDto update(Long id,StudentDto studentDto);
    void deleteStudent(Long id);
}
