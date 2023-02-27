package com.SpringProjects.Student_Library_Management_System.Service;

import com.SpringProjects.Student_Library_Management_System.DTOs.IssueBookRequestDto;
import com.SpringProjects.Student_Library_Management_System.Enums.CardStatus;
import com.SpringProjects.Student_Library_Management_System.Enums.TransactionStatus;
import com.SpringProjects.Student_Library_Management_System.Models.Book;
import com.SpringProjects.Student_Library_Management_System.Models.Card;
import com.SpringProjects.Student_Library_Management_System.Models.Transactions;
import com.SpringProjects.Student_Library_Management_System.Repositories.BookRepository;
import com.SpringProjects.Student_Library_Management_System.Repositories.CardRepository;
import com.SpringProjects.Student_Library_Management_System.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    BookRepository bookRepository;
    @Autowired
    CardRepository cardRepository;
    public String issueBook(IssueBookRequestDto issueBookRequestDto) throws Exception{
        int bookId = issueBookRequestDto.getBookId();
        int cardId = issueBookRequestDto.getCardId();
        //get the entities because issueBookReqDto can not be saved
        Book book = bookRepository.findById(bookId).get();
        Card card = cardRepository.findById(cardId).get();

        //final goal is to make a transaction entity , set its attributes and save it
        Transactions transaction = new Transactions();
        //set attributes
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setBook(book);
        transaction.setCard(card);
        transaction.setIssueOperation(true);
        transaction.setTransactionStatus(TransactionStatus.PENDING);

        //attribute left is success/failure
         //check for validations
         if(book == null || book.isIssued()==true){
             transaction.setTransactionStatus(TransactionStatus.FAILED);
             transactionRepository.save(transaction);
             throw new Exception("Book is not available");
         }
         if(card==null || card.getCardStatus()!= CardStatus.ACTIVATED){
             transaction.setTransactionStatus(TransactionStatus.FAILED);
             transactionRepository.save(transaction);
             throw new Exception("Invalid card");
         }
         // now we have reached to success transaction
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
         //now set the foreign key attributes
        book.setIssued(true);
        //b/w book and transaction : BiDirectional
        List<Transactions> transactionsListForBook= book.getListOfTransactions();
        transactionsListForBook.add(transaction);
        book.setListOfTransactions(transactionsListForBook);

        // for card also do the same
        //B/w book and card
        List<Book>issuedBooks = card.getIssuedBooks();
        issuedBooks.add(book);
        // /w card and transaction
        List<Transactions>transactionsListForCard = card.getTransactionsList();
        transactionsListForCard.add(transaction);
        card.setTransactionsList(transactionsListForCard);

        //save the parent
        cardRepository.save(card);
        return "book issued successfully";

    }

    public String getListOfTransactionOfBookAndCard(int bookId,int cardId){
        List<Transactions>txnList = transactionRepository.getTransactionList(bookId,cardId);
        String TxnId = txnList.get(0).getTransactionId();
        return TxnId;
    }
}
