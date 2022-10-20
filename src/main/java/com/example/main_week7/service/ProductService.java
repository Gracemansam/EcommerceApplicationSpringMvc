package com.example.main_week7.service;

import com.example.main_week7.dao.ProductDao;
import com.example.main_week7.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    List<Product> getAll();

    public Product findProductById(Long id);

    public Product saveProduct(Product product);

    Product saveProductDao(ProductDao productDao);

    public Product updateProduct(Product product);


    public Boolean deleteProductById(Long id);


    Product productDaoToEntity(ProductDao productDao);
}
