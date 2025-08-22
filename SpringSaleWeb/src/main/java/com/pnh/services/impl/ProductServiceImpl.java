/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pnh.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.pnh.pojo.Product;
import com.pnh.repositories.ProductRepository;
import com.pnh.services.ProductService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository prodRepo;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<Product> getProducts(Map<String, String> params) {
        return this.prodRepo.getProducts(params);
    }

    @Override
    public void deleteProduct(int id) {
        this.prodRepo.deleteProduct(id);
    }

    @Override
    public Product getProductById(int id) {
        return this.prodRepo.getProductById(id);
    }

    @Override
    public void addOrUpdate(Product p) {
        if (!p.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(p.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                p.setImage((String) res.get("secure_url"));
            } catch (IOException ex) {
                p.setImage("https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248652/dkeolz3ghc0eino87iec.jpg");
            }
        }
        this.prodRepo.addOrUpdate(p);
    }
}
