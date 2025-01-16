package com.example.project2.screens;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project2.R;

public class LogIn extends AppCompatActivity implements View.OnClickListener {
    EditText etEmail, etPassword;
    Button btnLog;
    String email, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initviews();
    }

    private void initviews() {
        etEmail = findViewById(R.id.etEmailLog);
        etPassword = findViewById(R.id.etPassLog);
        btnLog = findViewById(R.id.btnLogin);
        btnLog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        email = etEmail.getText().toString().trim();
        pass = etPassword.getText().toString().trim();

        // לוודא שהמייל והסיסמה לא ריקים
        if (email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show();
            return;
        }

        // כאן תוכל להוסיף את הקוד כדי לבדוק את הנתונים עם מערכת אחרת (כגון שרת)
        // לדוגמה, אם המייל והסיסמה נכונים, נעבור למסך הבא
        if (email.equals("user@example.com") && pass.equals("password123")) {
            Log.d("TAG", "Login success");
            Intent go = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(go);
        } else {
            Log.w("TAG", "Login failed");
            Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
        }
    }
}
