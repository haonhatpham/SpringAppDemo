/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pnh.services.impl;

import com.pnh.repositories.StatsRepository;
import com.pnh.services.StatsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public class StatsServiceImpl implements StatsService{
    @Autowired
    private StatsRepository statsRepo;

    @Override
    public List<Object[]> getRevenueByProduct() {
        return this.statsRepo.getRevenueByProduct();
    }

    @Override
    public List<Object[]> getRevenueByTime(String time, int year) {
        return this.statsRepo.getRevenueByTime(time, year);
    }
}
