package com.example.easygrocer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

public class SubCategoryDisplayer extends AppCompatActivity {

    RecyclerView rvList;
    ArrayList<Product> productsList;
    SubCategoryAdapter adapter;
    TextView tvSubCat;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sub_category_displayer);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();
//        FirebaseRecyclerOptions<Product> options =
//                new FirebaseRecyclerOptions.Builder<Product>().setQuery(reference
//                        .child("products"),Product.class).build();

        Intent i = getIntent();
        String subcategory = i.getStringExtra("subCategory");
        tvSubCat.setText(subcategory);

        FirebaseDatabase.getInstance().getReference().child("products").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){
                    productsList.clear();

                    for(DataSnapshot ds : snapshot.getChildren()){
                        Product product = ds.getValue(Product.class);
                        assert product != null;
                        if(product.getSubCategory().equals(subcategory) ){
                            productsList.add(product);
                        }

                    }
                    adapter.notifyDataSetChanged();
                }
                if(productsList.isEmpty()){

                    tvSubCat.setText("There is nothing to display yet");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        rvList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SubCategoryAdapter(productsList,this);
        rvList.setAdapter(adapter);

    }


    private void init(){
        rvList = findViewById(R.id.rvSelectedSubCategory);
        tvSubCat = findViewById(R.id.tvSelectedCategoryName);
        reference = FirebaseDatabase.getInstance().getReference();
        productsList = new ArrayList<>();

    }

}