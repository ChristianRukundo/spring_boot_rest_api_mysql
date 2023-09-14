package com.christian.mysql_rest_api.service.impl;

import com.christian.mysql_rest_api.model.Student;
import com.christian.mysql_rest_api.repository.StudentRepository;
import com.christian.mysql_rest_api.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<Student>  getAllStudents(String classroom){

            List<Student> students = new ArrayList<Student>();
            if (classroom == null){
                students.addAll(studentRepository.findAll());
            }
            else {
                students.addAll(studentRepository.findByClassroom(classroom));
            }
                return  students;
    };

   public Student  createStudent(Student student){

   };

    public Student getStudentById (long id){

    };
    public Student updateStudentById (long id, Student student){

    };

    void deleteStudentById (long id);
    void deleteAllStudents ();

    List<Student> findByPassed ();


}
