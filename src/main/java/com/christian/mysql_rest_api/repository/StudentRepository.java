package com.christian.mysql_rest_api.repository;

import com.christian.mysql_rest_api.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByPassed (boolean passed);
    List<Student> findByClass (String classroom);
}
