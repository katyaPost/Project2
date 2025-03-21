package com.example.project2.screens;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.R;
import com.example.project2.models.User;
import com.example.project2.services.AuthenticationService;
import com.example.project2.services.DatabaseService;
import com.example.project2.utils.SharedPreferencesUtil;

public class Register extends AppCompatActivity implements View.OnClickListener {
    EditText etFName, etLName, etPhone, etEmail, etPass;
    Button btnReg;

    String fName, lName, phone, email, pass;

    AuthenticationService authenticationService;
    DatabaseService databaseService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init_views();

        authenticationService = AuthenticationService.getInstance();
        databaseService = DatabaseService.getInstance();
    }

    private void init_views() {
        btnReg = findViewById(R.id.btnReg);
        etFName = findViewById(R.id.etFname);
        etLName = findViewById(R.id.etLname);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);

        btnReg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        fName = etFName.getText().toString();
        lName = etLName.getText().toString();
        phone = etPhone.getText().toString();
        email = etEmail.getText().toString();
        pass = etPass.getText().toString();


        //check if registration is valid
        boolean isValid = true;
        if (fName.length() < 2) {
            etFName.setError("שם פרטי קצר מדי");
            isValid = false;
        }
        if (lName.length() < 2) {
            Toast.makeText(Register.this, "שם משפחה קצר מדי", Toast.LENGTH_LONG).show();
            isValid = false;
        }
        if (phone.length() < 9 || phone.length() > 10) {
            Toast.makeText(Register.this, "מספר הטלפון לא תקין", Toast.LENGTH_LONG).show();
            isValid = false;
        }

        if (!email.contains("@")) {
            Toast.makeText(Register.this, "כתובת האימייל לא תקינה", Toast.LENGTH_LONG).show();
            isValid = false;
        }
        if (pass.length() < 6) {
            Toast.makeText(Register.this, "הסיסמה קצרה מדי", Toast.LENGTH_LONG).show();
            isValid = false;
        }
        if (pass.length() > 20) {
            Toast.makeText(Register.this, "הסיסמה ארוכה מדי", Toast.LENGTH_LONG).show();
            isValid = false;
        }

        if (!isValid) {
            return;
        }

        authenticationService.signUp(email, pass, new AuthenticationService.AuthCallback<String>() {
            @Override
            public void onCompleted(String uid) {
                User newUser = new User(uid, fName, lName, phone, email, pass, false);
                databaseService.writeUser(newUser, new DatabaseService.DatabaseCallback<Void>() {
                    @Override
                    public void onCompleted(Void object) {
                        SharedPreferencesUtil.saveUser(Register.this, newUser);
                        Intent goLog = new Intent(getApplicationContext(), ShoesActivity.class);
                        startActivity(goLog);
                    }

                    @Override
                    public void onFailed(Exception e) {
                        Log.w("TAG", "createNewUser:failure", e);
                        Toast.makeText(Register.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onFailed(Exception e) {
                Log.w("TAG", "createUserWithEmail:failure", e);
                Toast.makeText(Register.this, "Authentication failed.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}









