package com.example.project2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.R;

public class CheckoutPagerAdapter extends RecyclerView.Adapter<CheckoutPagerAdapter.ViewHolder> {

    private Context context;

    public CheckoutPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.page_checkout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // הגדרת כל עמוד/שלב: שלב 1 (הזנת פרטי כרטיס) ושלב 2 (תשלום מאושר)
        if (position == 0) {
            holder.layout.setVisibility(View.VISIBLE);
            // הגדרת עמוד שלב 1
        } else {
            holder.layout.setVisibility(View.VISIBLE);
            // הגדרת עמוד שלב 2
        }
    }

    @Override
    public int getItemCount() {
        return 2; // שני שלבים
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View layout;

        public ViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
        }
    }
}
