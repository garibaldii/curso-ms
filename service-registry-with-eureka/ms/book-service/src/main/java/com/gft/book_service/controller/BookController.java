package com.gft.book_service.controller;

import com.gft.book_service.domain.BookEntity;
import com.gft.book_service.domain.dto.ExchangeDTO;
import com.gft.book_service.environment.InstanceInformationService;
import com.gft.book_service.proxy.ExchangeProxy;
import com.gft.book_service.repository.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/book-service")
public class BookController {

    private final BookRepository bookRepository;
    private final ExchangeProxy exchangeProxy;
    private final InstanceInformationService instanceInformationService;


    public BookController(BookRepository bookRepository, ExchangeProxy exchangeProxy, InstanceInformationService instanceInformationService) {
        this.bookRepository = bookRepository;
        this.exchangeProxy = exchangeProxy;
        this.instanceInformationService = instanceInformationService;
    }

    @PostMapping
    public BookEntity postBook(String title, String author, LocalDate launch_date, double price) {
        String port = instanceInformationService.retrieveServerPort();

        var book = new BookEntity(title, author, LocalDate.now(), price, port);

        return bookRepository.saveAndFlush(book);
    }

    @GetMapping("/{id}/{currency}")
    public BookEntity findBookPrice(@PathVariable Long id, @PathVariable String currency) {
        var book = bookRepository.findById(id).orElseThrow(() ->  new RuntimeException("Book not found"));

        var exchange = exchangeProxy.getExchange(book.getPrice(), "USD", currency);

        book.setPrice(exchange.convertedValue());
        book.setEnvironment("BOOK PORT: " + instanceInformationService.retrieveServerPort() + ", EXCHANGE PORT: " + exchange.environment());

        return book;

    }


}


