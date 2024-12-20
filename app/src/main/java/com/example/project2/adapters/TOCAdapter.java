/*package com.example.project2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.R;

import java.util.List;//

/*public class TOCAdapter extends RecyclerView.Adapter<TOCAdapter.TOCViewHolder> {

    private List<String> tocList;

    // Constructor for Adapter
    public TOCAdapter(List<String> tocList) {
        this.tocList = tocList;
    }

    @Override
    public TOCViewHolder onCreateViewHolder(ViewGroup parent, int viewType) //Inflates the layout for each item (item_toc.xml).
    {
        // Inflate the item layout (item_toc.xml)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.toc_item, parent, false);
        return new TOCViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TOCViewHolder holder, int position) //Binds the data (chapter title) to the view (TextView).

    {
        // Bind the data (chapter titles) to the views
        String title = tocList.get(position);
        holder.titleTextView.setText(title);
    }

    @Override
    public int getItemCount() //Returns the number of items in the list.
    {
        return tocList.size();
    }

    // ViewHolder class to hold the views for each item
    public static class TOCViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;

        public TOCViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.toc_item_title);
        }
    }
}
*/