package com.example.project2.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.project2.R;
import com.example.project2.models.CartItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

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

            // טען את פרטי העגלה מה-SharedPreferences
            SharedPreferences sharedPreferences = context.getSharedPreferences("cart_prefs", Context.MODE_PRIVATE);
            String cartJson = sharedPreferences.getString("cart_items", null);

            if (cartJson != null && !cartJson.isEmpty()) {
                Gson gson = new Gson();
                Type type = new TypeToken<List<CartItem>>() {}.getType();
                List<CartItem> cartItems = gson.fromJson(cartJson, type);

                if (cartItems != null && !cartItems.isEmpty()) {
                    StringBuilder summary = new StringBuilder();
                    double total = 0;

                    // יצירת סיכום כל פריט בעגלה
                    for (CartItem item : cartItems) {
                        // הוצאת שם הנעל והמחיר מתוך אובייקט ה-CartItem
                        String shoeName = item.getShoeName();
                        double shoePrice = item.getShoePrice();

                        // בדוק אם שם ומחיר תקינים לפני הוספה לסיכום
                        if (shoeName != null && !shoeName.isEmpty() && shoePrice > 0) {
                            summary.append(shoeName)
                                    .append(" - ₪")
                                    .append(shoePrice)
                                    .append("\n");
                            total += shoePrice;
                        } else {
                            // הדפסה במקרה של בעיה עם פריט
                            Log.e("CheckoutPagerAdapter", "פריט בעגלה לא תקין: " + item);
                        }
                    }

                    // הצגת סכום כולל
                    summary.append("\nסכום כולל: ₪").append(total);
                    holder.cartSummaryText.setText(summary.toString());
                } else {
                    holder.cartSummaryText.setText("העגלה ריקה");
                }
            } else {
                holder.cartSummaryText.setText("העגלה ריקה");
            }
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout step1Layout, step2Layout;
        Button nextButton;
        TextView cartSummaryText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            step1Layout = itemView.findViewById(R.id.step_1_layout);
            step2Layout = itemView.findViewById(R.id.step_2_layout);
            nextButton = itemView.findViewById(R.id.next_button);
            cartSummaryText = itemView.findViewById(R.id.cart_summary_text);
        }
    }
}
