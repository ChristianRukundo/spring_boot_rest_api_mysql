package com.christian.mysql_rest_api.repository;

import com.christian.mysql_rest_api.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByPassed (boolean passed);
    List<Student> findByClassroom (String classroom);
}
