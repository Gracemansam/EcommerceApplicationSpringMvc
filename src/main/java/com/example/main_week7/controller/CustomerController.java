package com.example.main_week7.controller;

import com.example.main_week7.dao.UserRegistrationDao;
import com.example.main_week7.model.Product;
import com.example.main_week7.model.User;
import com.example.main_week7.service.ProductService;
import com.example.main_week7.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
@Controller
public class CustomerController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService service;

    @GetMapping("")
    public String viewHomePage(Model model) {
        List<Product> displayProducts = service.getAll();
        model.addAttribute("displayProducts", displayProducts);
        return "Customer/index";
    }

    @GetMapping("/register")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());
        return "Customer/login";
    }


    @PostMapping("/process_register")
    public String processRegister(UserRegistrationDao userRegistrationDao) {
        userService.saveCustomer(userRegistrationDao);
        return "Customer/signUpSuccess";
    }

    @GetMapping("/fill-form")
    public String showSignInForm(Model model) {
        model.addAttribute("user", new User());
        return "Customer/signUp";
    }


    @PostMapping("/login2")
    public String Signup(@ModelAttribute UserRegistrationDao user, HttpSession session) {
        String message = userService.userSignIn(user.getEmail(), user.getPassword());
        User user1 = userService.findByEmail(user.getEmail());
        if(message.equals("customer")){
            session.setAttribute("email" , user.getEmail());
            session.setAttribute("id" , user1.getId());
            return "redirect:/displayPage";

        } else if (message.equals("admin")) {
            session.setAttribute("email" , user.getEmail());
            return "redirect:/admin/dashboard";


        }else{
            return "Customer/login";
        }
    }

    @GetMapping(value = "/cartPage")
    public String cartDisplay(Model model, HttpSession session){
        model.addAttribute("cartList" , session.getAttribute("cart"));
        return "Customer/cart";
    }


    @GetMapping("/displayPage")
    public String display(Model model){
        model.addAttribute("displayProducts",service.getAll());
        return "Customer/displayPage";
    }

    @PostMapping("/deleteCart")
    public String deleteCart(@RequestParam String product_id ,  HttpSession session){
        userService.removeFromCart(Long.parseLong(product_id) , (HashMap<Long, Product>) session.getAttribute("cart"));
        return "redirect:/cartPage";
    }



    @PostMapping(value = "/addToCart")
    public String addToCart(@RequestParam String user_id, @RequestParam String product_id, HttpSession session){
        HashMap<Long , Product> cart = userService.addToCart(Long.parseLong(product_id) , Long.parseLong(user_id));
        session.setAttribute("cart" , cart);
        System.out.println(cart);
        return "redirect:/displayPage";
    }


    }


