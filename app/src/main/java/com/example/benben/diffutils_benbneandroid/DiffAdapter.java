package com.example.benben.diffutils_benbneandroid;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * 普通的一个adapter
 * 唯一的亮点是 重写 onBindViewHolder（）这个方法
 * Created by LiYuanXiong on 2016/10/10 21:48.
 * Email:3187683623@qq.com
 */

public class DiffAdapter extends RecyclerView.Adapter<DiffAdapter.MyViewHolder> {

    private List<TestBen> mDatas;
    private Context mContext;
    private LayoutInflater mInflater;

    public DiffAdapter( Context mContext,List<TestBen> mDatas) {
        this.mDatas = mDatas;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setDatas(List<TestBen> mDatas) {
        this.mDatas = mDatas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(mInflater.inflate(R.layout.item_diff, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TestBen bean = mDatas.get(position);
        holder.tv1.setText(bean.getName());
        holder.tv2.setText(bean.getDesc());
        holder.iv.setImageResource(bean.getPic());
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv1, tv2;
        ImageView iv;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv1 = (TextView) itemView.findViewById(R.id.tv1);
            tv2 = (TextView) itemView.findViewById(R.id.tv2);
            iv = (ImageView) itemView.findViewById(R.id.iv);
        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position, List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        }else {
            Bundle payload = (Bundle) payloads.get(0);//取出我们在getChangePayload（）方法返回的bundle
            TestBen bean = mDatas.get(position);//取出新数据（可以不用）
            for (String key : payload.keySet()) {
                switch (key) {
                    case "KEY_DESC":
                        /**这里可以用payload，里面的数据 不过data也是新的 也可以用*/
                        holder.tv2.setText(bean.getDesc());
                        break;
                    case "KEY_PIC":
                        holder.iv.setImageResource(payload.getInt(key));
                        break;
                    default:
                        break;

                }
            }
        }
        super.onBindViewHolder(holder, position, payloads);
    }
}
