package com.sha.springbootbooksat333.security;

import com.sha.springbootbooksat333.model.User;
import com.sha.springbootbooksat333.service.IUserService;
import com.sha.springbootbooksat333.service.UserPrincipal;
import com.sha.springbootbooksat333.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {  //Kulanıcı ayrıntıları


   @Autowired
    private IUserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.findByUsername(username)      //userServicteki bulma fonk kullanıyoruz username ile buluruz sonra
                .orElseThrow(() -> new UsernameNotFoundException(username));   //null ise hata gönder

        Set<GrantedAuthority> authorities = Set.of(SecurityUtils.convertToAuthority(user.getRole().name()));//Birden fazla role olabilir

        return  UserPrincipal.builder()
                .user(user).id(user.getId())
                .username(username)
                .password(user.getPassword())
                .authorities(authorities)
                .build();








    }
}
