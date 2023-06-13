package com.sk.maa.pojo;

import android.graphics.drawable.Drawable;

public class My {
    private Drawable Icon;
    private String Text;

    public My() {
    }

    public My(Drawable icon, String text) {
        Icon = icon;
        Text = text;
    }

    public Drawable getIcon() {
        return Icon;
    }

    public void setIcon(Drawable icon) {
        Icon = icon;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    @Override
    public String toString() {
        return "My{" +
                "Icon=" + Icon +
                ", Text='" + Text + '\'' +
                '}';
    }
}
