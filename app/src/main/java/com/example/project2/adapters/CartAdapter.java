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
import com.example.project2.models.Shoe;
import com.example.project2.utils.ImageUtil;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private List<CartItem> cartItemList;

    public CartAdapter(Context context, List<CartItem> cartItemList) {
        this.context = context;
        this.cartItemList = cartItemList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem cartItem = cartItemList.get(position);
        holder.shoeName.setText(cartItem.getName());
        holder.shoePrice.setText("$" + cartItem.getPrice());
        holder.shoeSize.setText("Size: " + cartItem.getShoeSize());
        holder.shoeImage.setImageBitmap(ImageUtil.convertFrom64base(cartItem.getShoeImageBase64()));
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView shoeImage;
        TextView shoeName, shoePrice, shoeSize;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            shoeImage = itemView.findViewById(R.id.cart);
            shoeName = itemView.findViewById(R.id.cart_item_name);
            shoePrice = itemView.findViewById(R.id.cart_item_price);
            shoeSize = itemView.findViewById(R.id.cart_item_size);
        }
    }
}
