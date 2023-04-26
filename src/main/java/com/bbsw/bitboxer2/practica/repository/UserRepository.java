package com.bbsw.bitboxer2.practica.repository;

import com.bbsw.bitboxer2.practica.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
