package ru.mail.park.dz1.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import ru.mail.park.dz1.R;

public class SelectedNumberFragment extends Fragment {

    private int value;
    private static final String VALUE_TAG = "value_tag";

    public static @NonNull SelectedNumberFragment newInstance(@NonNull Integer value) {
        SelectedNumberFragment fragment = new SelectedNumberFragment();
        Bundle args = new Bundle();
        args.putInt(VALUE_TAG, value);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            value = getArguments().getInt(VALUE_TAG, 0);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button = view.findViewById(R.id.back);
        TextView textView = view.findViewById(R.id.text);

        if (value % 2 == 0) {
            textView.setTextColor(Color.RED);
        } else {
            textView.setTextColor(Color.BLUE);
        }
        textView.setText(String.valueOf(value));

        button.setOnClickListener(v -> {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.popBackStack();
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_selected_number, container, false);
    }
}