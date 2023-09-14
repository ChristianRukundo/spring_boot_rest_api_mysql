package com.christian.mysql_rest_api.service;


import com.christian.mysql_rest_api.model.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents(String classroom);
    Student createStudent(Student student);
    Student getStudentById (long id);
    Student updateStudentById (long id, Student student);

    void deleteStudentById (long id);
    void deleteAllStudents ();

    List<Student> findByPassed ();
}
