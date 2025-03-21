package com.example.project2.screens;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.R;
import com.example.project2.models.User;
import com.example.project2.services.AuthenticationService;
import com.example.project2.services.DatabaseService;
import com.example.project2.utils.SharedPreferencesUtil;
import com.google.android.material.textfield.TextInputEditText;

public class user_info extends AppCompatActivity {

    private ImageButton logoutButton, saveButton;
    private TextInputEditText tvetUserFirstName, tvetUserLastName, tvetUserEmail, tvetUserPassword;

    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info); // טעינת ה-XML הנכון

        // חיבור רכיבים ל-ID שלהם
        tvetUserFirstName = findViewById(R.id.tvet_user_first_name);
        tvetUserLastName = findViewById(R.id.tvet_user_last_name);
        tvetUserEmail = findViewById(R.id.tvet_user_email);
        tvetUserPassword = findViewById(R.id.tvet_user_password);
        logoutButton = findViewById(R.id.logoutButton);
        saveButton = findViewById(R.id.saveButton);

        String userId = AuthenticationService.getInstance().getCurrentUserId();
        if (userId == null) {
            Toast.makeText(this, "שגיאה בזיהוי המשתמש!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // טוען את פרטי המשתמש מה-Database
        DatabaseService.getInstance().getUser(userId, new DatabaseService.DatabaseCallback<User>() {
            @Override
            public void onCompleted(User user) {
                setView(user);
            }

            @Override
            public void onFailed(Exception e) {
                Toast.makeText(user_info.this, "שגיאה בטעינת הנתונים", Toast.LENGTH_SHORT).show();
            }
        });

        // לחיצה על כפתור שמירה
        saveButton.setOnClickListener(v -> {
            saveUserDetails();
        });

        // לחיצה על כפתור התנתקות
        logoutButton.setOnClickListener(v -> {
            AuthenticationService.getInstance().signOut();
            SharedPreferencesUtil.clearCart(this);

            // מעבר למסך ההתחברות
            Intent intent = new Intent(user_info.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }

    private void setView(User user) {
        this.user = user;
        tvetUserFirstName.setText(user.getfName());
        tvetUserLastName.setText(user.getlName());
        tvetUserEmail.setText(user.getEmail());
        tvetUserPassword.setText(user.getPassword()); // מומלץ לא להציג את הסיסמה ככה!
    }

    private void saveUserDetails() {
        if (this.user == null) return;
        String firstName = tvetUserFirstName.getText().toString();
        String lastName = tvetUserLastName.getText().toString();
        String email = tvetUserEmail.getText().toString();
        String password = tvetUserPassword.getText().toString();

        // עדכון פרטי המשתמש ב-Database
        if (!firstName.isEmpty() && !lastName.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
            this.user.setfName(firstName);
            this.user.setlName(lastName);
//            User updatedUser = new User(firstName, lastName, email, password);
            DatabaseService.getInstance().writeUser(this.user, new DatabaseService.DatabaseCallback<Void>() {
                @Override
                public void onCompleted(Void result) {
                    Toast.makeText(user_info.this, "הפרטים עודכנו בהצלחה", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailed(Exception e) {
                    Toast.makeText(user_info.this, "שגיאה בעדכון פרטי המשתמש", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(user_info.this, "יש למלא את כל השדות", Toast.LENGTH_SHORT).show();
        }
    }
}
