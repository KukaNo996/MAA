package com.sk.maa.utils;

import java.util.ArrayList;

public class MAAUtil {
    public static String arrayListToString(ArrayList<Integer> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(list.get(i));
        }
        return stringBuilder.toString();
    }
}
