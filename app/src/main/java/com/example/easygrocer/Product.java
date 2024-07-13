package com.example.easygrocer;

public class Product {
        private String category;
        private String subCategory;

        private String name;
        private double price;
        private String imageUrl;

        // Default constructor required for Firebase
        public Product() {
        }

        public Product(String category, String name, double price, String imageUrl, String subCategory) {
            this.category = category;
            this.name = name;
            this.price = price;
            this.imageUrl = imageUrl;
            this.subCategory = subCategory;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }
}

