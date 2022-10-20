package com.example.main_week7.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data @AllArgsConstructor @NoArgsConstructor
public class AddToCartDao {
    private Integer id;
    private @NotNull Integer productId;
    private @NotNull Integer quantity;

}
