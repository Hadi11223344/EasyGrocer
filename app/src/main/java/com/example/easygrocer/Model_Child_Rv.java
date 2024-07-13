package com.example.easygrocer;

import android.graphics.Bitmap;
import android.widget.TextView;

public class Model_Child_Rv {

private    int image;
  private  String title;

    public Model_Child_Rv(int image, String title) {
        this.image = image;
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
