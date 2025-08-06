/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pnh.repositories.impl;

import com.pnh.hibernatedemo.HibernateUtils;
import com.pnh.pojo.Product;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;

/**
 *
 * @author admin
 */
public class ProductRepositoryImpl {

    private static final int PAGE_SIZE = 8;

    public List<Product> getProducts(Map<String, String> params) {
        try (Session s = HibernateUtils.getFACTORY().openSession()) {

            CriteriaBuilder b = s.getCriteriaBuilder();
            CriteriaQuery<Product> query = b.createQuery(Product.class);
            Root root = query.from(Product.class);
            query.select(root);
            //loc

            if (params != null) {
                List<Predicate> predicates = new ArrayList<>();
                String kw = params.get("kw");
                if (kw != null && !kw.isEmpty()) {
                    predicates.add(b.like(root.get("name"), String.format("%%%s%%", kw)));
                }
                String fromPrice = params.get("fromPrice");
                if (fromPrice != null && !fromPrice.isEmpty()) {
                    predicates.add(b.greaterThanOrEqualTo(root.get("price"), fromPrice));
                }
                String toPrice = params.get("toPrice");
                if (toPrice != null && !toPrice.isEmpty()) {
                    predicates.add(b.lessThanOrEqualTo(root.get("price"), toPrice));
                }
                String cateId = params.get("cateId");
                if (cateId != null && !cateId.isEmpty()) {
                    predicates.add(b.equal(root.get("categoryId").as(Integer.class), cateId));
                }
                query.where(predicates);

                String orderBy = params.get("orderBy");
                if (orderBy != null && !orderBy.isEmpty()) {
                    query.orderBy(b.desc(root.get(orderBy)));
                }
            }
            //sxep
            Query q = s.createQuery(query);
            //phan trang
            if (params != null) {
                String p = params.getOrDefault("page", "1");
                int page = Integer.parseInt(p);
                int start = (page - 1) * PAGE_SIZE;

                q.setMaxResults(PAGE_SIZE);
                q.setFirstResult(start);
            }

            return q.getResultList();
        }
    }

    public void addOrUpdate(Product p) {
        try (Session s = HibernateUtils.getFACTORY().openSession()) {
            s.getTransaction().begin();
            if(p.getId() != null){
                s.merge(p);
            }
            else {
               s.persist(p);
            }
            s.getTransaction().commit();
        }
    }
}
