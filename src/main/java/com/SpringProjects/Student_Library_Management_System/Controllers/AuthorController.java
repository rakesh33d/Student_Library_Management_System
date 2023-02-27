package com.SpringProjects.Student_Library_Management_System.Controllers;

import com.SpringProjects.Student_Library_Management_System.DTOs.AuthorEntryDto;
import com.SpringProjects.Student_Library_Management_System.DTOs.AuthorResponseDto;
import com.SpringProjects.Student_Library_Management_System.Models.Author;
import com.SpringProjects.Student_Library_Management_System.Models.Book;
import com.SpringProjects.Student_Library_Management_System.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @PostMapping("/add")
    public String addAuthor(@RequestBody AuthorEntryDto authorEntryDto){
        return authorService.addAuthor(authorEntryDto);
    }
    @GetMapping("/getAuthor")
    public AuthorResponseDto getAuthor(@RequestParam int authorId){
        return authorService.getAuthor(authorId);
    }
}
