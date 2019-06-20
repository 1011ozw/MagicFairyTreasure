/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Dao;

import com.mft.Bean.Province;
import java.util.List;

/**
 *
 * @author 张帆
 */

public interface ProvinceDao {
	List<Province>getProvinces();//得到所有省份
}