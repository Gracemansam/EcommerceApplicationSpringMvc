package com.example.main_week7.dao;

import com.example.main_week7.model.Cart;
import com.example.main_week7.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDao {
    private Integer id;
    private @NotNull Integer quantity;
    private @NotNull Product product;

    @Autowired
    private Cart cart;

    public CartItemDao(Cart cart){
        this.setId(cart.getId());
        this.setQuantity(cart.getQuantity());
        this.setProduct(cart.getProduct());
    }
}
