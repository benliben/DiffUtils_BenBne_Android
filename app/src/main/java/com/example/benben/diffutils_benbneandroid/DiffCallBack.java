package com.example.benben.diffutils_benbneandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * 核心类 用来判断新旧Item是否相等
 * Created by LiYuanXiong on 2016/10/10 21:48.
 * Email:3187683623@qq.com
 */

public class DiffCallBack extends DiffUtil.Callback {


//
//    @Override
//    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
//        TestBen beanOld = mOldDatas.get(oldItemPosition);
//        TestBen beanNew = mNewDatas.get(newItemPosition);
//        if (!beanOld.getDesc().equals(beanNew.getDesc())) {
//            return false;//如果有内容不同，就返回false
//        }
//        if (beanOld.getPic() != beanNew.getPic()) {
//            return false;//如果有内容不同，就返回false
//        }
//        return true; //默认两个data内容是相同的
//    }
//
//    @Nullable
//    @Override
//    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
//        TestBen oldBean = mOldDatas.get(oldItemPosition);
//        TestBen newBean = mNewDatas.get(newItemPosition);
//
//
//        Bundle payload = new Bundle();
//        if (!oldBean.getDesc().equals(newBean.getDesc())) {
//            payload.putString("KEY_DESC", newBean.getDesc());
//        }
//        if (oldBean.getPic() != newBean.getPic()) {
//            payload.putInt("KEY_PIC", newBean.getPic());
//        }
//
//        if (payload.size() == 0)//如果没有变化 就传空
//            return null;
//        return payload;//
//    }






    private List<TestBen> mOldDatas, mNewDatas;

    public DiffCallBack(List<TestBen> mOldDatas, List<TestBen> mNewDatas) {
        this.mOldDatas = mOldDatas;
        this.mNewDatas = mNewDatas;
    }


    /**
     * 老数据size
     */
    @Override
    public int getOldListSize() {
        return mOldDatas != null ? mOldDatas.size() : 0;
    }

    /**
     * 新数据size
     */
    @Override
    public int getNewListSize() {
        return mNewDatas != null ? mNewDatas.size() : 0;
    }

    /**
     * 被DiffUtil调用，用来判断两个对象是否是相同的Item
     * 例如：如果你对Item有唯一的字段，这个方法就判断id是否相等
     * 本例判断Name字段是否一致
     * @param oldItemPosition 在旧列表中的位置
     * @param newItemPosition 在新列表中的位置
     * @return 是否是同一个对象（boolean类型
     */
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldDatas.get(oldItemPosition).getName()
                .equals(mNewDatas.get(newItemPosition).getName());
    }


    /**
     * 被DiffUtil调用 用来检查两个Item是否有相同的数据
     * DiffUtil用返回的信息（true or false）来检测当前的Item的内容是否发生了变化
     * DiffUtil 用这个方法替代equals方法检查是否相等
     * 所以你可以根据你的UI去改变它的返回值
     * 例如 如果你用RecycleView.Adapter 配哈DiffUtil使用 你需要返回Item的视觉表现是否相同
     * 这个方法仅仅在areItemsTheSame（）返回true时调用
     *
     * @param oldItemPosition 旧列表在项目中的位置
     * @param newItemPosition 新列表在项目中的位置
     * @return true or false
     */
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        TestBen beanOld = mOldDatas.get(oldItemPosition);
        TestBen beanNew = mNewDatas.get(newItemPosition);

        if (!beanOld.getDesc().equals(beanNew.getDesc())) {
            return false;//如果有内容不同就返回false
        }

        if (beanOld.getPic() != beanNew.getPic()) {
            return false;//如果内容不同，就返回false
        }
        return true;//莫让两个data内容是相同的
    }


    /**
     *
     * 例如 如果你用RecycleView配合DiffUtil 你可以返回 这个Item改变的那些字段
     * 默认的实现是返回null
     *
     * @param oldItemPosition
     * @param newItemPosition
     * @return 一个代表着新老Item的改变内容的payload对象
     */
    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        /**定向刷新重点部分更新
         * 效率最高
         * 只是没有了ItemChange的白光一闪动画*/
        TestBen oldBen = mOldDatas.get(oldItemPosition);
        TestBen newBen = mNewDatas.get(newItemPosition);

        /**这里就不用比较核心字段，一定相等*/
        Bundle payload = new Bundle();
        if (!oldBen.getDesc().equals(newBen.getDesc())) {
            payload.putString("KEY_DESC", newBen.getDesc());

        }
        if (oldBen.getPic() != newBen.getPic()) {
            payload.putInt("KEY_PIC", newBen.getPic());
        }

        /**如果没有变化就传空*/
        if (payload.size() == 0)//如果没有变化 就传空
            return null;
        return payload;//
        }
}
