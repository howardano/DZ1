package ru.mail.park.dz1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.mail.park.dz1.R;

public class FeedAdapter extends RecyclerView.Adapter<NumbersViewHolder> {

    private final List<Integer> numbers;
    private final OnItemClicked clicked;

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
        holder.getNumber().setOnClickListener(v -> clicked.onItemClick(current));
    }

    @Override
    public int getItemCount() {
        return numbers.size();
    }

}
