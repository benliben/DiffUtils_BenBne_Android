package com.example.benben.diffutils_benbneandroid;

import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * 一个普通的JavaBean，但是实现了clone方法，仅仅用于写Demo时，模拟刷新从网络获取数据用，
 * 因为使用DiffUtils比较新老数据集差异时，会遍历新老数据集的每个data，要确保他们的内存地址（指针）不一样，否则比较的是新老data是同一个，就一定相同，
 * 实际项目不需要，因为刷新时，数据一般从网络拉取，并且用Gson等解析出来，内存地址一定是不一样的。
 * Created by LiYuanXiong on 2016/10/10 21:48.
 * Email:3187683623@qq.com
 */

class TestBen implements Cloneable {
    private String name;
    private String desc;
    private int pic;

    public TestBen(String name, String desc, int pic) {
        this.name = name;
        this.desc = desc;
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }


    /**
     * 仅用于demo 实现克隆方法
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        TestBen ben = null;
        try {
            ben = (TestBen) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return super.clone();
    }
}
