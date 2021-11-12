package com.sha.springbootbooksat333.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
@Data
@Entity
@Table(name="books")
public class Book {



    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;


    @Column(name="title", nullable = false,length = 100) //başlık
    private String title;

    @Column(name="description",nullable = false,length = 1000)  //açıklama
    private String Description;

    @Column(name="author",nullable = false,length = 100)        //Yazar
    private String author;

    @Column(name="price",nullable = false)                   //  fiyat
    private Double price;

    @Column(name="create_time",nullable = false)             //tarih
    private LocalDateTime createTime;
}
