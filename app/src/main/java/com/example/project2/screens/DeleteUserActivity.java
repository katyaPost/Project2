package com.example.project2.screens;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.R;
import com.example.project2.adapters.UserAdapter;
import com.example.project2.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DeleteUserActivity extends AppCompatActivity {
    private UserAdapter userAdapter;
    private List<User> userList;
    private DatabaseReference databaseReference;
    ValueEventListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_delete_user);
        // Initialize Firebase database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        // Initialize the RecyclerView and List
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        userList = new ArrayList<>();
        userAdapter = new UserAdapter(this, userList, userId -> deleteUser(userId)); // Use a lambda

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(userAdapter);

        // Fetch users and display them
        fetchUsers();
    }

    private void fetchUsers() {
        listener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Clear previous data
                userList.clear();

                // Iterate through all the users in Firebase and add them to the list
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    if (user != null) {
                        userList.add(user);
                    }
                }

                // Notify the adapter that data has changed
                userAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(DeleteUserActivity.this, "Failed to load users: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteUser(String userId) {
        databaseReference.child(userId).removeValue().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(DeleteUserActivity.this, "User deleted successfully", Toast.LENGTH_SHORT).show();
                // Optionally, update the RecyclerView list after deletion
                fetchUsers();
            } else {
                Toast.makeText(DeleteUserActivity.this, "Failed to delete user: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        databaseReference.removeEventListener(listener);
        super.onDestroy();
    }
}