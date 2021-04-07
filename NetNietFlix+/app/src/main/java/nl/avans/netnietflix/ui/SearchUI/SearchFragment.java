package nl.avans.netnietflix.ui.SearchUI;

import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import nl.avans.netnietflix.R;
import nl.avans.netnietflix.domain.MediaItem;
import nl.avans.netnietflix.ui.RecyclerView.MediaItemAdapter;

public class SearchFragment extends Fragment {

    private String TAG = this.getClass().getSimpleName();
    private SearchViewModel searchViewModel;
    private RecyclerView searchRecyclerview;
    private MediaItemAdapter searchMoviesAdapter;
    private List<MediaItem> localMediaItems = new ArrayList<MediaItem>();
    private String query = "shrek";
    private EditText searchText;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchViewModel =
                new ViewModelProvider(this).get(SearchViewModel.class);

        View root = inflater.inflate(R.layout.fragment_search, container, false);
        searchMoviesAdapter = new MediaItemAdapter(root.getContext());

        searchRecyclerview = root.findViewById(R.id.search_recycler_view);
        searchText = root.findViewById(R.id.search_bar);

        searchViewModel.loadSearchMediaItems(query);
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //
            }

            private Timer timer = new Timer();
            private final long DELAY = 1000; // miliseconds

            @Override
            public void afterTextChanged(Editable s) {
                timer.cancel();
                timer = new Timer();
                timer.schedule(
                        new TimerTask() {
                            @Override
                            public void run() {
                                query = s.toString().toLowerCase();
                                Log.d(TAG, query);
                                searchViewModel.loadSearchMediaItems(query);
                            }
                        },
                        DELAY
                );
            }
        });

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(root.getContext(), 3, GridLayoutManager.VERTICAL, false);
            searchRecyclerview.setLayoutManager(gridLayoutManager);
            Log.d(TAG, "linearLayoutManager is used");
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(root.getContext(), 5, GridLayoutManager.VERTICAL, false);
            searchRecyclerview.setLayoutManager(gridLayoutManager);
            Log.d(TAG, "gridLayoutManager is used");
        }
        searchRecyclerview.setAdapter(searchMoviesAdapter);
        searchViewModel.getSearchMediaItems().observe(getViewLifecycleOwner(), new Observer<List<MediaItem>>() {
            @Override
            public void onChanged(@Nullable List<MediaItem> mediaItems) {
                if(mediaItems.size() != 0) {
                    localMediaItems.clear();
                    localMediaItems.addAll(mediaItems);
//                searchViewModel.loadSearchMediaItems(query);
                    searchMoviesAdapter.setMediaItems(localMediaItems);
                    searchMoviesAdapter.notifyDataSetChanged();
                } else {
                    searchViewModel.loadSearchMediaItems("shrek");
                }
            }
        });



        return root;
    }
}