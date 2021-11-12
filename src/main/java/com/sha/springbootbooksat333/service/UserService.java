package com.sha.springbootbooksat333.service;


import com.sha.springbootbooksat333.model.Role;
import com.sha.springbootbooksat333.model.User;
import com.sha.springbootbooksat333.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService implements  IUserService{

    @Autowired //field
    private IUserRepository userRepository;    //
    @Autowired
    private PasswordEncoder passwordEncoder;


//********************************************************************************************************
@Override
    public User saveUser(User user){                          //kullanıcı kaydetmek için kullanıyorum
        user.setPassword(passwordEncoder.encode(user.getPassword()));  //Şifresini şifreliyoruz
        user.setRole(Role.USER);                                       //rolünü user yapıyoruz
        user.setCreateTime(LocalDateTime.now());                       //Kayıt tarihini o anki zaman yapıyoruz

        return userRepository.save(user);                                //.save yöntemiyle kaydediyoruz
    }
//********************************************************************************************************
                       //kullanıcıyı bulma
    public Optional<User> findByUsername(String username){  //username göre kullanıcıyı buluyoruz

    return userRepository.findByUsername(username);
    }

    //*******************************************************************************
    @Override
    @Transactional  //Güncelleme işlemlerinde zorunlu kullanmak
    public void makeAdmin(String username)
    {
        userRepository.updateUserRole(username,Role.ADMIN); //username göre admin yap
    }
//***********************************************************************************











}
