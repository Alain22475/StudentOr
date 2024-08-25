package com.auca.studentorientation.service;

import com.auca.studentorientation.model.Student;
import com.auca.studentorientation.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class StudentService implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Save student with encoded password
    public void saveStudent(Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        studentRepository.save(student);
    }

    // Find student by phone number
    public Student findByPhone(String phone) {
        return studentRepository.findByPhone(phone);
    }

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        Student student = findByPhone(phone);
        if (student == null) {
            throw new UsernameNotFoundException("Student not found with phone: " + phone);
        }
        return new org.springframework.security.core.userdetails.User(
                student.getPhone(),
                student.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_STUDENT"))
        );
    }
}