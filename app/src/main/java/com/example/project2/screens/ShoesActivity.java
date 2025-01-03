package com.example.project2.screens;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.R;
import com.example.project2.adapters.ShoesAdapter;
import com.example.project2.models.Shoe;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ShoesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ShoesAdapter shoesAdapter;
    private List<Shoe> shoesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoes); // שם ה-XML של ה-Activity

        recyclerView = findViewById(R.id.shoesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // קריאה לקובץ JSON מתוך תיקיית assets
        String json = loadJSONFromAssets();

        // אם קובץ ה-JSON לא ריק, נפענח אותו
        if (json != null) {
            parseJSON(json);
        }

        // הגדרת ה-Adapter עם רשימת הנעליים
        shoesAdapter = new ShoesAdapter(this, shoesList);
        recyclerView.setAdapter(shoesAdapter);
    }

    private String loadJSONFromAssets() {
        String json = null;
        try {
            // פותח את קובץ ה-JSON מתוך תיקיית assets
            InputStream is = getAssets().open("shoes.json");

            // קורא את התוכן של הקובץ
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            // ממיר את תוכן הקובץ למיתרים (String)
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    // פונקציה לפענוח ה-JSON והמרת המידע לאובייקטים של Shoe
    private void parseJSON(String json) {
        try {
            // ממירים את המידע מ-JSON לאובייקטים של Shoe
            JSONObject jsonObject = new JSONObject(json);
            JSONArray shoesArray = jsonObject.getJSONArray("shoes");

            // עובר על כל נעל ומוסיף אותה לרשימה
            for (int i = 0; i < shoesArray.length(); i++) {
                JSONObject shoeObject = shoesArray.getJSONObject(i);
                String name = shoeObject.getString("name");
                double price = shoeObject.getDouble("price");
                String gender = shoeObject.getString("gender");
                String imageName = shoeObject.getString("image");

                // קריאת רשימת הצבעים
                JSONArray colorsArray = shoeObject.getJSONArray("colors");
                List<Integer> colorOptions = new ArrayList<>();
                for (int j = 0; j < colorsArray.length(); j++) {
                    String colorName = colorsArray.getString(j);
                    int colorResId = getResources().getIdentifier(colorName, "drawable", getPackageName());
                    colorOptions.add(colorResId);
                }

                // המרה לשם התמונה שנמצא ב-res/drawable
                int imageResId = getResources().getIdentifier(imageName, "drawable", getPackageName());

                // יוצרים אובייקט של Shoe ומוסיפים לרשימה
                Shoe shoe = new Shoe(name, price, gender, imageResId, colorOptions);
                shoesList.add(shoe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
