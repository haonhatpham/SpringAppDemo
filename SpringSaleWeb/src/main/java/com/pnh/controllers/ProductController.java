/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pnh.controllers;

import com.pnh.pojo.Product;
import com.pnh.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author ADMIN
 */

@Controller
public class ProductController {
    @Autowired
    private ProductService prodService;
    
    @GetMapping("/products")
    public String list(Model model) {
        model.addAttribute("product", new Product());
        return "products";
    }
    
    @PostMapping("/products")
    public String create(@ModelAttribute(value = "product") Product p) {
        this.prodService.addOrUpdate(p);
        return "redirect:/";
    }
    
    @GetMapping("/products/{productId}")
    public String update(Model model, @PathVariable(value = "productId") int id) {
        model.addAttribute("product", this.prodService.getProductById(id));
        return "products";
    }
}
