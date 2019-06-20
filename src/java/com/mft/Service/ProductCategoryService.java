/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Service;

import com.mft.Bean.ProductCategory;
import com.mft.Dao.Impl.ProductCategoryImpl;
import com.mft.Dao.ProductCategoryDao;
import java.util.List;

/**
 *
 * @author 张帆
 */
public class ProductCategoryService {

    ProductCategoryDao pd = new ProductCategoryImpl();

    public List<ProductCategory> getPcsByPid(int pid) {
        List<ProductCategory> pcs = pd.getPcsByPid(pid);
        return pcs;
    }

    public String getPcName(int pcid, int pid) {
        return pd.getPcName(pcid, pid);
    }

    public int addPc(int ppid, String name) {
        return pd.addPc(ppid, name);
    }

    public int delPc(int ppid, int pcid) {
        return pd.delPc(ppid, pcid);
    }

    public boolean isPc(int ppid, String name) {
        return pd.isPc(ppid, name);
    }

    public int upPc(int ppid, int pcid, String name) {
        return pd.upPc(ppid, pcid, name);
    }

    public int delPcs(int ppid) {
        return pd.delPcs(ppid);
    }
}
