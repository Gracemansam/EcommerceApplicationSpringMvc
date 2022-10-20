package com.example.main_week7.repository;

import com.example.main_week7.model.Cart;
import com.example.main_week7.model.Product;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Product, Long> {

}
