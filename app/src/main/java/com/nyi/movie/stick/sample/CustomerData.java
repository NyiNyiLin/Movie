package com.nyi.movie.stick.sample;


import com.nyi.movie.stick.StickyMainData;

public class CustomerData implements StickyMainData {
    public static final int CONTENT_1 = 3;

    private String title;
    private int felan;


    public CustomerData(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getFelan() {
        return felan;
    }

    public void setFelan(int felan) {
        this.felan = felan;
    }
}
