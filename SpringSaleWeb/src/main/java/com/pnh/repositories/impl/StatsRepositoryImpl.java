/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pnh.repositories.impl;

import com.pnh.pojo.OrderDetail;
import com.pnh.pojo.Product;
import com.pnh.pojo.SaleOrder;
import com.pnh.repositories.StatsRepository;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author admin
 */
@Repository
@Transactional
public class StatsRepositoryImpl implements StatsRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Object[]> getRevenueByProduct() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = b.createQuery(Object[].class);

        Root root = query.from(OrderDetail.class);
        Join<OrderDetail, Product> join = root.join("productId");

        query.select(b.array(join.get("id"), join.get("name"), 
                b.sum(b.prod(root.get("unitPrice"), root.get("quantity")))));
        query.groupBy(join.get("id"));

        Query q = s.createQuery(query);
        return q.getResultList();
    }

    @Override
    public List<Object[]> getRevenueByTime(String time, int year) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = b.createQuery(Object[].class);

        Root root = query.from(OrderDetail.class);
        Join<OrderDetail, SaleOrder> join = root.join("orderId");

        query.select(b.array(b.function(time, Integer.class, join.get("createdDate")), 
                b.sum(b.prod(root.get("unitPrice"), root.get("quantity")))));
        query.where(b.equal(b.function("YEAR", Integer.class, join.get("createdDate")), year));
        query.groupBy(b.function(time, Integer.class, join.get("createdDate")));

        Query q = s.createQuery(query);
        return q.getResultList();
    }
}
