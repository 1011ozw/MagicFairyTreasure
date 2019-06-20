/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Service;

import com.mft.Bean.Area;
import com.mft.Dao.AreaDao;
import com.mft.Dao.Impl.AreaImpl;
import java.util.List;

/**
 *
 * @author 张帆
 */
public class AreaService {

    //实例化AreaDao的实现类对象
    AreaDao ad = new AreaImpl();

    /**
     * 声明一个泛型getAreas方法,根据城市id 和省id 查询区域
     *
     * @param cid
     * @param pid
     * @return
     */
    public List<Area> getAreas(int cid, int pid) {
        return ad.getAreas(cid, pid);
    }

    /**
     * 根据省份id进行查询
     *
     * @param pid
     * @return
     */
    public List<Area> getAreas(int pid) {
        return ad.getAreas(pid);
    }
}
