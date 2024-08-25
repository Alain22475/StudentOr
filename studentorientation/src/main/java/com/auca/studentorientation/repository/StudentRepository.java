package com.auca.studentorientation.repository;

import com.auca.studentorientation.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByPhone(String phone);
}
