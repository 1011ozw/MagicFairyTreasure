/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Service;

import com.mft.Bean.Province;
import com.mft.Dao.Impl.ProvinceImpl;
import com.mft.Dao.ProvinceDao;
import java.util.List;

/**
 *
 * @author 张帆
 */
public class ProvinceService {

    ProvinceDao pd = new ProvinceImpl();

    public List<Province> getProvinces() {
        return pd.getProvinces();
    }
}
