package com.example.easygrocer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ViewHolder> {

    List<Model_Child_Rv> child_list;
    Context context;

    public ChildAdapter(List<Model_Child_Rv> child_list, Context context) {
        this.child_list = child_list;
        this.context = context;
    }

    @NonNull
    @Override
    public ChildAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.child_rv_single_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildAdapter.ViewHolder holder, int position) {
        holder.ivImage.setImageResource(child_list.get(position).getImage());
        holder.tvName.setText(child_list.get(position).getTitle());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, SubCategoryDisplayer.class);
            intent.putExtra("subCategory", child_list.get(position).getTitle());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return child_list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView tvName;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            ivImage = itemView.findViewById(R.id.ivChildItem);
            tvName = itemView.findViewById(R.id.tvChildItem);
        }
    }
}
