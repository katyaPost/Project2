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
import com.example.project2.utils.ImageUtil;

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
        holder.colorImageView.setImageBitmap(ImageUtil.convertFrom64base(shoeColor.getPicBase64()));
    }

    @Override
    public int getItemCount() {
        return shoeColorList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView colorNameTextView;
        ImageView colorImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            colorNameTextView = itemView.findViewById(R.id.color_name_text_view_2);
            colorImageView = itemView.findViewById(R.id.color_image_view_2);
        }
    }
}
