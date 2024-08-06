package com.example.livestockmanagement;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MilkRecordAdapter extends RecyclerView.Adapter<MilkRecordAdapter.MilkRecordViewHolder> {

    private final List<MilkRecord> milkRecordList;

    public MilkRecordAdapter(List<MilkRecord> milkRecordList) {
        this.milkRecordList = milkRecordList;
    }

    @NonNull
    @Override
    public MilkRecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_milk_record, parent, false);
        return new MilkRecordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MilkRecordViewHolder holder, int position) {
        MilkRecord milkRecord = milkRecordList.get(position);
        holder.dateTextView.setText(milkRecord.getDate());
        holder.morningTextView.setText(String.valueOf(milkRecord.getMorning()));
        holder.afternoonTextView.setText(String.valueOf(milkRecord.getAfternoon()));
        holder.eveningTextView.setText(String.valueOf(milkRecord.getEvening()));
        holder.totalTextView.setText(String.valueOf(milkRecord.getTotal()));
    }

    @Override
    public int getItemCount() {
        return milkRecordList.size();
    }

    public List<MilkRecord> getMilkRecordList() {
        return milkRecordList;
    }

    static class MilkRecordViewHolder extends RecyclerView.ViewHolder {

        TextView dateTextView, morningTextView, afternoonTextView, eveningTextView, totalTextView;

        MilkRecordViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            morningTextView = itemView.findViewById(R.id.morningTextView);
            afternoonTextView = itemView.findViewById(R.id.afternoonTextView);
            eveningTextView = itemView.findViewById(R.id.eveningTextView);
            totalTextView = itemView.findViewById(R.id.totalTextView);
        }
    }
}