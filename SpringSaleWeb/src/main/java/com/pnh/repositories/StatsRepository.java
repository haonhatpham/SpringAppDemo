/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pnh.repositories;

import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface StatsRepository {
    List<Object[]> getRevenueByProduct();
    List<Object[]> getRevenueByTime(String time, int year);
}
