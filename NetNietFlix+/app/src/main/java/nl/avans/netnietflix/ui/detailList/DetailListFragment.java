package nl.avans.netnietflix.ui.detailList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import nl.avans.netnietflix.R;

public class DetailListFragment extends Fragment {

    private DetailListViewModel detailListViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        detailListViewModel =
                new ViewModelProvider(this).get(DetailListViewModel.class);
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        detailListViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });
        return root;
    }
}