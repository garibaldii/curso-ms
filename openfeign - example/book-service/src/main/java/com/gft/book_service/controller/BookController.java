package com.gft.book_service.controller;

import com.gft.book_service.domain.BookEntity;
import com.gft.book_service.domain.dto.ExchangeDTO;
import com.gft.book_service.proxy.ExchangeProxy;
import com.gft.book_service.repository.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookRepository bookRepository;
    private final ExchangeProxy exchangeProxy;


    public BookController(BookRepository bookRepository, ExchangeProxy exchangeProxy) {
        this.bookRepository = bookRepository;
        this.exchangeProxy = exchangeProxy;
    }

    @PostMapping
    public BookEntity postBook(String title, String author, LocalDate launch_date, double price) {
        var book = new BookEntity(title, author, LocalDate.now(), price);

        return bookRepository.saveAndFlush(book);
    }

    @GetMapping("/{id}/{currency}")
    public BookEntity findBookPrice(@PathVariable Long id, @PathVariable String currency) {
        var book = bookRepository.findById(id).orElseThrow(() ->  new RuntimeException("Book not found"));

        var exchange = exchangeProxy.getExchange(book.getPrice(), "USD", currency);

        book.setPrice(exchange.convertedValue());

        return book;

    }


}


