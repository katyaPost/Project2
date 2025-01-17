package com.example.project2.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.R;
import com.example.project2.models.Shoe;
import com.example.project2.screens.ShoeDetails;
import com.example.project2.utils.ImageUtil;

import java.util.ArrayList;
import java.util.List;

public class ShoesAdapter extends RecyclerView.Adapter<ShoesAdapter.ShoeViewHolder> {

    private final Context context;
    private final List<Shoe> shoesList;

    public ShoesAdapter(Context context, List<Shoe> shoesList) {
        this.context = context;
        this.shoesList = shoesList;
    }

    @NonNull
    @Override
    public ShoeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_shoe, parent, false);
        return new ShoeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoeViewHolder holder, int position) {
        Shoe shoe = shoesList.get(position);

        holder.shoeName.setText(shoe.getName());
        holder.shoePrice.setText("$" + shoe.getPrice());
        holder.shoeImage.setImageBitmap(ImageUtil.convertFrom64base(shoe.getColorOptions().get(0).getPicBase64()));

//        // לחיצה על פריט הנעל
//        holder.itemView.setOnClickListener(v -> {
//            Intent intent = new Intent(context, ShoeDetails.class);
//            intent.putExtra("shoe_name", shoe.getName());
//            intent.putExtra("shoe_price", shoe.getPrice());
//            intent.putExtra("shoe_image", shoe.getImageResId());
////            intent.putIntegerArrayListExtra("shoe_colors", new ArrayList<>(shoe.getColorOptions()));
//            context.startActivity(intent);
//        });
    }

    @Override
    public int getItemCount() {
        return shoesList.size();
    }

    static class ShoeViewHolder extends RecyclerView.ViewHolder {
        TextView shoeName, shoePrice;
        ImageView shoeImage;

        public ShoeViewHolder(@NonNull View itemView) {
            super(itemView);
            shoeName = itemView.findViewById(R.id.shoeName);
            shoePrice = itemView.findViewById(R.id.shoePrice);
            shoeImage = itemView.findViewById(R.id.shoeImage);
        }
    }
}
