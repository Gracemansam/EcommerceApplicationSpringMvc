package com.example.main_week7.repository;

import com.example.main_week7.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users WHERE email = ?1" , nativeQuery = true)
    Optional<User> findUserByEmail(String email);


}
