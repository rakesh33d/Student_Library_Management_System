package com.SpringProjects.Student_Library_Management_System.Service;

import com.SpringProjects.Student_Library_Management_System.DTOs.StudentUpdateMobileDto;
import com.SpringProjects.Student_Library_Management_System.Enums.CardStatus;
import com.SpringProjects.Student_Library_Management_System.Models.Card;
import com.SpringProjects.Student_Library_Management_System.Models.Student;
import com.SpringProjects.Student_Library_Management_System.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    public String addStudent(Student student){
      //before saving set the attributes
        //card will be auto generated when addStudent is called
        Card card = new Card();
        card.setCardStatus(CardStatus.ACTIVATED);
        card.setStudent(student);

        //set the attribute of child variable present in parent class
        student.setCard(card);

        //save student now
        studentRepository.save(student);

         return "Student and card added successfully";
    }
//    public Student findByEmail(String email){
//        return studentRepository.findByEmail(email);
//    }
    public String updateMobNo(StudentUpdateMobileDto studentUpdateMobileDto){
        //convert studentDto object to student entity
        Student originalStudent = studentRepository.findById(studentUpdateMobileDto.getId()).get();

        originalStudent.setMobNo(studentUpdateMobileDto.getMobNo());

        //now save student object
        studentRepository.save(originalStudent);

        return "student updated";
    }
}
