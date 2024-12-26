package com.example.project2.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.R;
import com.example.project2.screens.ShoeDetails;
import com.example.project2.models.Shoe;

import java.util.List;

public class ShoesAdapter extends RecyclerView.Adapter<ShoesAdapter.ShoeViewHolder> {

    private Context context;
    private List<Shoe> shoesList;

    public ShoesAdapter(Context context, List<Shoe> shoesList) {
        this.context = context;
        this.shoesList = shoesList;
    }

    @Override
    public ShoeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_shoe, parent, false);
        return new ShoeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShoeViewHolder holder, int position) {
        Shoe shoe = shoesList.get(position);
        holder.shoeName.setText(shoe.getName());
        holder.shoePrice.setText("$" + shoe.getPrice());
        holder.shoeImage.setImageResource(shoe.getImageResId());

        // הגדרת ליחוץ על התמונה
        holder.shoeImage.setOnClickListener(v -> {
            // יצירת Intent לשלוח את הנתונים ל-ShoeDetailsActivity
            Intent intent = new Intent(context, ShoeDetails.class);
            intent.putExtra("shoe_name", shoe.getName());
            intent.putExtra("shoe_price", shoe.getPrice());
            intent.putExtra("shoe_image", shoe.getImageResId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return shoesList.size();
    }

    public static class ShoeViewHolder extends RecyclerView.ViewHolder {
        TextView shoeName, shoePrice;
        ImageView shoeImage;

        public ShoeViewHolder(View itemView) {
            super(itemView);
            shoeName = itemView.findViewById(R.id.shoeName);
            shoePrice = itemView.findViewById(R.id.shoePrice);
            shoeImage = itemView.findViewById(R.id.shoeImage);
        }
    }
}
