package com.sk.maa.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.util.UUID;

public class LoginUtil {
    public static Bitmap stringToBitmap(String code, int width, int height) {
        Bitmap bitmap = null;
        try {
            byte[] bytes = Base64.decode(code, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            bitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }
}
