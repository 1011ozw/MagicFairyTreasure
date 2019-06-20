/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Dao;

import com.mft.Bean.City;
import java.util.List;

/**
 *
 * @author 张帆
 */
public interface CityDao {
	List<City>getCitysByPid(int pid);//得到某个省所有城市
}