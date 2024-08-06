package com.example.livestockmanagement;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CowAdapter extends RecyclerView.Adapter<CowAdapter.CowViewHolder> {
    private final List<Cow> cowList;
    private final OnCowClickListener onCowClickListener;

    public CowAdapter(List<Cow> cowList, OnCowClickListener onCowClickListener) {
        this.cowList = cowList;
        this.onCowClickListener = onCowClickListener;
    }

    @NonNull
    @Override
    public CowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cow, parent, false);
        return new CowViewHolder(itemView, onCowClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CowViewHolder holder, int position) {
        Cow cow = cowList.get(position);
        holder.nameTextView.setText(cow.getName());
        holder.tagTextView.setText(cow.getTagNumber());
        holder.breedTextView.setText(cow.getBreed());
        holder.purposeTextView.setText(cow.getPurpose());
    }

    @Override
    public int getItemCount() {
        return cowList.size();
    }

    public static class CowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nameTextView;
        public TextView tagTextView;
        public TextView breedTextView;
        public TextView purposeTextView;
        OnCowClickListener onCowClickListener;

        public CowViewHolder(View itemView, OnCowClickListener onCowClickListener) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.cow_name);
            tagTextView = itemView.findViewById(R.id.tag);
            breedTextView = itemView.findViewById(R.id.breed);
            purposeTextView = itemView.findViewById(R.id.purpose);
            this.onCowClickListener = onCowClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onCowClickListener.onCowClick(getAdapterPosition());
        }
    }

    public interface OnCowClickListener {
        void onCowClick(int position);
    }
}
