package com.example.project2.screens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.R;
import com.example.project2.adapters.ShoeColorAdapter;
import com.example.project2.models.ShoeColor;

import java.util.ArrayList;
import java.util.List;

public class AddShoeActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etShoeName,etPrice;
    Button btnPlus,addBtn;
    RadioButton btnMale,btnFemale,btSex;
    RecyclerView recyclerViewAddShoe;

    private List<ShoeColor> shoeColorList = new ArrayList<>();
    private ShoeColorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_shoe);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initview();


    }

    private void initview() {
        etShoeName=findViewById(R.id.etShoeName);
        etPrice=findViewById(R.id.etPrice);
        btnPlus=findViewById(R.id.btnPlus);
        addBtn=findViewById(R.id.addBtn);
        btnMale=findViewById(R.id.btnMale);
        btnFemale=findViewById(R.id.btnFemale);
        btSex=findViewById(R.id.btSex);
        recyclerViewAddShoe=findViewById(R.id.recyclerViewAddShoe);

        adapter = new ShoeColorAdapter(shoeColorList);
        recyclerViewAddShoe.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAddShoe.setAdapter(adapter);

        addBtn.setOnClickListener(this);
        btnPlus.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.addBtn) {
            createNewShoe();
            return;
        }
        if (view.getId() == R.id.btnPlus) {
            showDialogShoeColor();
            return;
        }
    }


    void createNewShoe() {

    }

    void showDialogShoeColor() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_color, null);
        builder.setView(dialogView);

        EditText colorNameEditText = dialogView.findViewById(R.id.colorNameEditText);
        EditText base64EditText = dialogView.findViewById(R.id.base64EditText);
        Button addColorButton = dialogView.findViewById(R.id.addColorButton);

        AlertDialog dialog = builder.create();

        addColorButton.setOnClickListener(v -> {
            String colorName = colorNameEditText.getText().toString().trim();
            String base64 = base64EditText.getText().toString().trim();

            if (!colorName.isEmpty() && !base64.isEmpty()) {
                shoeColorList.add(new ShoeColor(colorName, base64));
                adapter.notifyItemInserted(shoeColorList.size() - 1);
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}