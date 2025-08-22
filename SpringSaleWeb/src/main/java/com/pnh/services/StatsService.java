/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pnh.services;

import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface StatsService {
    List<Object[]> getRevenueByProduct();
    List<Object[]> getRevenueByTime(String time, int year);
}
