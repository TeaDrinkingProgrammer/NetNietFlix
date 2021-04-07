package nl.avans.netnietflix.ui.home;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class HomeFragment extends Fragment {

    private final String TAG = "Main Activity";
    private final String SAVED_CHARACTER_LIST = "characterList";
    private RecyclerView trendingRecyclerview;
    private RecyclerView topRatedRecyclerview;
    private RecyclerView watchLaterRecyclerview;
    private MediaItemAdapter trendingMoviesAdapter;
    private MediaItemAdapter topRatedMoviesAdapter;
    private MediaItemAdapter watchLaterAdapter;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        trendingRecyclerview = root.findViewById(R.id.trending_recycler_view);
        topRatedRecyclerview = root.findViewById(R.id.top_rated_recycler_view);
        watchLaterRecyclerview = root.findViewById(R.id.watch_later_recycler_view);

        //Maakt 2 layoutmanagers

        //Bij portretoriëntatie, kies normale layout, bij landschap gridlayout
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false);
            LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false);
            LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false);
            trendingRecyclerview.setLayoutManager(linearLayoutManager);
            topRatedRecyclerview.setLayoutManager(linearLayoutManager2);
            watchLaterRecyclerview.setLayoutManager(linearLayoutManager3);
            Log.d(TAG, "linearLayoutManager is used");
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(root.getContext(), 2, GridLayoutManager.HORIZONTAL, false);
            GridLayoutManager gridLayoutManager2 = new GridLayoutManager(root.getContext(), 2, GridLayoutManager.HORIZONTAL, false);
            GridLayoutManager gridLayoutManager3 = new GridLayoutManager(root.getContext(), 2, GridLayoutManager.HORIZONTAL, false);
            trendingRecyclerview.setLayoutManager(gridLayoutManager);
            topRatedRecyclerview.setLayoutManager(gridLayoutManager2);
            watchLaterRecyclerview.setLayoutManager(gridLayoutManager3);
            Log.d(TAG, "gridLayoutManager is used");
        }

        trendingMoviesAdapter = new MediaItemAdapter(this.getContext());
        topRatedMoviesAdapter = new MediaItemAdapter(this.getContext());
        watchLaterAdapter = new MediaItemAdapter(this.getContext());

        trendingRecyclerview.setAdapter(trendingMoviesAdapter);
        topRatedRecyclerview.setAdapter(topRatedMoviesAdapter);
        watchLaterRecyclerview.setAdapter(watchLaterAdapter);

        homeViewModel.getTrendingMediaItems().observe(getViewLifecycleOwner(), new Observer<List<MediaItem>>() {
            @Override
            public void onChanged(@Nullable List<MediaItem> mediaItems) {
                Log.d(TAG, "onChanged");
                    trendingMoviesAdapter.setMediaItems(mediaItems);
            }
        });

        homeViewModel.getTopRatedMediaItems().observe(getViewLifecycleOwner(), new Observer<List<MediaItem>>() {
            @Override
            public void onChanged(@Nullable List<MediaItem> mediaItems) {
                Log.d(TAG, "onChanged");
                topRatedMoviesAdapter.setMediaItems(mediaItems);
            }
        });
        homeViewModel.getWatchLaterMediaItems().observe(getViewLifecycleOwner(), new Observer<List<MediaItem>>() {
            @Override
            public void onChanged(@Nullable List<MediaItem> mediaItems) {
                Log.d(TAG, "onChanged");
                watchLaterAdapter.setMediaItems(mediaItems);
            }
        });


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        //Maakt het optiemenu aan en zet het "main menu" menu erin
//        Log.d(TAG,"onCreateOptionsMenu has been called");
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.main_menu,menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        //verwerkt de input van de filters
//        Log.d(TAG,"onOptionsItemSelected has been called");
//        switch (item.getItemId()) {
//            case R.id.filter_all:
//                trendingMoviesAdapter.resetCharactersInRecyclerView();
//                return true;
//            case R.id.filter_alive:
//                trendingMoviesAdapter.filterOnStatus("Alive");
//                return true;
//            case R.id.filter_deceased:
//                trendingMoviesAdapter.filterOnStatus("Deceased");
//                return true;
//            case R.id.filter_presumed_dead:
//                trendingMoviesAdapter.filterOnStatus("Presumed dead");
//                return true;
//        }
//        return false;
//    }
        return root;
    }
}