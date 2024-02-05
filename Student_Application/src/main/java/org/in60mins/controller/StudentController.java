package org.in60mins.controller;

import org.in60mins.dto.StudentDto;
import org.in60mins.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class StudentController {

    private StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto){
        return new ResponseEntity<>(service.createStudent(studentDto), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<StudentDto>> all(){
        List<StudentDto> getAll = service.getAll();
        return ResponseEntity.ok(getAll);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getId(@PathVariable Long id){
        StudentDto studentDto = service.findById(id);
        return ResponseEntity.ok(studentDto);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<StudentDto> update(@PathVariable Long id,
                                             @RequestBody StudentDto studentDto){
        StudentDto student1 = service.update(id,studentDto);
        return ResponseEntity.ok(student1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteId(@PathVariable Long id){
        service.deleteStudent(id);
        return ResponseEntity.ok(id+ " : deleted Successfully...");
    }
}
