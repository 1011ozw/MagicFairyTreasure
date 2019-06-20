/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Service;

import com.mft.Bean.ProductParent;
import com.mft.Dao.Impl.ProductParentImpl;
import com.mft.Dao.ProductParetnDao;
import java.util.List;

/**
 *
 * @author 张帆
 */
public class ProductParentService {

    ProductParetnDao pd = new ProductParentImpl();

    public List<ProductParent> getAllPs() {
        List<ProductParent> pps = pd.getAllPs();
        return pps;
    }

    public int addPp(String name) {
        return pd.addPp(name);
    }

    public int delPp(int id) {
        return pd.delPp(id);
    }

    public int upPp(int id, String name) {
        return pd.upPp(id, name);
    }

    public boolean isPp(String name) {
        return pd.isPp(name);
    }
}
