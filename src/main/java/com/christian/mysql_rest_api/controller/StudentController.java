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
            Student _student = studentRepository.save(
                new Student(
                        student.getName(),
                        student.getClassroom(),
                        student.isPassed()
                )
            );

            return new ResponseEntity<>(_student, HttpStatus.OK);

        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("students/{id}")
    public ResponseEntity<Student> getStudentById (@PathVariable("id") long id) {
        try {
            Optional<Student> student = studentRepository.findById(id);
            if (student.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else {
                return new ResponseEntity<>(student.get(), HttpStatus.OK);
            }
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("students/{id}")
    public ResponseEntity<Student> updateStudentsById (@PathVariable("id") long id, @RequestBody Student student) {
        try {
            Optional<Student> studentData = studentRepository.findById(id);
            if(studentData.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else  {
              Student  _student = studentData.get();
              _student.setName(student.getName());
              _student.setClassroom(student.getClassroom());
              _student.setPassed(student.isPassed());

              return new ResponseEntity<>(studentRepository.save(_student), HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("students/{id}")
    public ResponseEntity<HttpStatus> deleteStudent (@PathVariable("id") long id) {
        try {
            studentRepository.deleteById(id);

            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("students")
    public  ResponseEntity<HttpStatus> deleteAllStudents (){
        try {
            studentRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("students/passed")
    public ResponseEntity<List<Student>> findByPassed () {
        try {
            List<Student> students = studentRepository.findByPassed(true);
            if(students.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(students, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
