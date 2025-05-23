package com.example.project2.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.R;
import com.example.project2.adapters.ShoesAdapter;
import com.example.project2.models.Shoe;
import com.example.project2.models.User;
import com.example.project2.services.DatabaseService;
import com.example.project2.utils.SharedPreferencesUtil;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ShoesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ShoesAdapter shoesAdapter;
    private List<Shoe> shoesList = new ArrayList<>();
    private DatabaseService databaseService;
    private ImageButton cartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoes);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        databaseService = DatabaseService.getInstance();

        recyclerView = findViewById(R.id.shoesRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        shoesAdapter = new ShoesAdapter(this, shoesList);
        recyclerView.setAdapter(shoesAdapter);

        cartButton = findViewById(R.id.cart_button);
        cartButton.setOnClickListener(v -> {
            Intent intent = new Intent(ShoesActivity.this, CartActivity.class);
            startActivity(intent);
        });

        databaseService.getShoes(new DatabaseService.DatabaseCallback<List<Shoe>>() {
            @Override
            public void onCompleted(List<Shoe> shoes) {
                shoesList.clear();
                shoesList.addAll(shoes);
                shoesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(Exception e) {
                // handle error
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.shoes_menu, menu);


        User user = SharedPreferencesUtil.getUser(this);
        if (user == null || !user.isAdmin()) {
            menu.removeItem(R.id.menu_admin);
            menu.removeItem(R.id.menu_add_shoe);
        }

        // טריק להצגת האייקונים
        try {
            Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", boolean.class);
            m.setAccessible(true);
            m.invoke(menu, true);
        } catch (Exception ignored) {
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_admin) {
            startActivity(new Intent(this, Admin.class));
            return true;
        } else if (id == R.id.menu_user) {
            startActivity(new Intent(this, user_info.class));
            return true;
        } else if (id == R.id.menu_add_shoe) {
            startActivity(new Intent(this, AddShoeActivity.class));
            return true;
        } else if (id == 123456) {

        }
        return super.onOptionsItemSelected(item);
    }
}
