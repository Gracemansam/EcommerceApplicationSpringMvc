package com.example.main_week7.service.impl;

import com.example.main_week7.dao.ProductDao;
import com.example.main_week7.model.Product;
import com.example.main_week7.repository.ProductRepository;
import com.example.main_week7.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findProductById(Long id) {
        return  productRepository.findById(id).orElse(null);
    }

    @Override
    public Product saveProduct(Product product) {
        return null;
    }

    @Override
    public Product saveProductDao(ProductDao productDao) {
        return productRepository.save(productDaoToEntity(productDao));
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Boolean deleteProductById(Long id) {
        if (this.findProductById(id) == null) {
            return false;
        }
        productRepository.deleteById(id);
        return true;
    }


    @Override
    public Product productDaoToEntity(ProductDao productDao){
        Product product1 = new Product();
        product1.setProductName(productDao.getProductName());
        product1.setPrice(productDao.getProductPrice());
        product1.setQuantity(productDao.getProductQty());
        product1.setProductDesc(productDao.getProductDesc());
        product1.setProductCategory(productDao.getProductCategory());
        product1.setImage(productDao.getProductImage());
        return product1;
    }
}
