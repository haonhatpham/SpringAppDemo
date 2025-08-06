/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.pnh.hibernatedemo;

import com.pnh.pojo.Product;
import com.pnh.repositories.impl.CategoryRepositoryImpl;
import com.pnh.repositories.impl.ProductRepositoryImpl;
import com.pnh.repositories.impl.StatsRepositoryImpl;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author admin
 */
public class HibernateDemo {
    
    public static void main(String[] args) {
        CategoryRepositoryImpl s2 = new CategoryRepositoryImpl();
//        s.getCates().forEach(c -> System.out.println(c.getName()));

        ProductRepositoryImpl s = new ProductRepositoryImpl();

//          Map<String,String> params = new HashMap<>();
//          params.put("orderBy","price");
//          params.put("page","2");
//          s.getProducts(params).forEach(p-> System.out.printf("%s - %.1f\n",p.getName(),p.getPrice()));
//        Product p = new Product();
//        p.setName("ABC");
//        p.setPrice(123l);
//        p.setCategoryId(s2.getCateById(1));
//        
//        s.addOrUpdate(p);
        StatsRepositoryImpl s3 = new StatsRepositoryImpl();
        s3.getRevenueByProduct().forEach(o -> System.out.printf("%d - %s: %d\n", o[0], o[1], o[2]));
    }
}
