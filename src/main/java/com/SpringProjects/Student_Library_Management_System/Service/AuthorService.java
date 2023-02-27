package com.SpringProjects.Student_Library_Management_System.Service;

import com.SpringProjects.Student_Library_Management_System.DTOs.AuthorEntryDto;
import com.SpringProjects.Student_Library_Management_System.DTOs.AuthorResponseDto;
import com.SpringProjects.Student_Library_Management_System.DTOs.BookResponseDto;
import com.SpringProjects.Student_Library_Management_System.Models.Author;
import com.SpringProjects.Student_Library_Management_System.Models.Book;
import com.SpringProjects.Student_Library_Management_System.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;
    public String addAuthor(AuthorEntryDto authorEntryDto){
        //the object is of type dto but repository interacts only with author entity

        //convert authorEntryDto to author
        Author author = new Author();
        //set the required attributes
        author.setName(authorEntryDto.getName());
        author.setAge(authorEntryDto.getAge());
        author.setCountry(authorEntryDto.getCountry());
        author.setRating(authorEntryDto.getRating());

        //now save the author
        authorRepository.save(author);
        return "Author added successfully";
    }
    public AuthorResponseDto getAuthor(int authorId){
        Author author = authorRepository.findById(authorId).get();

        //create response DTO
        AuthorResponseDto authorResponseDto = new AuthorResponseDto();
        // set its attributes
        //List<Book>--> List<BookResponseDto>
        List<Book>booksList= author.getBooksWritten();
         List<BookResponseDto>booksWrittenDto = new ArrayList<>();

         for(Book b:booksList){
             BookResponseDto bookResponseDto = new BookResponseDto();
             bookResponseDto.setGenre(b.getGenre());
             bookResponseDto.setName(b.getName());
             bookResponseDto.setPages(b.getPages());

             booksWrittenDto.add(bookResponseDto);
         }

         //set attributes for authorResponseDto
         authorResponseDto.setBooksWritten(booksWrittenDto);
         authorResponseDto.setAge(author.getAge());
         authorResponseDto.setName(author.getName());
         authorResponseDto.setRating(author.getRating());


         return authorResponseDto;
    }
}
