package com.example.project2.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.project2.R;

public class CheckoutPagerAdapter extends RecyclerView.Adapter<CheckoutPagerAdapter.ViewHolder> {

    private Context context;

    public CheckoutPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return 2; // שני שלבים
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.page_checkout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == 0) {
            holder.step1Layout.setVisibility(View.VISIBLE);
            holder.step2Layout.setVisibility(View.GONE);

            holder.nextButton.setOnClickListener(v -> {
                // מעבר לשלב 2 דרך ViewPager2
                ViewPager2 viewPager = ((Activity) context).findViewById(R.id.view_pager);
                viewPager.setCurrentItem(1);
            });

        } else if (position == 1) {
            holder.step1Layout.setVisibility(View.GONE);
            holder.step2Layout.setVisibility(View.VISIBLE);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout step1Layout, step2Layout;
        Button nextButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            step1Layout = itemView.findViewById(R.id.step_1_layout);
            step2Layout = itemView.findViewById(R.id.step_2_layout);
            nextButton = itemView.findViewById(R.id.next_button);
        }
    }
}
