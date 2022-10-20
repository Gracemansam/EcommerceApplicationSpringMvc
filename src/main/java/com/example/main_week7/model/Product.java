package com.example.main_week7.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name ="product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long product_Id;


    private String productName;

    private String productDesc;

    private String productCategory;


    private double price;


    private  int quantity;

    private String image;

    @Transient
    private int cartQty = 1;
}
