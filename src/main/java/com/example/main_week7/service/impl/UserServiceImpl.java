package com.example.main_week7.service.impl;

import com.example.main_week7.exception.UserNotFoundException;
import com.example.main_week7.dao.UserRegistrationDao;
import com.example.main_week7.model.Product;
import com.example.main_week7.model.Roles;
import com.example.main_week7.model.User;
import com.example.main_week7.repository.ProductRepository;
import com.example.main_week7.repository.UserRepository;
import com.example.main_week7.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    private final UserRegistrationDao userRegistrationDao;

    private final  ProductRepository productRepository;

    HashMap<Long , Product> tempCart = new HashMap<>();


    public UserServiceImpl(UserRepository userRepository, UserRegistrationDao userRegistrationDao, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.userRegistrationDao = userRegistrationDao;
        this.productRepository = productRepository;
    }

    @Override
    public List<User> getAllCustomer() {
        return userRepository.findAll();
    }

    @Override
    public User saveCustomer(UserRegistrationDao customerRegistrationDao) {
        return userRepository.save(customerDaoToEntity(customerRegistrationDao));
    }

    @Override
    public User saveAdmin(UserRegistrationDao adminRegistrationDao) {
        return userRepository.save(adminDaoToEntity(adminRegistrationDao));
    }


    @Override
    public boolean loginAdmin(String email, String password) throws UserNotFoundException {
        boolean isLoggedIn = false;
        var admin = userRepository.findUserByEmail(email).orElseThrow(()-> new UserNotFoundException(email));
        if (admin.getPassword().equalsIgnoreCase(password)){
            isLoggedIn = true;
        }else {
            throw new UserNotFoundException(email);
        }
        return isLoggedIn;
    }

    @Override
    public User findByEmail(String email) {
        User user = userRepository.findUserByEmail(email).orElseThrow(()-> new UserNotFoundException("This User was not found"));
        return  user;
    }

    public User customerDaoToEntity(UserRegistrationDao userRegistrationDao){
            User customer = new User();
            customer.setFirstName(userRegistrationDao.getFirstName());
            customer.setLastName(userRegistrationDao.getLastName());
            customer.setEmail(userRegistrationDao.getEmail());
            customer.setUsername(userRegistrationDao.getUsername());
            customer.setPassword(userRegistrationDao.getPassword());
            customer.setRoles(Roles.CUSTOMER);
            return customer;
        }


        public User adminDaoToEntity(UserRegistrationDao userRegistrationDao){
            User admin = new User();
            admin.setFirstName(userRegistrationDao.getFirstName());
            admin.setLastName(userRegistrationDao.getLastName());
            admin.setEmail(userRegistrationDao.getEmail());
            admin.setUsername(userRegistrationDao.getUsername());
            admin.setPassword(userRegistrationDao.getPassword());
            admin.setRoles(Roles.ADMIN);
            return admin;
        }
    @Override
    public String userSignIn(String email, String password) {
        String message = "";
        User user = findByEmail(email);

        if (user.getPassword().equals(password)){
            if (user.getRoles() == Roles.ADMIN){
                message = "admin";
            } else if (user.getRoles() == Roles.CUSTOMER) {
                message = "customer";
            }
        }else{
            message = "Incorrect Password";
        }

        return message;
    }

    @Override
    public HashMap<Long, Product> addToCart(long product_id, long user_id) {
        Product product = productRepository.findById(product_id).get();
        System.out.println(" product add to cart "+ product);
        //product.setCartQty(1);
        User user = userRepository.findById(user_id).get();
        System.out.println("customer adding the product" + user);

        if(tempCart.containsKey(product_id)){
            Product duplicateProduct = tempCart.get(product_id);
            duplicateProduct.setCartQty(duplicateProduct.getCartQty() + 1);
        }else {
            tempCart.put(product_id, product);
        }
        user.setCart(tempCart);

        return user.getCart();

    }
    public boolean removeFromCart(long productId, HashMap<Long, Product> cart){
        boolean isRemoved = false;
        if (cart.containsKey(productId)){
            cart.remove(productId);
            isRemoved = true;
        }
        return isRemoved;
    }

}


