package com.example.main_week7.dao;

import com.example.main_week7.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
@Data @AllArgsConstructor @NoArgsConstructor
public class ProductDao {

    private String productName;
    private String productDesc;
    private String productCategory;

    private double productPrice;
    private String productImage;
    private int productQty;


}
