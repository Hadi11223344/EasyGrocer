package com.example.easygrocer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SubCategoryAdapter extends RecyclerView.Adapter< SubCategoryAdapter.ViewHolder> {
    private final ArrayList<Product> productsList;
    Context context;

    public SubCategoryAdapter(ArrayList<Product> productsList, Context context) {
        this.productsList = productsList;
        this.context = context;
    }

    @NonNull
    @Override
    public SubCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.single_selected_item_design, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product model = productsList.get(position);
        holder.tv_ProductName.setText(model.getName());
        holder.tv_ProductPrice.setText(String.valueOf( model.getPrice()));

//        // Get the image URL
//        String imageUrl = model.getImageUrl();
//
//        // Check if the imageUrl is not null or empty
//        if (imageUrl != null && !imageUrl.isEmpty()) {
//            // Use Glide to load the image from URL
//            Glide.with(context)
//                    .load(imageUrl)
//                    .apply(new RequestOptions().placeholder(R.drawable.placeholder)) // placeholder image
//                    .into(holder.iv_image);
//        } else {
//            // Set a placeholder image if imageUrl is null or empty
//            holder.iv_image.setImageResource(R.drawable.placeholder);
//        }
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_image;
        ImageButton ib_addToCart,ib_inc_Quantity, ib_dec_Quantity;
        TextView  tv_ProductName, tv_ProductPrice,tv_totalPrice, tv_Quantity;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ib_addToCart  = itemView.findViewById(R.id.ibAddToCart);
            ib_inc_Quantity = itemView.findViewById(R.id.btnPlus);
            ib_dec_Quantity = itemView.findViewById(R.id.btnMinus);
            tv_ProductName = itemView.findViewById(R.id.tvSelectedItemName);
            tv_ProductPrice = itemView.findViewById(R.id.tvSingleItemPrice);
            tv_totalPrice  = itemView.findViewById(R.id.tvTotalPrice);
            tv_Quantity     = itemView.findViewById(R.id.tvQuantity);





        }
    }
}
