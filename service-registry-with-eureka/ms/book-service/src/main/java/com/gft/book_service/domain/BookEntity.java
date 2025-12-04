package com.gft.book_service.domain;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity(name = "book")
public class BookEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

    private String author;

    private LocalDate launch_date;

    private double price;

    @Transient
    private String environment;


    public BookEntity(String title, String author, LocalDate launch_date, double price, String environment) {
        this.title = title;
        this.author = author;
        this.launch_date = launch_date;
        this.price = price;
        this.environment = environment;
    }
}
