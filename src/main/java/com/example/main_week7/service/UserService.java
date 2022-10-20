package com.example.main_week7.service;

import com.example.main_week7.dao.UserLoginDao;
import com.example.main_week7.dao.UserRegistrationDao;
import com.example.main_week7.exception.UserNotFoundException;
import com.example.main_week7.model.Product;
import com.example.main_week7.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

public interface UserService {
    public List<User> getAllCustomer();

    public User saveCustomer(UserRegistrationDao customerRegistrationDao);

    public User saveAdmin(UserRegistrationDao adminRegistrationDao);

    boolean loginAdmin(String email, String password) throws UserNotFoundException;

    public User findByEmail(String email);

    String userSignIn(String email , String password);

    HashMap<Long , Product> addToCart(long product_id , long customer_id);
    boolean removeFromCart(long productId, HashMap<Long, Product> cart);

}
