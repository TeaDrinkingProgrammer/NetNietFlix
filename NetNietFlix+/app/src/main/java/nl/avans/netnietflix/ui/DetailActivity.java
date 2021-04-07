package nl.avans.netnietflix.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.slider.Slider;
import com.squareup.picasso.Picasso;

import java.util.List;

import nl.avans.netnietflix.R;
import nl.avans.netnietflix.applogic.DataManager;
import nl.avans.netnietflix.domain.DetailedMediaItem;
import nl.avans.netnietflix.domain.MediaItem;
import nl.avans.netnietflix.domain.Review;
import nl.avans.netnietflix.repository.API.DetailedMediaItemController;
import nl.avans.netnietflix.repository.API.RatingController;
import nl.avans.netnietflix.repository.API.ReviewController;

public class DetailActivity extends AppCompatActivity implements DetailedMediaItemController.DetailedMediaItemListener, ReviewController.ReviewListener, RatingController.RatingListener {

    private final String TAG = getClass().getSimpleName();
    final static String INTENT_EXTRA_MEDIA_ITEM = "media_item";
    public static final String BASE_URL = "https://image.tmdb.org/t/p/w500";
    private DataManager dataManager;
    private DetailedMediaItem detailedMediaItem;
    private TextView mTitle;
    private ImageView mImage;
    private TextView mOverview;
    private TextView mRating;
    private TextView mReleaseDate;
    private TextView mWatchtime;
    private Button submitButton;
    private TextView ProgressLabel;
    private Slider slider;
    private Double savedValue;

    public DetailActivity(){
        dataManager = new DataManager();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        getSupportActionBar().hide();
        mTitle = (TextView) findViewById(R.id.media_item_title);
        mImage = (ImageView) findViewById(R.id.media_item_image);
        mOverview = (TextView) findViewById(R.id.media_item_overview);
        mRating = (TextView) findViewById(R.id.media_item_rating);
        mReleaseDate = (TextView) findViewById(R.id.media_item_release_date);
        mWatchtime = (TextView) findViewById(R.id.media_item_watchtime);
        submitButton = findViewById(R.id.submit_button);
        slider = findViewById(R.id.media_item_slider);
        ProgressLabel = findViewById(R.id.media_item_sheezer_text);
        ProgressLabel.setText("Rating 0.0");
        slider = (Slider) findViewById(R.id.media_item_slider);
        slider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                ProgressLabel.setText("Rating " + value);
                savedValue = Double.valueOf(value);
            }
        });



    Intent intent = getIntent();

        if(intent != null) {
            if (intent.hasExtra(INTENT_EXTRA_MEDIA_ITEM)) {
                MediaItem mediaItem = (MediaItem) intent.getExtras().getSerializable(INTENT_EXTRA_MEDIA_ITEM);
                dataManager.getMediaItemDetails(this,mediaItem.getId());
                dataManager.getReviewForMovieId(this,mediaItem.getId());
                mTitle.setText(mediaItem.getTitle());
                Log.d("Test", BASE_URL + mediaItem.getImgLink());
                Picasso.
                        get()
                        .load(BASE_URL + mediaItem.getImgLink())
//                        .resize(200, 200) //TODO Resize properly
                        .into(mImage);
//                mWatchtime.setText("113 m");
                mOverview.setText(mediaItem.getOverview());
                String rating = mediaItem.getVoteAverage() + " ("+ mediaItem.getVoteCount() + ")";
                mRating.setText(rating);
                mReleaseDate.setText(mediaItem.getReleaseDate());

                submitButton.setOnClickListener(new DetailActivityOnClickListener(this,mediaItem.getId()));
            }
        }

    }

    @Override
    public void onPost(Boolean isSuccessful) {
        String toast_msg = Resources.getSystem().getString(R.string.rating_added);
        Toast.makeText(this, toast_msg, Toast.LENGTH_SHORT).show();
    }

    class DetailActivityOnClickListener implements View.OnClickListener {
        RatingController.RatingListener listener;
        int mediaItemId;
        public DetailActivityOnClickListener(RatingController.RatingListener listener,int mediaItemId){
            this.listener = listener;
            this.mediaItemId = mediaItemId;
        }
        @Override
        public void onClick(View v) {
            dataManager.addRatingToMovie(listener,mediaItemId,savedValue);
        }

    }

    @Override
    public void onMediaItemsAvailable(DetailedMediaItem mediaItem) {
        detailedMediaItem = mediaItem;
        int t = Integer.valueOf(mediaItem.getWatchTime());
        int hours = t / 60;
        int minutes = t % 60;
        this.mWatchtime.setText(hours + "h " + minutes + "m");
    }

    @Override
    public void onReviewsAvailable(List<Review> reviews) {
    }

}