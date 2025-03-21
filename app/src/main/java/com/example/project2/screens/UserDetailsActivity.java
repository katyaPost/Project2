package com.example.project2.screens;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.R;
import com.example.project2.models.User;
import com.example.project2.services.DatabaseService;

public class UserDetailsActivity extends AppCompatActivity {

    private TextView userIdTextView;
    private TextView userNameTextView;
    private TextView userPhoneTextView;
    private TextView userEmailTextView;
    private Button deleteUserButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_details);
        // Initialize views
        userIdTextView = findViewById(R.id.userIdTextView);
        userNameTextView = findViewById(R.id.userNameTextView);
        userPhoneTextView = findViewById(R.id.userPhoneTextView);
        userEmailTextView = findViewById(R.id.userEmailTextView);
        deleteUserButton = findViewById(R.id.deleteUserButton);

        // Initialize Firebase database reference

        // Get user object from the Intent
        Intent intent = getIntent();
        User user = intent.getSerializableExtra("user", User.class);

        if (user == null) {
            Toast.makeText(this, "User data not available", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity if no user data is available
            return;
        }
            // Set user details in the TextViews
        userIdTextView.setText("ID: " + user.getId());
        userNameTextView.setText("Name: " + user.getfName() + " " + user.getlName());
        userPhoneTextView.setText("Phone: " + user.getPhone());
        userEmailTextView.setText("Email: " + user.getEmail());

        // Delete user functionality
        DatabaseService.getInstance().deleteUser(user.getId(), new DatabaseService.DatabaseCallback<Void>() {
            @Override
            public void onCompleted(Void object) {

                finish();
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }
}