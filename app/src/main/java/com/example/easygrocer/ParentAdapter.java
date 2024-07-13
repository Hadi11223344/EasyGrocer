package com.example.easygrocer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.ViewHolder> {
    List<Model_Parent_Rv> parent_list;
    Context context;

    public ParentAdapter(List<Model_Parent_Rv> parent_list, Context context) {
        this.parent_list = parent_list;
        this.context = context;
    }

    @NonNull
    @Override
    public ParentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.parent_rv,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ParentAdapter.ViewHolder holder, int position) {
        holder.parentTitle.setText(parent_list.get(position).getTitle());
        ChildAdapter childAdapter = new ChildAdapter(parent_list.get(position).childModelClassList,context);
        holder.childRv.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        holder.childRv.setAdapter(childAdapter);

    }

    @Override
    public int getItemCount() {
        return parent_list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView parentTitle;
        RecyclerView childRv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            childRv = itemView.findViewById(R.id.rvChild);
            parentTitle = itemView.findViewById(R.id.tvCategoryName);
        }
    }
}
