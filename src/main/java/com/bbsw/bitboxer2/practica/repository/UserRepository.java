package com.bbsw.bitboxer2.practica.repository;

import com.bbsw.bitboxer2.practica.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT user FROM User user WHERE user.username = :username AND user.password = :password")
    User loginUser(@Param("username") String username, @Param("password") String password);
}
