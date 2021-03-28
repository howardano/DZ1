package ru.mail.park.dz_1;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class NumbersViewHolder extends RecyclerView.ViewHolder {

    private final TextView number;
    public NumbersViewHolder(@NonNull View itemView) {
        super(itemView);
        number = itemView.findViewById(R.id.number);
    }

    public void bind(int value) {
        if (value % 2 == 0) {number.setTextColor(Color.RED);}
        else {number.setTextColor(Color.BLUE);}
        number.setText(String.valueOf(value));
    }

    public TextView getNumber() {
        return number;
    }
}