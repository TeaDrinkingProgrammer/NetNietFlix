package nl.avans.netnietflix.ui.detailList;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

import nl.avans.netnietflix.R;
import nl.avans.netnietflix.applogic.DataManager;
import nl.avans.netnietflix.domain.DetailMediaItemList;
import nl.avans.netnietflix.domain.DetailedMediaItem;
import nl.avans.netnietflix.domain.MediaItem;
import nl.avans.netnietflix.domain.Review;
import nl.avans.netnietflix.repository.API.DetailMediaItemListController;
import nl.avans.netnietflix.repository.API.DetailedMediaItemController;
import nl.avans.netnietflix.repository.API.ReviewController;
import nl.avans.netnietflix.ui.RecyclerView.MediaItemAdapter;

public class DetailListActivity extends AppCompatActivity implements DetailMediaItemListController.DetailMediaItemListListener {

    private final String TAG = getClass().getSimpleName();
    private TextView name;
    private TextView description;
    private DetailMediaItemList detailMediaItemList;
    private RecyclerView detailListRecyclerview;
    private MediaItemAdapter detailListMoviesAdapter = new MediaItemAdapter(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_list_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        detailListRecyclerview= findViewById(R.id.detail_list_recycler_view);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
            detailListRecyclerview.setLayoutManager(gridLayoutManager);
            Log.d(TAG, "linearLayoutManager is used");
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5, GridLayoutManager.VERTICAL, false);
            detailListRecyclerview.setLayoutManager(gridLayoutManager);
            Log.d(TAG, "gridLayoutManager is used");
        }
        detailListRecyclerview.setAdapter(detailListMoviesAdapter);
        //TODO Proper back button
        name = (TextView) findViewById(R.id.media_item_list_name) ;
        description = (TextView) findViewById(R.id.media_item_list_description) ;
        Intent intent = getIntent();

        if(intent != null) {
            if (intent.hasExtra("id")) {
               int id = intent.getExtras().getInt("id");
                DataManager dataManager = new DataManager();
                dataManager.getDetailItemList(this,id);
            }
        }
    }
    @Override
    public void onDetailMediaItemListAvailable(DetailMediaItemList mediaItemList) {
        this.detailMediaItemList = mediaItemList;
        name.setText(mediaItemList.getName());
        description.setText(mediaItemList.getDescription());
        if(detailMediaItemList.getMediaItemList().size() != 0) {
            detailListMoviesAdapter.setMediaItems(detailMediaItemList.getMediaItemList());
        }
    }
}