package nl.avans.netnietflix.ui.SearchUI;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import nl.avans.netnietflix.ui.RecyclerView.MediaItemAdapter;

public class SearchFragment extends Fragment {

    private String TAG = this.getClass().getSimpleName();
    private SearchViewModel searchViewModel;
    private RecyclerView searchRecyclerview;
    private MediaItemAdapter searchMoviesAdapter;
    private String query = "killer";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchViewModel =
                new ViewModelProvider(this).get(SearchViewModel.class);

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        searchRecyclerview = root.findViewById(R.id.search_recycler_view);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(root.getContext(), LinearLayoutManager.VERTICAL, false);
            searchRecyclerview.setLayoutManager(linearLayoutManager);
            Log.d(TAG, "linearLayoutManager is used");
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(root.getContext(), 2, GridLayoutManager.HORIZONTAL, false);
            searchRecyclerview.setLayoutManager(gridLayoutManager);
            Log.d(TAG, "gridLayoutManager is used");
        }

        searchMoviesAdapter = new MediaItemAdapter(this.getContext());

        searchRecyclerview.setAdapter(searchMoviesAdapter);

        searchViewModel.getSearchMediaItems(query).observe(getViewLifecycleOwner(), new Observer<List<MediaItem>>() {
            @Override
            public void onChanged(@Nullable List<MediaItem> mediaItems) {
                Log.d(TAG, mediaItems.get(0).toString());
                searchMoviesAdapter.setMediaItems(mediaItems);
            }
        });
        return root;
    }
}