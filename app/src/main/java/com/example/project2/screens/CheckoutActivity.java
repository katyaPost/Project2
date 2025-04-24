package com.example.project2.screens;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.project2.R;
import com.example.project2.adapters.CheckoutPagerAdapter;

public class CheckoutActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private int currentStep = 0; // מספר השלב הנוכחי
    private LinearLayout stepIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        viewPager = findViewById(R.id.view_pager);
        stepIndicator = findViewById(R.id.step_indicator);

        // אתחול ה-Adapter של ה-ViewPager
        viewPager.setAdapter(new CheckoutPagerAdapter(this));

        // עדכון שלב (כאשר עובר לעמוד חדש)
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                currentStep = position;
                updateStepIndicator();
            }
        });
    }

    private void updateStepIndicator() {
        // עדכון העיגולים
        View step1 = findViewById(R.id.circle_step_1);
        View step2 = findViewById(R.id.circle_step_2);
        View step3 = findViewById(R.id.circle_step_3);

        // עדכון כל עיגול בהתאם לשלב הנוכחי
        if (currentStep == 0) {
            step1.setBackgroundResource(R.drawable.circle_step_active);
            step2.setBackgroundResource(R.drawable.circle_step_inactive);
            step3.setBackgroundResource(R.drawable.circle_step_inactive);
        } else if (currentStep == 1) {
            step1.setBackgroundResource(R.drawable.circle_step_inactive);
            step2.setBackgroundResource(R.drawable.circle_step_active);
            step3.setBackgroundResource(R.drawable.circle_step_inactive);
        } else {
            step1.setBackgroundResource(R.drawable.circle_step_inactive);
            step2.setBackgroundResource(R.drawable.circle_step_inactive);
            step3.setBackgroundResource(R.drawable.circle_step_active);
        }
    }
}
