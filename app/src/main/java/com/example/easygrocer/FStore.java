package com.example.easygrocer;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FStore extends Fragment {
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageUri;
    private Bitmap bitmap;

    private ImageView ivImage;
    private Spinner productCatSpinner;
    private Spinner productSubCatSpinner;

    private TextInputEditText tietProductName, tietProductPrice;
    private Button btnImageUpload, btnSubmit;

    private DatabaseReference databaseReference;
    private Bitmap selectedImageBitmap;
    private Map<String, ArrayList<String>> subcategoriesMap;
    List<String> categories;

    public FStore() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_f_store, container, false);

        // Initialize views
        ivImage = view.findViewById(R.id.ivImage);
        productCatSpinner = view.findViewById(R.id.productCatSpinner);
        tietProductName = view.findViewById(R.id.tietProductName);
        tietProductPrice = view.findViewById(R.id.tietProductPrice);
        btnImageUpload = view.findViewById(R.id.btnImageUpload);
        btnSubmit = view.findViewById(R.id.btnSubmit);
        productSubCatSpinner = view.findViewById(R.id.productSubCatSpinner);



        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        categories = new ArrayList<>();
        subcategoriesMap = new HashMap<>();

        categories.add("Dairy");
        categories.add("Grocery");
        categories.add("Meat & Seafood");
        categories.add("Beverages");
        categories.add("Breakfast Essentials");
        categories.add("Fruits and Vegetables");

        // Populating lists with respective subcategories
        ArrayList<String> fruitsAndVegetablesSubcategories = new ArrayList<>();
        fruitsAndVegetablesSubcategories.add("Fruits");
        fruitsAndVegetablesSubcategories.add("Vegetables");
        fruitsAndVegetablesSubcategories.add("Exotic Herbs");

        ArrayList<String> dairySubcategories = new ArrayList<>();
        dairySubcategories.add("Milk and Cheese");
        dairySubcategories.add("Cheese");
        dairySubcategories.add("Powdered Milk");
        dairySubcategories.add("Flavoured Milk");
        dairySubcategories.add("Butter");

        ArrayList<String> breakfastSubcategories = new ArrayList<>();
        breakfastSubcategories.add("Bread & Crumbs");
        breakfastSubcategories.add("Buns");
        breakfastSubcategories.add("Eggs");
        breakfastSubcategories.add("Syrups");
        breakfastSubcategories.add("Spreads");

        ArrayList<String> groceriesSubcategories = new ArrayList<>();
        groceriesSubcategories.add("Cooking Oil");
        groceriesSubcategories.add("Spices");
        groceriesSubcategories.add("Salt & Sugar");

        ArrayList<String> beveragesSubcategories = new ArrayList<>();
        beveragesSubcategories.add("Cold Drinks");
        beveragesSubcategories.add("Hot Drinks");

        ArrayList<String> meatAndSeafoodSubcategories = new ArrayList<>();
        meatAndSeafoodSubcategories.add("Meat");
        meatAndSeafoodSubcategories.add("Seafood");

        // mapping categories to subcategories
        subcategoriesMap.put("Fruits and Vegetables", fruitsAndVegetablesSubcategories);
        subcategoriesMap.put("Dairy", dairySubcategories);
        subcategoriesMap.put("Breakfast Essentials", breakfastSubcategories);
        subcategoriesMap.put("Grocery", groceriesSubcategories);
        subcategoriesMap.put("Beverages", beveragesSubcategories);
        subcategoriesMap.put("Meat & Seafood", meatAndSeafoodSubcategories);


        setupCategorySpinner();

        // Initialize Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("products");

        // Set onClickListener for the button to upload an image
        btnImageUpload.setOnClickListener(v -> openFileChooser());

        // Set onClickListener for the submit button
        btnSubmit.setOnClickListener(v -> saveProductToFirebase());
    }
    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            Log.d("Image URI", "onActivityResult: " + imageUri);
            try {
                selectedImageBitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), imageUri);
                ivImage.setImageBitmap(selectedImageBitmap);
                Log.d("Selected Image", "onActivityResult: " + selectedImageBitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void saveProductToFirebase() {
        String category = productCatSpinner.getSelectedItem().toString();
        String productName = tietProductName.getText().toString().trim();
        String productPriceStr = tietProductPrice.getText().toString().trim();
        String subcategory = productSubCatSpinner.getSelectedItem().toString();

        if (TextUtils.isEmpty(productName)) {
            tietProductName.setError("Please enter product name");
            return;
        }

        if (TextUtils.isEmpty(productPriceStr)) {
            tietProductPrice.setError("Please enter product price");
            return;
        }

        double productPrice = Double.parseDouble(productPriceStr);

        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        StorageReference imageRef;
        String fileName;
        if (imageUri != null) {
            fileName = "images/" + System.currentTimeMillis() + "." + imageUri.getLastPathSegment();
            imageRef = storageRef.child(fileName);
        } else {
            Toast.makeText(requireContext(), "No image selected", Toast.LENGTH_SHORT).show();
            return;
        }
            ivImage.setDrawingCacheEnabled(true);
            ivImage.buildDrawingCache();
            bitmap = ((BitmapDrawable) ivImage.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] imageData = baos.toByteArray();
            UploadTask uploadTask = storageRef.putBytes(imageData);


        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }

                // Continue with the task to get the download URL
                return storageRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();

                } else {
                    // handle else part
                }
            }
        });

        String imageUrl = urlTask.toString();
        // Create a Product object and save to Firebase Realtime Database
        Product product = new Product(category, productName, productPrice, imageUrl, subcategory);
        String productId = databaseReference.push().getKey();

        databaseReference.child(productId).setValue(product)
                .addOnCompleteListener(dbTask -> {
                    if (dbTask.isSuccessful()) {
                        Toast.makeText(requireContext(), "Product added successfully", Toast.LENGTH_SHORT).show();
                        clearFields();
                    } else {
                        Toast.makeText(requireContext(), "Failed to add product", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> {
                    // Handle failure to save product data
                    Log.e("Firebase", "Failed to save product to database: " + e.getMessage());
                    Toast.makeText(requireContext(), "Failed to add product: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });


    }

    private void setupCategorySpinner() {
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, categories);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productCatSpinner.setAdapter(categoryAdapter);

        productCatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = categories.get(position);
                updateSubcategorySpinner(selectedCategory);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }

    private void updateSubcategorySpinner(String category) {
        ArrayList<String> subcategories = subcategoriesMap.get(category);
        if (subcategories != null) {
            ArrayAdapter<String> subcategoryAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, subcategories);
            subcategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            productSubCatSpinner.setAdapter(subcategoryAdapter);
        }
    }

    private void clearFields() {
        tietProductPrice.setText("");
        tietProductName.setText("");
        ivImage.setImageResource(R.drawable.ic_image); // Reset image view to default

    }
}



