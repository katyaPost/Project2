package com.example.project2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.R;
import com.example.project2.models.ShoeColor;

import java.util.List;

public class ShoeColorAdapter extends RecyclerView.Adapter<ShoeColorAdapter.ViewHolder> {

    private List<ShoeColor> shoeColorList;

    public ShoeColorAdapter(List<ShoeColor> shoeColorList) {
        this.shoeColorList = shoeColorList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shoe_color, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ShoeColor shoeColor = shoeColorList.get(position);
        holder.colorNameTextView.setText(shoeColor.getColorName());
        // For simplicity, skipping decoding and showing base64 string
        holder.base64TextView.setText(shoeColor.getPicBase64());
    }

    @Override
    public int getItemCount() {
        return shoeColorList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView colorNameTextView, base64TextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            colorNameTextView = itemView.findViewById(R.id.colorNameEditText);
            base64TextView = itemView.findViewById(R.id.textView);
        }
    }
}
