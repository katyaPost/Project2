package com.example.project2.screens;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.project2.R;
import com.example.project2.adapters.ShoeColorAdapter;
import com.example.project2.models.Shoe;
import com.example.project2.models.ShoeColor;
import com.example.project2.services.DatabaseService;
import com.example.project2.utils.ImageUtil;

import java.util.ArrayList;
import java.util.List;

public class AddShoeActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etShoeName, etPrice;
    RadioGroup radioGroupGender;
    Button btnPlus, addBtn;
    RecyclerView recyclerViewAddShoe;

    private List<ShoeColor> shoeColorList = new ArrayList<>();
    private ShoeColorAdapter adapter;

    private ActivityResultLauncher<Intent> selectImageLauncher;
    private ImageView shoeImageViewReference;

    DatabaseService databaseService;



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

        databaseService = DatabaseService.getInstance();

        initView();


        selectImageLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri selectedImage = result.getData().getData();
                        if (shoeImageViewReference != null) {
                            shoeImageViewReference.setImageURI(selectedImage);
                        }
                    }
                }
        );


    }

    private void initView() {
        etShoeName = findViewById(R.id.etShoeName);
        etPrice = findViewById(R.id.etPrice);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        btnPlus = findViewById(R.id.btnPlus);
        addBtn = findViewById(R.id.addBtn);
        recyclerViewAddShoe = findViewById(R.id.recyclerViewAddShoe);

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
        } else if (view.getId() == R.id.btnPlus) {
            showDialogShoeColor();
        }
    }

    void createNewShoe() {
        String id = databaseService.generateShoeId();
        String name = etShoeName.getText().toString();
        String stPrice = etPrice.getText().toString();
        double price = stPrice.isEmpty() ? 0 : Double.parseDouble(stPrice);

        int radioButtonID = radioGroupGender.getCheckedRadioButtonId();
        View radioButtonView = radioGroupGender.findViewById(radioButtonID);
        int idx = radioGroupGender.indexOfChild(radioButtonView);
        RadioButton radioButton = (RadioButton) radioGroupGender.getChildAt(idx);
        String gender = radioButton.getText().toString();

        Shoe shoe = new Shoe(id, name, price, gender, shoeColorList);

        databaseService.createNewShoe(shoe, new DatabaseService.DatabaseCallback<Void>() {
            @Override
            public void onCompleted(Void object) {
                Toast.makeText(AddShoeActivity.this, "yayyyy!!!!!!", Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }

    void showDialogShoeColor() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_color, null);
        builder.setView(dialogView);

        EditText colorNameEditText = dialogView.findViewById(R.id.colorNameEditText);
        ImageView shoeImageView = dialogView.findViewById(R.id.shoeImageView);
        Button addColorButton = dialogView.findViewById(R.id.addColorButton);

        shoeImageViewReference = shoeImageView;

        AlertDialog dialog = builder.create();

        shoeImageView.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            selectImageLauncher.launch(intent);
        });

        addColorButton.setOnClickListener(v -> {
            String colorName = colorNameEditText.getText().toString().trim();

            if (colorName.isEmpty()) {
                colorNameEditText.setError("Enter a color name");
                return;
            }

            String imageBase64 = ImageUtil.convertTo64Base(shoeImageView);
            if (imageBase64 == null) {
                return;
            }

            shoeColorList.add(new ShoeColor(colorName, imageBase64));
            adapter.notifyItemInserted(shoeColorList.size() - 1);
            dialog.dismiss();
            shoeImageViewReference = null;
        });



        dialog.show();
    }
}
