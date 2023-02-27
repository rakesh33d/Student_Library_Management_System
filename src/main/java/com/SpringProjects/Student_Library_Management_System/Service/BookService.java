package com.SpringProjects.Student_Library_Management_System.Service;

import com.SpringProjects.Student_Library_Management_System.DTOs.BookReqDto;
import com.SpringProjects.Student_Library_Management_System.Models.Author;
import com.SpringProjects.Student_Library_Management_System.Models.Book;
import com.SpringProjects.Student_Library_Management_System.Repositories.AuthorRepository;
import com.SpringProjects.Student_Library_Management_System.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    AuthorRepository authorRepository;
    public String addBook(Book book) {
        //convert bookReqDto to book entity
        int authorId = book.getAuthor().getId();


            Author author = authorRepository.findById(authorId).get();
           // Book book =new Book();
            //we have created this entity so that we can save it into DB
//            book.setGenre(bookReqDto.getGenre());
//            book.setPages(bookReqDto.getPages());
//            book.setName(bookReqDto.getName());
//            book.setIssued(false);

        //before saving set the attributes which are not set
        book.setAuthor(author);

        //update list of books

        List<Book> currentBooksWritten = author.getBooksWritten();
        currentBooksWritten.add(book);


        //now the book will be saved and author is also to be saved
        //update author
        authorRepository.save(author);//book is automatically saved


        return "Book added successfully";
    }
}
