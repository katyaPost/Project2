package com.example.project2.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.project2.models.Cart;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.project2.models.CartItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class SharedPreferencesUtil {

    private static final String PREF_NAME = "com.example.project2.PREFERENCE_FILE_KEY";
    private static final String CART_KEY = "cart_data";

    public static void saveCart(Context context, List<CartItem> cartItems) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson=new Gson();
        editor.putString(CART_KEY, gson.toJson(cartItems));
        editor.apply();
    }

    public static void AddToCart(Context context, CartItem item) {
        List<CartItem> cartItems = loadCart(context);
        cartItems.add(item);
        saveCart(context, cartItems);
    }

    public static List<CartItem> loadCart(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String cartJsonString = sharedPreferences.getString(CART_KEY, null);
        if (cartJsonString == null) return new ArrayList<>();
        Gson gson=new Gson();
        Type listType = new TypeToken<List<CartItem>>() {}.getType();
        return gson.fromJson(cartJsonString, listType);
    }

    public static void clearCart(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(CART_KEY);
        editor.apply();
    }
}
