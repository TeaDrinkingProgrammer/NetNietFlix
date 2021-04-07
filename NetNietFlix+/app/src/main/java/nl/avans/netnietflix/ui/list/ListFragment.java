package nl.avans.netnietflix.ui.list;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nl.avans.netnietflix.R;
import nl.avans.netnietflix.domain.MediaItem;
import nl.avans.netnietflix.domain.MediaItemList;
import nl.avans.netnietflix.ui.RecyclerView.MediaItemAdapter;

public class ListFragment extends Fragment {

    private ListViewModel listViewModel;
    private MediaItemListAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;
    private final String TAG = this.getClass().getSimpleName();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        listViewModel =
                new ViewModelProvider(this).get(ListViewModel.class);
        View root = inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView = root.findViewById(R.id.list_recycler_view);

        //Maakt 2 layoutmanagers

        //Bij portretoriëntatie, kies normale layout, bij landschap gridlayout
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(root.getContext(), LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(linearLayoutManager);
            Log.d(TAG, "linearLayoutManager is used");
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(root.getContext(), 2, GridLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(gridLayoutManager);
            Log.d(TAG, "gridLayoutManager is used");
        }
        recyclerViewAdapter = new MediaItemListAdapter(this.getContext());
        recyclerView.setAdapter(recyclerViewAdapter);

        listViewModel.getMediaItemLists().observe(getViewLifecycleOwner(), new Observer<List<MediaItemList>>() {
            @Override
            public void onChanged(@Nullable List<MediaItemList> mediaItemLists) {
                Log.d(TAG, "onChanged");
                recyclerViewAdapter.setMediaItemLists(mediaItemLists);
            }
        });
        return root;
    }
}