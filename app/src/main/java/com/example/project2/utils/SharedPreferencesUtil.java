package com.example.project2.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.project2.models.Cart;

import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.Gson;


public class SharedPreferencesUtil {

    private static final String PREF_NAME = "com.example.testapp.PREFERENCE_FILE_KEY";
    private static final String CART_KEY = "cart_data";

    public static void saveCart(Context context, Cart cart) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson=new Gson();
        editor.putString(CART_KEY, gson.toJson(cart));
        editor.apply();
    }

    public static Cart loadCart(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String cartJsonString = sharedPreferences.getString(CART_KEY, null);
        if (cartJsonString == null) return null;
        Gson gson=new Gson();
        return gson.fromJson(cartJsonString, Cart.class);
    }

    public static void clearCart(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(CART_KEY);
        editor.apply();
    }
}
