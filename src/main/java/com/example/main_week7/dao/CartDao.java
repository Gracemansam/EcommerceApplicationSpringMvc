package com.example.main_week7.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDao {
    private List<CartItemDao> cartItem;
    private double totalCost;
}
