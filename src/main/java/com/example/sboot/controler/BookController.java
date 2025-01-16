package com.example.sboot.controler;

import com.example.sboot.entity.Book;
import com.example.sboot.entity.Customer;
import com.example.sboot.service.BookService;
import com.example.sboot.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/register")
    public String register(){
        Book c=new Book();
        c.setAuthor("cocom");
        c.setPage(100);
        c.setPrice(200);
        c.setTitle("title");

        Book cus= bookService.register(c);
        return "redirect:/";
    }
}
