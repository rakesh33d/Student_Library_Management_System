package com.SpringProjects.Student_Library_Management_System.Controllers;

import com.SpringProjects.Student_Library_Management_System.DTOs.IssueBookRequestDto;
import com.SpringProjects.Student_Library_Management_System.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    TransactionService transactionService;
    @PostMapping("issueBook")
    public String issueBook(@RequestBody IssueBookRequestDto issueBookRequestDto){
        try {
            return transactionService.issueBook(issueBookRequestDto);
        }catch (Exception e){
          return e.getMessage();
        }
    }
    @GetMapping("/txnList")
    public String getTxnId(@RequestParam int bookId,@RequestParam int cardId){
        return transactionService.getListOfTransactionOfBookAndCard(bookId,cardId);
    }

}
