package com.example.project2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.R;

import java.util.List;

public class ColorsAdapter extends RecyclerView.Adapter<ColorsAdapter.ColorViewHolder> {

    private final Context context;
    private final List<Integer> colorOptions;
    private final OnColorClickListener listener;

    public interface OnColorClickListener {
        void onColorClick(int colorResId);
    }

    public ColorsAdapter(Context context, List<Integer> colorOptions, OnColorClickListener listener) {
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
        int colorResId = colorOptions.get(position);
        holder.colorImage.setImageResource(colorResId);

        holder.itemView.setOnClickListener(v -> listener.onColorClick(colorResId));
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
