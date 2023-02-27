package com.SpringProjects.Student_Library_Management_System.Repositories;

import com.SpringProjects.Student_Library_Management_System.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {

//    Student findByEmail(String email);

}
