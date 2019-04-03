package com.example.application.view;

import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.*;
import com.example.application.R;

import java.util.*;

import com.example.application.entity.ArticleEntity;

public class ArticlesRecyclerViewAdapter extends RecyclerView.Adapter<ArticlesRecyclerViewAdapter.ViewHolder> implements Comparator<Map.Entry<Long, ArticleEntity>> {
    private static ClickListener clickListener;
    private static ArrayList mData;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView idTextView;
        public TextView titleTextView;

        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
            idTextView = view.findViewById(android.R.id.text1);
            titleTextView = view.findViewById(android.R.id.text2);
        }

        @Override
        public void onClick(View view) {
            Map.Entry<Long, ArticleEntity> entry = (Map.Entry<Long, ArticleEntity>) mData.get(getAdapterPosition());
            clickListener.onItemClick(entry.getValue(), view);
        }

        @Override
        public boolean onLongClick(View view) {
            Map.Entry<Long, ArticleEntity> entry = (Map.Entry<Long, ArticleEntity>) mData.get(getAdapterPosition());
            clickListener.onItemLongClick(entry.getValue(), view);
            return false;
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        ArticlesRecyclerViewAdapter.clickListener = clickListener;
    }

    public interface ClickListener{
        void onItemClick(ArticleEntity article, View view);
        void onItemLongClick(ArticleEntity article, View view);
    }

    @Override
    public int compare(Map.Entry<Long, ArticleEntity> o1, Map.Entry<Long, ArticleEntity> o2) {
        return o1.getValue().id.compareTo(o2.getValue().id);
    }

    public ArticlesRecyclerViewAdapter(Map<Long, ArticleEntity> map) {
        mData = new ArrayList();
        mData.addAll(map.entrySet());
        Collections.sort(this.mData, this);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ArticlesRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.articles_adapter_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Map.Entry<Long, ArticleEntity> item = this.getItem(position);
        holder.idTextView.setText(item.getKey().toString());
        holder.titleTextView.setText(item.getValue().title);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private Map.Entry<Long, ArticleEntity> getItem(int position) {
        return (Map.Entry<Long, ArticleEntity>) mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        Map.Entry<Long, ArticleEntity> item = this.getItem(position);
        return item.getValue().id;
    }
}