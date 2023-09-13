package com.christian.mysql_rest_api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "class")
    private String classroom;

    @Column(name = "passed")
    private boolean passed;

    public Student() {}

    public Student(String name, String classroom, boolean passed) {
        this.name = name;
        this.classroom = classroom;
        this.passed = passed;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", classroom='" + classroom + '\'' +
                ", passed=" + passed +
                '}';
    }
}
