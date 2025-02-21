package com.example.project2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.R;
import com.example.project2.models.CartItem;
import com.example.project2.utils.ImageUtil;
import com.example.project2.utils.SharedPreferencesUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private List<CartItem> cartItemList;

    public CartAdapter(Context context) {
        this.context = context;
        this.cartItemList = new ArrayList<>();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem cartItem = cartItemList.get(position);

        // שימוש במתודות של CartItem
        holder.shoeName.setText(cartItem.getShoeName());
        holder.shoePrice.setText("$" + cartItem.getShoePrice());
        holder.shoeSize.setText("Size: " + cartItem.getSize());
        holder.shoeImage.setImageBitmap(ImageUtil.convertFrom64base(cartItem.getShoeImageBase64()));
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public void setItems(List<CartItem> items) {
        this.cartItemList.clear();
        this.cartItemList.addAll(items);
        this.notifyDataSetChanged();
    }

    // שמירה של פריטי הסל ב-SharedPreferences
    public void saveCartToPreferences() {
        SharedPreferencesUtil sharedPreferences = context.getSharedPreferences("cart_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String cartJson = gson.toJson(cartItemList);
        editor.putString("cart_items", cartJson);
        editor.apply();
    }

    // טעינת פריטי הסל מ-SharedPreferences
    public void loadCartFromPreferences() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("cart_prefs", Context.MODE_PRIVATE);
        String cartJson = sharedPreferences.getString("cart_items", "[]");

        Gson gson = new Gson();
        List<CartItem> cartItems = gson.fromJson(cartJson, List.class);

        if (cartItems != null) {
            setItems(cartItems);
        }
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView shoeImage;
        TextView shoeName, shoePrice, shoeSize;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            shoeImage = itemView.findViewById(R.id.cart_item_image);
            shoeName = itemView.findViewById(R.id.cart_item_name);
            shoePrice = itemView.findViewById(R.id.cart_item_price);
            shoeSize = itemView.findViewById(R.id.cart_item_size);
        }
    }
}
