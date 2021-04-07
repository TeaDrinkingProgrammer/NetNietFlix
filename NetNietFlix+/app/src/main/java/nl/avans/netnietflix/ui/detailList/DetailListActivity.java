package nl.avans.netnietflix.ui.detailList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

public class DetailListActivity extends AppCompatActivity implements DetailMediaItemListController.DetailMediaItemListListener {

    private final String TAG = getClass().getSimpleName();
    private TextView name;
    private DetailMediaItemList detailMediaItemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_list_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //TODO Proper back button
        name = (TextView) findViewById(R.id.media_item_list_name) ;

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
    }
}