package com.example.project2.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.project2.models.Cart;

import org.json.JSONException;
import org.json.JSONObject;

public class SharedPreferencesUtil {

    private static final String PREF_NAME = "com.example.testapp.PREFERENCE_FILE_KEY";
    private static final String CART_KEY = "cart_data";

    public static void saveCart(Context context, Cart cart) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        try {
            JSONObject cartJson = cart.toJson();
            editor.putString(CART_KEY, cartJson.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        editor.apply();
    }

    public static Cart loadCart(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String cartString = sharedPreferences.getString(CART_KEY, null);
        if (cartString != null) {
            try {
                JSONObject cartJson = new JSONObject(cartString);
                return Cart.fromJson(cartJson);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return new Cart();
    }

    public static void clearCart(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(CART_KEY);
        editor.apply();
    }
}
