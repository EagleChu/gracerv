package com.mizi.lib.rv;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<Data, Holder extends BaseHolder> extends RecyclerView.Adapter<Holder> {

    protected List<Data> dataList;

    public BaseAdapter() {
    }

    public BaseAdapter(List<Data> dataList) {
        this.dataList = dataList;
    }

    /**
     * 初始化或替换 dataList
     */
    public void putData(List<Data> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    /**
     * 新增数据组
     */
    public void addData(List<Data> dataList) {
        if (this.dataList == null) {
            this.dataList = new ArrayList<>();
        }
        int start = this.dataList.size();
        int count = dataList.size();
        this.dataList.addAll(dataList);
        notifyItemRangeChanged(start, count);
    }

    /**
     * 新增单条数据
     */
    public void addData(Data data) {
        if (this.dataList == null) {
            this.dataList = new ArrayList<>();
        }
        int start = this.dataList.size();
        int count = 1;
        this.dataList.add(data);
        notifyItemRangeChanged(start, count);
    }

    @NonNull
    @Override
    public abstract Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType);

    /**
     * 根据 position 绑定(更新)holder中的数据
     * @param holder 对应的holder
     * @param position Item 的 index
     */
    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        // 从 dataList 中取出 数据, 调用 holder 的相应方法, 绑定数据
        holder.bindView(getItemData(position));
    }

    @Override
    public int getItemCount() {
        if (dataList == null || dataList.isEmpty()) {
            return 0;
        } else {
            return dataList.size();
        }
    }

    public List<Data> getDataList() {
        return dataList;
    }

    public Data getItemData(int position) {
        if (dataList == null || dataList.isEmpty()) {
            return null;
        } else {
            return dataList.get(position);
        }
    }
}
