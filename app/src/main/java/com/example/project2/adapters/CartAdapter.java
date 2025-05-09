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

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private List<CartItem> cartItemList;

    // קונסטרוקטור המקבל את ה-Context
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

        // הצגת פרטי המוצר
        holder.shoeName.setText(cartItem.getShoeName());
        holder.shoePrice.setText("₪" + cartItem.getShoePrice());
        holder.shoeSize.setText("Size: " + cartItem.getSize());
        holder.shoeImage.setImageBitmap(ImageUtil.convertFrom64base(cartItem.getShoeImageBase64()));
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    // עדכון רשימת הפריטים
    public void setItems(@NonNull List<CartItem> items) {
        this.cartItemList.clear();
        this.cartItemList.addAll(items);
        this.notifyDataSetChanged();
    }

    // שמירה של העגלה ב-SharedPreferences
    public void saveCartToPreferences() {
        SharedPreferencesUtil.saveCart(this.context, cartItemList);
    }

    // טעינת פרטי העגלה מ-SharedPreferences
    public void loadCartFromPreferences() {
        List<CartItem> cartItems = SharedPreferencesUtil.loadCart(this.context);
        setItems(cartItems);
    }

    // הסרת פריט מהעגלה
    public void removeItem(int position) {
        if (position < 0 || position >= cartItemList.size()) {
            return;
        }
        cartItemList.remove(position);
        notifyItemRemoved(position);
        saveCartToPreferences(); // שמירה של העדכון ב-SharedPreferences
    }

    // מחלקת ViewHolder שמייצגת פריט בעגלה
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
