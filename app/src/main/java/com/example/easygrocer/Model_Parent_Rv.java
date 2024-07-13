package com.example.easygrocer;

import android.widget.TextView;

import java.util.List;

public class Model_Parent_Rv {
    String title;
    List<Model_Child_Rv> childModelClassList;
    public Model_Parent_Rv(String title, List<Model_Child_Rv> childModelClassList) {
        this.title = title;
        this.childModelClassList = childModelClassList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Model_Child_Rv> getChildModelClassList() {
        return childModelClassList;
    }

    public void setChildModelClassList(List<Model_Child_Rv> childModelClassList) {
        this.childModelClassList = childModelClassList;
    }
}
