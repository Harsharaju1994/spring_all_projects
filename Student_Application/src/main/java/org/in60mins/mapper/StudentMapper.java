package org.in60mins.mapper;

import org.in60mins.dto.StudentDto;
import org.in60mins.model.Student;

public class StudentMapper {

    public static Student mapToStudent(StudentDto studentDto){
        Student student = new Student(
                studentDto.getId(),
                studentDto.getStudentName(),
                studentDto.getLocation()
        );
        return student;
    }

    public static StudentDto mapToStudentDto(Student student){
        StudentDto studentDto = new StudentDto(
                student.getId(),
                student.getStudentName(),
                student.getLocation()
        );
        return studentDto;
    }
}
