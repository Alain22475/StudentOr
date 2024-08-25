package com.auca.studentorientation.controller;

import com.auca.studentorientation.model.Student;
import com.auca.studentorientation.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/student/login")
    public String studentLogin() {
        return "student/student-login";
    }

    @GetMapping("/student/signup")
    public String studentSignup(Model model) {
        model.addAttribute("student", new Student());
        return "student/student-signup";
    }

    @GetMapping("/student/home")
    public String studentHome() {
        return "student/student-home"; // Thymeleaf template name (student-home.html)
    }

    @PostMapping("/student/login")
    public String processLogin(@RequestParam String phone, @RequestParam String password) {
        // This method is not needed if you're using Spring Security's form login
        // Spring Security will handle the authentication process
        return "redirect:/student/home";
    }

    @PostMapping("/student/signup")
    public String registerStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/student/login";
    }
}
