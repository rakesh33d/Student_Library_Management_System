package com.SpringProjects.Student_Library_Management_System.Controllers;

import com.SpringProjects.Student_Library_Management_System.DTOs.StudentUpdateMobileDto;
import com.SpringProjects.Student_Library_Management_System.Models.Student;
import com.SpringProjects.Student_Library_Management_System.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")

public class StudentController {
    @Autowired
    StudentService studentService;
    @PostMapping("/add")
    public String addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }
//    @GetMapping("/find-student-by-email")
//    public Student findByEmail(@RequestParam String email){
//       return studentService.findByEmail(email);
//    }
    @PutMapping("/update-mobNo")
    public String updateMobNo(@RequestBody StudentUpdateMobileDto studentUpdateMobileDto){
        return studentService.updateMobNo(studentUpdateMobileDto);
    }
}
