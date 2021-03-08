package com.farmers.buyers.common;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Iterator;
import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 25-01-2021 at 20:42
 * mohammadsajjad679@gmail.com
 */

public class Extensions {

    public static View inflate(ViewGroup $this$inflate, int resId) {
        return LayoutInflater.from($this$inflate.getContext()).inflate(resId, $this$inflate, false);
    }


    public static String convert(List<String> list) {
        String res = "";
        for (Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
            res += iterator.next() + (iterator.hasNext() ? "," : "");
        }
        return res;
    }
}
