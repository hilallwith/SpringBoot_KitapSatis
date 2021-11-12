package com.sha.springbootbooksat333.repository;

import com.sha.springbootbooksat333.model.Role;
import com.sha.springbootbooksat333.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User,Long> {  //Entity ve ıd tipi

    //Özel sorgu
    Optional<User> findByUsername(String username);
    @Modifying
    @Query("update User set role = :role where username = :username") //username gelen ile verideki aynıysa role al
    void updateUserRole(@Param("username") String username, @Param("role") Role role);

}
