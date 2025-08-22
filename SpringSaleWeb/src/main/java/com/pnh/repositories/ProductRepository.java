/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pnh.repositories;

import com.pnh.pojo.Product;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ADMIN
 */
public interface ProductRepository {
    List<Product> getProducts(Map<String, String> params);
    void deleteProduct(int id);
    Product getProductById(int id);
    void addOrUpdate(Product p);
}

