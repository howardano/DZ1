package ru.mail.park.dz_1;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

class FeedAdapter extends RecyclerView.Adapter<NumbersViewHolder> {

    private final List<Integer> numbers;
    private OnItemClicked clicked;

    public FeedAdapter(List<Integer> numbers, OnItemClicked clicked) {
        this.numbers = numbers;
        this.clicked = clicked;
    }

    @NonNull
    @Override
    public NumbersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemcard, parent, false);
        return new NumbersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NumbersViewHolder holder, int position) {
        Integer current = numbers.get(position);
        holder.bind(current);
        holder.getNumber().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked.onItemClick(current);

            }
        });

    }

    @Override
    public int getItemCount() {
        return numbers.size();
    }

}
