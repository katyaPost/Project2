package com.example.project2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.R;
import com.example.project2.models.ShoeColor;
import com.example.project2.utils.ImageUtil;

import java.util.List;

public class ColorsAdapter extends RecyclerView.Adapter<ColorsAdapter.ColorViewHolder> {

    private final Context context;
    private final List<ShoeColor> colorOptions;
    private final OnColorClickListener listener;

    public interface OnColorClickListener {
        void onColorClick(ShoeColor shoeColor);
    }

    public ColorsAdapter(Context context, List<ShoeColor> colorOptions, OnColorClickListener listener) {
        this.context = context;
        this.colorOptions = colorOptions;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ColorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_color, parent, false);
        return new ColorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ColorViewHolder holder, int position) {
        ShoeColor shoeColor = colorOptions.get(position);
        holder.colorImage.setImageBitmap(ImageUtil.convertFrom64base(shoeColor.getPicBase64()));

        holder.itemView.setOnClickListener(v -> listener.onColorClick(shoeColor));
    }

    @Override
    public int getItemCount() {
        return colorOptions.size();
    }

    static class ColorViewHolder extends RecyclerView.ViewHolder {
        ImageView colorImage;

        public ColorViewHolder(@NonNull View itemView) {
            super(itemView);
            colorImage = itemView.findViewById(R.id.color_image);
        }
    }
}
