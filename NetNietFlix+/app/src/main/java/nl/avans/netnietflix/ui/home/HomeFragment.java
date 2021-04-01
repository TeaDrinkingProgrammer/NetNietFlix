package nl.avans.netnietflix.ui.home;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

import nl.avans.netnietflix.R;
import nl.avans.netnietflix.repository.API.APIcontroller;
import nl.avans.netnietflix.domain.MediaItem;
import nl.avans.netnietflix.ui.RecyclerView.MediaItemAdapter;

public class HomeFragment extends Fragment implements APIcontroller.MediaItemControllerListener {

    private HomeViewModel homeViewModel;

    private final String TAG = "Main Activity";
    private final String SAVED_CHARACTER_LIST = "characterList";
    private RecyclerView trendingRecyclerview;
    private RecyclerView topRatedRecyclerview;
    private MediaItemAdapter trendingMoviesAdapter;
    private MediaItemAdapter topRatedMoviesAdapter;
    private List<MediaItem> mediaItems = null;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        trendingRecyclerview = root.findViewById(R.id.trending_recycler_view);
        topRatedRecyclerview = root.findViewById(R.id.top_rated_recycler_view);

        //Maakt 2 layoutmanagers

        //Bij portretoriëntatie, kies normale layout, bij landschap gridlayout
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL,false);
            LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL,false);
            trendingRecyclerview.setLayoutManager(linearLayoutManager);
            topRatedRecyclerview.setLayoutManager(linearLayoutManager2);
            Log.d(TAG,"linearLayoutManager is used");
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(root.getContext(),2, GridLayoutManager.HORIZONTAL,false);
            GridLayoutManager gridLayoutManager2 = new GridLayoutManager(root.getContext(),2, GridLayoutManager.HORIZONTAL,false);
            trendingRecyclerview.setLayoutManager(gridLayoutManager);
            topRatedRecyclerview.setLayoutManager(gridLayoutManager2);
            Log.d(TAG,"gridLayoutManager is used");
        }
        //CharacterAPITask apiTask = new CharacterAPITask(this);
        //apiTask.execute();

        //Als de savedInstanceState gevuld is gebruik die ipv VolleyTask opvragen (al heeft die ook chaching)
        if(savedInstanceState != null){
            Log.d(TAG,"savedInstanceState is used");
            mediaItems = (List<MediaItem>) savedInstanceState.getSerializable(SAVED_CHARACTER_LIST);
            trendingMoviesAdapter = new MediaItemAdapter(mediaItems,root.getContext());
            trendingRecyclerview.setAdapter(trendingMoviesAdapter);
            trendingMoviesAdapter = new MediaItemAdapter(mediaItems,root.getContext());
            trendingRecyclerview.setAdapter(trendingMoviesAdapter);
        }else {
            //Voert een volleyRequest uit dmv constructor(zie VolleyTask). Geeft een response door aan onResponseVolleyTask via een listener.
            // Call API request
            APIcontroller mediaItemController = new APIcontroller(this,this.getContext());
            mediaItemController.loadTrendingMoviesPerWeek();
            mediaItemController.loadTopRatedMoviesPerWeek();
            Log.d(TAG, "VolleyRequest is executed");
        }

        return root;
    }

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

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d(TAG,"onSaveInstanceState is called");
        super.onSaveInstanceState(outState);
        //Slaat de characterList op in de SavedInstanceState.
        if(mediaItems != null){
            Log.d(TAG,"Characterlist is put in outstate");
            outState.putSerializable(SAVED_CHARACTER_LIST,(Serializable) mediaItems);
        }
    }

    @Override
    public void onMediaItemsAvailable(List<MediaItem> mediaItems, Context context,String callName) {
        if(callName.equals(APIcontroller.GET_TRENDING_MOVIES)){
            this.mediaItems = mediaItems;
            trendingMoviesAdapter = new MediaItemAdapter(mediaItems,context);
            trendingRecyclerview.setAdapter(trendingMoviesAdapter);
        } else {
            topRatedMoviesAdapter = new MediaItemAdapter(mediaItems,context);
            topRatedRecyclerview.setAdapter(topRatedMoviesAdapter);
        }

    }
}