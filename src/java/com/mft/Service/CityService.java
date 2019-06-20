/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Service;

import com.mft.Bean.City;
import com.mft.Dao.CityDao;
import com.mft.Dao.Impl.CityImpl;
import java.util.List;

/**
 *
 * @author 张帆
 */
public class CityService {

    //实例化城市实现类的对象
    CityDao cd = new CityImpl();
    //得到某个省所有城市

    public List<City> getCitysByPid(int pid) {
        return cd.getCitysByPid(pid);
    }
}
