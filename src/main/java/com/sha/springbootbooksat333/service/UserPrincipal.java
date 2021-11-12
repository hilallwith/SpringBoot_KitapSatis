package com.sha.springbootbooksat333.service;

import com.sha.springbootbooksat333.model.Role;
import com.sha.springbootbooksat333.model.User;
import com.sha.springbootbooksat333.util.SecurityUtils;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
@Getter
@NoArgsConstructor  //boş constructor yap
@AllArgsConstructor //tüm fieldlerle constructor yap demek
@Builder
public class UserPrincipal implements UserDetails {

    private Long id;
    private String username;
    transient private String password; //mühürlenmiş yerlerde görünme
    transient private User user; //kullanıcı yalnızca oturum açma işlemi içindir, JWT'de kullanmayız
    private Set<GrantedAuthority> authorities;


    public static UserPrincipal createSuperUser()
    {
        Set<GrantedAuthority> authorities = Set.of(SecurityUtils.convertToAuthority(Role.SYSTEM_MANAGER.name()));

        return UserPrincipal.builder()
                .id(-1L)
                .username("system-administrator")
                .authorities(authorities)
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
