/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pnh.repositories.impl;

import com.pnh.hibernatedemo.HibernateUtils;
import com.pnh.pojo.OrderDetail;
import com.pnh.pojo.Product;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;

/**
 *
 * @author admin
 */
public class StatsRepositoryImpl {

    public List<Object[]> getRevenueByProduct() {
        try (Session s = HibernateUtils.getFACTORY().openSession()) {
            CriteriaBuilder b =s.getCriteriaBuilder();
            CriteriaQuery<Object[]> query = b.createQuery(Object[].class);
            
            Root root = query.from(OrderDetail.class);
            Join<OrderDetail,Product> join = root.join("productId",JoinType.RIGHT);
            
            
            query.select(b.array(join.get("id"),join.get("name"),b.sum(b.prod(root.get("unitPrice"),root.get("quantity")))));
            query.groupBy(join.get("id"));
            
            Query q = s.createQuery(query);
            return q.getResultList();
        }
    }
}
