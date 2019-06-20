/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Dao;
import com.mft.Bean.Area;
import java.util.List;

/**
 *
 * @author 张帆
 */
public interface AreaDao {
	List<Area>getAreas(int cid,int pid);//根据城市id 和省id 查询区域
	List<Area>getAreas(int pid);//根据省份id查询
}
