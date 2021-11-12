package com.sha.springbootbooksat333.service;

import com.sha.springbootbooksat333.model.User;

import java.util.Optional;

public interface IUserService {

    //Fonksiyonları belirliyoruz   class yapıp orda kullanıyoruz


    User saveUser(User user);         //user ı kaydet

    Optional<User> findByUsername(String username);           //ad a göre bul


    void makeAdmin(String username);                           //role belirleme
}
