package com.christian.mysql_rest_api.service.impl;

import com.christian.mysql_rest_api.model.Student;
import com.christian.mysql_rest_api.repository.StudentRepository;
import com.christian.mysql_rest_api.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
       Student _student;
       _student = studentRepository.save(
               new Student(
                       student.getName(),
                       student.getClassroom(),
                       student.isPassed()
               )
       );
       return  _student;
   };

    public Student getStudentById (long id){
        Optional<Student> student = studentRepository.findById(id);
        return student.orElse(null);


    };
    public Student updateStudentById (long id, Student student){
        Optional<Student> studentData = studentRepository.findById(id);
        if (studentData.isEmpty()){
            return null;
        }else  {
            Student  _student = studentData.get();
            _student.setName(student.getName());
            _student.setClassroom(student.getClassroom());
            _student.setPassed(student.isPassed());

            return studentRepository.save(_student);
        }
    };
  @Override
   public void deleteStudentById (long id){
        studentRepository.deleteById(id);
    };
   @Override
  public   void deleteAllStudents (){
      studentRepository.deleteAll();
  }

    @Override
    public List<Student> findByPassed() {
        return studentRepository.findByPassed(true);
    }

    ;
}