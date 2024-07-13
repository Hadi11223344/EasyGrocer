package com.example.easygrocer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codebyashish.autoimageslider.AutoImageSlider;
import com.codebyashish.autoimageslider.Enums.ImageActionTypes;
import com.codebyashish.autoimageslider.Enums.ImageScaleType;
import com.codebyashish.autoimageslider.Interfaces.ItemsListener;
import com.codebyashish.autoimageslider.Models.ImageSlidesModel;
import com.google.firebase.FirebaseApp;

import java.util.ArrayList;


public class FHome extends Fragment implements ItemsListener {
    private ItemsListener listener;
    private RecyclerView recyclerView;
    private ParentAdapter parentAdapter;
    private ArrayList<Model_Parent_Rv> parentList;
    private ArrayList<Model_Child_Rv>  breakFastList;
    private ArrayList<Model_Child_Rv>  dairyList;
    private ArrayList<Model_Child_Rv>  groceryList;
    private ArrayList<Model_Child_Rv>  meatAndSeafoodList;
    private ArrayList<Model_Child_Rv>  beveragesList;
    private ArrayList<Model_Child_Rv>  fruitsAndVegetablesList;

    public FHome() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
        init();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_f_home, container, false);
        recyclerView  = v.findViewById(R.id.rvParent);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupRecyclerView();
        setupImageSlider(view);
    }

    private void setupRecyclerView() {
        populateLists();
        parentAdapter = new ParentAdapter(parentList, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(parentAdapter);
    }

    private void populateLists() {
        fruitsAndVegetablesList.add(new Model_Child_Rv(R.drawable.fruits, "Fruits"));
        fruitsAndVegetablesList.add(new Model_Child_Rv(R.drawable.vegetables, "Vegetables"));
        fruitsAndVegetablesList.add(new Model_Child_Rv(R.drawable.exotic_herbs, "Exotic Herbs"));
        parentList.add(new Model_Parent_Rv("Fruits and Vegetables", fruitsAndVegetablesList));

        breakFastList.add(new Model_Child_Rv(R.drawable.breads, "Bread & Crumbs"));
        breakFastList.add(new Model_Child_Rv(R.drawable.buns, "Buns"));
        breakFastList.add(new Model_Child_Rv(R.drawable.eggs, "Eggs"));
        breakFastList.add(new Model_Child_Rv(R.drawable.syrups, "Syrups"));
        breakFastList.add(new Model_Child_Rv(R.drawable.spreads, "Spreads"));
        parentList.add(new Model_Parent_Rv("Breakfast Essentials", breakFastList));

        dairyList.add(new Model_Child_Rv(R.drawable.dairy, "Milk & Cheese"));
        dairyList.add(new Model_Child_Rv(R.drawable.butter, "Butter"));
        dairyList.add(new Model_Child_Rv(R.drawable.flavoured_milk, "Flavoured Milk"));
        dairyList.add(new Model_Child_Rv(R.drawable.powdered_milk, "Powdered Milk"));
        parentList.add(new Model_Parent_Rv("Dairy", dairyList));

        groceryList.add(new Model_Child_Rv(R.drawable.cooking_oil, "Cooking Oil"));
        groceryList.add(new Model_Child_Rv(R.drawable.spices, "Spices"));
        groceryList.add(new Model_Child_Rv(R.drawable.salt_sugar, "Salt & Sugar"));
        parentList.add(new Model_Parent_Rv("Grocery", groceryList));

        meatAndSeafoodList.add(new Model_Child_Rv(R.drawable.meat, "Meat"));
        meatAndSeafoodList.add(new Model_Child_Rv(R.drawable.seafood, "Seafood"));
        parentList.add(new Model_Parent_Rv("Meat and Seafood", meatAndSeafoodList));

        beveragesList.add(new Model_Child_Rv(R.drawable.cold_drink, "Cold Drinks"));
        beveragesList.add(new Model_Child_Rv(R.drawable.beverages, "Beverages"));
        parentList.add(new Model_Parent_Rv("Beverages", beveragesList));
    }

    private void setupImageSlider(View view) {
        ArrayList<ImageSlidesModel> autoImageList = new ArrayList<>();
        AutoImageSlider autoImageSlider = view.findViewById(R.id.autoImageSlider);

        autoImageList.add(new ImageSlidesModel(R.drawable.simage1, "First image"));
        autoImageList.add(new ImageSlidesModel(R.drawable.simage2, "Second image"));
        autoImageList.add(new ImageSlidesModel(R.drawable.simage1, "Third image"));

        autoImageSlider.setImageList(autoImageList, ImageScaleType.FIT);
        autoImageSlider.setDefaultAnimation();
        autoImageSlider.onItemClickListener(this);
    }

    @Override
    public void onItemChanged(int position) {
        // Handle image change if needed
    }

    @Override
    public void onItemClicked(int position) {
        // Handle image click
    }

    @Override
    public void onTouched(@Nullable ImageActionTypes actionType, int position) {
        // Handle image touch events
    }

    private void init() {
        parentList = new ArrayList<>();
        breakFastList = new ArrayList<>();
        dairyList = new ArrayList<>();
        groceryList = new ArrayList<>();
        meatAndSeafoodList = new ArrayList<>();
        fruitsAndVegetablesList = new ArrayList<>();
        beveragesList = new ArrayList<>();
    }
}