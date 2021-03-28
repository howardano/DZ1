package ru.mail.park.dz_1;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MatrixOfNumbersFragment extends Fragment implements OnItemClicked {
    private List<Integer> data;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_matrix_of_numbers, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.feed);

        if (data == null && savedInstanceState == null) {
            data = new ArrayList<>();
            insert(data);
        } else {
            data = savedInstanceState.getIntegerArrayList("values");
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 3));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 4));
        }


        FeedAdapter adapter = new FeedAdapter(data, this);
        recyclerView.setAdapter(adapter);

        Button addNumber = view.findViewById(R.id.add);
        addNumber.setOnClickListener(v -> {
            data.add(data.get(data.size() - 1) + 1);
            adapter.notifyItemInserted(data.size() - 1);
            Toast.makeText(requireContext(), "Number was added!", Toast.LENGTH_SHORT).show();
        });
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        if (savedInstanceState == null) {
            savedInstanceState = new Bundle();
        }
        savedInstanceState.putIntegerArrayList("values", (ArrayList<Integer>)data);
    }

    public void insert(List<Integer> data) {
        for (int i = 1; i <= 100; i++) {
            data.add(i);
        }
    }

    @Override
    public void onItemClick(int value) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragInMain, new SelectedNumberFragment(value), "NUMBER_FRAGMENT");
        fragmentTransaction.commit();
    }
}