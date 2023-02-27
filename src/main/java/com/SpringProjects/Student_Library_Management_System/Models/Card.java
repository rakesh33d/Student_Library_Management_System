package com.SpringProjects.Student_Library_Management_System.Models;

import com.SpringProjects.Student_Library_Management_System.Enums.CardStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Card")

public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;//auto generated
    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;
    @OneToOne
    @JoinColumn
    private Student student;//this variable used in parent class while doing bidirectional mapping
    @CreationTimestamp//auto time stamp the time when an entry is created
    Date createdOn;
    @UpdateTimestamp//sets time when entry is updated
    Date updatedOn;

    //card is parent class for book class
    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL)
    private List<Book> issuedBooks= new ArrayList<>();

    public Card() {
    }

    public List<Book> getIssuedBooks() {
        return issuedBooks;
    }

    //connecting card class to transactions
    //bidirectional mapping
    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL)
   private List<Transactions>transactionsList = new ArrayList<>();

    public List<Transactions> getTransactionsList() {
        return transactionsList;
    }

    public void setTransactionsList(List<Transactions> transactionsList) {
        this.transactionsList = transactionsList;
    }

    public void setIssuedBooks(List<Book> issuedBooks) {
        this.issuedBooks = issuedBooks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
}
