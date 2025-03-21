package com.example.project2.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.R;
import com.example.project2.models.User;
import com.example.project2.screens.UserDetailsActivity;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> userList;
    private OnDeleteClickListener onDeleteClickListener;
    private Context context;

    // Interface for delete button
    public interface OnDeleteClickListener {
        void onDeleteClick(String userId);
    }

    public UserAdapter(Context context, List<User> userList, OnDeleteClickListener onDeleteClickListener) {
        this.context = context;
        this.userList = userList;
        this.onDeleteClickListener = onDeleteClickListener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout for individual user items
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.userIdTextView.setText(user.getId());
        holder.userNameTextView.setText(user.getfName() + " " + user.getlName());

        // Set delete button functionality
        holder.deleteUserButton.setOnClickListener(v -> {
            if (onDeleteClickListener != null) {
                onDeleteClickListener.onDeleteClick(user.getId());
            }
        });

        // Open user details activity on item click
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, UserDetailsActivity.class);
            intent.putExtra("User", user);  // Pass the User object
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView userIdTextView;
        TextView userNameTextView;
        Button deleteUserButton;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            userIdTextView = itemView.findViewById(R.id.userIdTextView);
            userNameTextView = itemView.findViewById(R.id.userNameTextView);
            deleteUserButton = itemView.findViewById(R.id.deleteUserButton);
        }
    }

    // Method to remove user from the list when deleted
    public void removeUser(String userId) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId().equals(userId)) {
                userList.remove(i);
                notifyItemRemoved(i);
                break;
            }
        }
    }
}
