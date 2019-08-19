package com.example.ka.listviewnangcao.model;

/**
 * Created by KA on 10/18/2017.
 */

public class iConmodel {
    private int img;
    private String text;

    public iConmodel(int img, String text) {
        this.img = img;
        this.text = text;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
