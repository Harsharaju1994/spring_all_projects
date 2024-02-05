package org.in60mins.service.impl;

import org.in60mins.dto.StudentDto;
import org.in60mins.mapper.StudentMapper;
import org.in60mins.model.Student;
import org.in60mins.repository.StudentRepo;
import org.in60mins.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepo studentRepo;

    public StudentServiceImpl(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        Student student = StudentMapper.mapToStudent(studentDto);
        Student saveStudent = studentRepo.save(student);
        return StudentMapper.mapToStudentDto(saveStudent);
    }

    @Override
    public List<StudentDto> getAll() {
        List<Student> all = studentRepo.findAll();
        return all.stream().map((data)->StudentMapper.mapToStudentDto(data)).collect(Collectors.toList());
    }

    @Override
    public StudentDto findById(Long id) {
        Student student = studentRepo.findById(id).orElseThrow(()->new RuntimeException("Not Exist number... "+id));
        return StudentMapper.mapToStudentDto(student);
    }

    @Override
    public StudentDto update(Long id, StudentDto studentDto) {
        Student student = studentRepo.findById(id).orElseThrow(()->new RuntimeException("Not Exist number... "+id));
        student.setStudentName(studentDto.getStudentName());
        student.setLocation(studentDto.getLocation());
        Student updateStudent = studentRepo.save(student);
        return StudentMapper.mapToStudentDto(updateStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = studentRepo.findById(id).orElseThrow(()->new RuntimeException("Not Exist number... "+id));
        studentRepo.delete(student);
    }
}
