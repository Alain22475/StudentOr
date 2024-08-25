package com.auca.studentorientation.service;

import com.auca.studentorientation.model.Student;
import com.auca.studentorientation.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;

@Service
public class StudentUserDetailsService implements UserDetailsService {

    @Autowired
    private StudentService studentService;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        Student student = studentService.findByPhone(phone);
        if (student == null) {
            throw new UsernameNotFoundException("Student not found");
        }
        return new org.springframework.security.core.userdetails.User(
                student.getPhone(),
                student.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_STUDENT"))
        );
    }
}
