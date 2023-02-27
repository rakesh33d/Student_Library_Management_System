package com.SpringProjects.Student_Library_Management_System.Models;

import com.SpringProjects.Student_Library_Management_System.Enums.Genre;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int pages;
    private boolean issued;

    public boolean isIssued() {
        return issued;
    }

    public void setIssued(boolean issued) {
        this.issued = issued;
    }

    @Enumerated(value = EnumType.STRING)
    private Genre genre;
    //book is child w.r.t author class
    //setting foreign key here
    @ManyToOne
    @JoinColumn
    private Author author;
    //book is child w.r.t card class
    @ManyToOne
    @JoinColumn
    private Card card;

    //book is parent w.r.t. transactions class
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    List<Transactions>ListOfTransactions = new ArrayList<>();

    public List<Transactions> getListOfTransactions() {
        return ListOfTransactions;
    }

    public void setListOfTransactions(List<Transactions> listOfTransactions) {
        ListOfTransactions = listOfTransactions;
    }

    public Book() {
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
