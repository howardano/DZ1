package ru.mail.park.dz1.fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.mail.park.dz1.R;
import ru.mail.park.dz1.adapter.FeedAdapter;
import ru.mail.park.dz1.adapter.OnItemClicked;

import static ru.mail.park.dz1.fragments.SelectedNumberFragment.newInstance;

public class MatrixOfNumbersFragment extends Fragment implements OnItemClicked {

    private List<Integer> data;
    private static final String VALUE_TAG = "value_tag";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
            data = savedInstanceState.getIntegerArrayList(VALUE_TAG);
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), requireContext().getResources().getInteger(R.integer.orientation_portrait)));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), requireContext().getResources().getInteger(R.integer.orientation_landscape)));
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
    public void onSaveInstanceState(@Nullable Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        if (savedInstanceState == null) {
            savedInstanceState = new Bundle();
        }
        savedInstanceState.putIntegerArrayList(VALUE_TAG, (ArrayList<Integer>) data);
    }

    public void insert(@NonNull List<Integer> data) {
        for (int i = 1; i <= 100; i++) {
            data.add(i);
        }
    }

    @Override
    public void onItemClick(int value) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        Fragment fragment = newInstance(value);
        fragmentTransaction.add(R.id.fragInMain, fragment, "NUMBER_FRAGMENT").addToBackStack("STACK");
        fragmentTransaction.commit();
    }
}