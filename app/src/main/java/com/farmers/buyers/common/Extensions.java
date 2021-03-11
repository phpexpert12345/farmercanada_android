package com.farmers.buyers.common;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

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

    public static String getRandomCode() {
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";

        // combine all strings
        String alphaNumeric = upperAlphabet + numbers;

        // create random string builder
        StringBuilder sb = new StringBuilder();

        // create an object of Random class
        Random random = new Random();

        // specify length of random string
        int length = 10;

        for(int i = 0; i < length; i++) {

            // generate random index number
            int index = random.nextInt(alphaNumeric.length());

            // get character specified by index
            // from the string
            char randomChar = alphaNumeric.charAt(index);

            // append the character to string builder
            sb.append(randomChar);
        }

        String randomString = sb.toString();
        System.out.println("Random String is: " + randomString);

        return randomString.toUpperCase();
    }
}
