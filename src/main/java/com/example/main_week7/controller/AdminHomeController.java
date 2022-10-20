package com.example.main_week7.controller;

import com.example.main_week7.dao.ProductDao;
import com.example.main_week7.dao.UserRegistrationDao;
import com.example.main_week7.model.Product;
import com.example.main_week7.model.User;
import com.example.main_week7.service.ProductService;
import com.example.main_week7.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminHomeController {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRegistrationDao userRegistrationDao;




    @GetMapping( value="/register")
    public String registerPage(Model model){
        model.addAttribute("user" , new UserRegistrationDao());
        return "Admin/register";
    }


    @PostMapping(value="/registerAdmin")
    public String registerAdmin(@ModelAttribute UserRegistrationDao admin ){
        userService.saveAdmin(admin);
        return "redirect:/admin/loginAdmin";
    }

    @GetMapping( value="/login")
    public String loginPage(Model model){
        model.addAttribute("loggedInAdmin" , new UserRegistrationDao());
        return "Admin/login";
    }

//    @PostMapping( value="/loginAdmin")
//    public String loginAdmin(@ModelAttribute User admin, Model model, HttpSession session){
//        boolean isLoggedIn = userService.loginAdmin(admin.getEmail() , admin.getPassword());
//        if (!isLoggedIn){
//            return "redirect:/admin/login";
//        }else {
//            session.setAttribute("email" , admin.getEmail());
//            return "redirect:/admin/dashboard";
//        }
//    }

    @GetMapping( value="/dashboard")
    public String dashboard(Model model , HttpSession session){
        List<Product> productList = productService.getAll();
        String email = (String) session.getAttribute("email");
        User admin = userService.findByEmail(email);
        System.out.println(admin);
        model.addAttribute("admin" , new User());
        model.addAttribute("products" , productList);
        model.addAttribute("id" , admin.getId());
        model.addAttribute("product" , new Product());

        if(session.getAttribute("email") == null){
            return "redirect:/admin/loginAdmin";
        }else {
            return "Admin/dashboard";
        }
    }

    @GetMapping(value = "/getProduct/{productId}")
    public String getProduct(@PathVariable(name="productId") String productId , Model model){
        Long id = Long.parseLong(productId);
        System.out.println(productService.findProductById(id));
        model.addAttribute("product" , productService.findProductById(id));
        return "Admin/product";
    }

    @GetMapping(value = "/editProduct/{productId}")
    public String editProduct(@PathVariable(name="productId") String productId , Model model){
        Long id = Long.parseLong(productId);
        // System.out.println(adminService.findProductById(id));
        model.addAttribute("product" , productService.findProductById(id));
        model.addAttribute("productField" , new Product());
        return "Admin/editProduct";
    }

    @PostMapping(value = "/editProduct")
    public String adminEditProduct(@ModelAttribute Product product , Model model){
        System.out.println(product);
        productService.updateProduct(product);
        return "redirect:/admin/dashboard";
    }



    @PostMapping(value = "/addProduct")
    public String addProduct(@ModelAttribute ProductDao product){
        productService.saveProductDao(product);
        return "redirect:/admin/dashboard";
    }

    @PostMapping(value = "/deleteProduct/{productId}")
    public String deleteProduct(@PathVariable String productId){
        Long id = Long.parseLong(productId);
        productService.deleteProductById(id);
        return "redirect:/admin/dashboard";
    }


    @PostMapping("/admin")
    public String processRegister(UserRegistrationDao userRegistrationDao) {
        userService.saveAdmin(userRegistrationDao);
        return "Customer/signUpSuccess";
    }
}
