package com.christian.mysql_rest_api.controller;

import com.christian.mysql_rest_api.model.Student;
import com.christian.mysql_rest_api.repository.StudentRepository;
import com.christian.mysql_rest_api.service.StudentService;
import com.christian.mysql_rest_api.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("api/v1")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentServiceImpl studentServiceImpl;



    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents (@RequestParam(required = false) String classroom) {
        try {
            List<Student> students = studentServiceImpl.getAllStudents(classroom);
            if (students.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            else {
                return new ResponseEntity<>(students, HttpStatus.OK);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("students")
    public ResponseEntity<Student> createStudent (@RequestBody Student student) {
        try {
            Student _student = studentServiceImpl.createStudent(student);
            return new ResponseEntity<>(_student, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("students/{id}")
    public ResponseEntity<Student> getStudentById (@PathVariable("id") long id) {
        try {
            Student student = studentServiceImpl.getStudentById(id);
            if (student == null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else {
                return new ResponseEntity<>(student, HttpStatus.OK);
            }
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("students/{id}")
    public ResponseEntity<Student> updateStudentsById (@PathVariable("id") long id, @RequestBody Student student) {
        try {
            Student studentData = studentServiceImpl.updateStudentById(id, student);
            if(studentData == null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else  {
              return new ResponseEntity<>(studentData, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("students/{id}")
    public ResponseEntity<HttpStatus> deleteStudent (@PathVariable("id") long id) {
        try {
            studentServiceImpl.deleteStudentById(id);
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("students")
    public  ResponseEntity<HttpStatus> deleteAllStudents (){
        try {
            studentServiceImpl.deleteAllStudents();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("students/passed")
    public ResponseEntity<List<Student>> findByPassed () {
        try {
            List<Student> students =studentServiceImpl.findByPassed();
            if(students.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(students, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
