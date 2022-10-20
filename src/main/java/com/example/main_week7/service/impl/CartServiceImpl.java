package com.example.main_week7.service.impl;

import com.example.main_week7.model.Cart;
import com.example.main_week7.model.Product;
import com.example.main_week7.repository.ProductRepository;
import com.example.main_week7.service.CartService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CartServiceImpl implements CartService {


    ProductRepository productRepository;

    @Override
    public Product addToCart(Long prodId) {
        Product product = new Product();
//        Cart cart = new Cart();
//        List<Cart> item = new ArrayList<>();
//       item.add(cart.getProductName());
//       item.add(product.getProductCategory());
//       item.add(product.getPrice());
//        double total = price* (double)quantity;
//
       return product;
    }
}
