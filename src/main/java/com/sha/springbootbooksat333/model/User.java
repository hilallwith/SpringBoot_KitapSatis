package com.sha.springbootbooksat333.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
@Data
@Entity
@Table(name="users")//Tablo adı user olmaz
public class User {



    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;


    @Column(name="username",unique = true,nullable = false,length = 100)//benzersiz ve özellikleri olduğunu belirtiyoruz
    private String username;

    @Column(name="password",length = 100,nullable = false)
    private String password;

    @Column(name="name" ,nullable = false,length = 100)
    private String name;

    @Column(name="create_time",nullable = false)
    private LocalDateTime createTime;


    //role
    @Enumerated(EnumType.STRING)  //String olarak veritabanınsa saklıycaz
    @Column(name="role",nullable = false)
    private Role role;
    @Transient    //anlık işlemler için kullanıyoruz
    private String token;


}
