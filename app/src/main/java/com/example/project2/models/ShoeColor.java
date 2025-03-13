package com.example.project2.models;

public class ShoeColor {
    private String colorName;
    private String picBase64;


    public ShoeColor() {
    }

    public ShoeColor(String colorName, String picBase64) {
        this.colorName = colorName;
        this.picBase64 = picBase64;
    }


    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getPicBase64() {
        return picBase64;
    }

    public void setPicBase64(String picBase64) {
        this.picBase64 = picBase64;
    }

    @Override
    public String toString() {
        return "ShoeColor{" +
                "colorName='" + colorName + '\'' +
                ", picBase64='" + "HELP" + '\'' +
                '}';
    }
}
