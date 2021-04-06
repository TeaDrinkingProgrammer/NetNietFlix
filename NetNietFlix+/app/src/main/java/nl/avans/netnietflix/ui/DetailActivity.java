package nl.avans.netnietflix.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import nl.avans.netnietflix.R;
import nl.avans.netnietflix.domain.DetailedMediaItem;
import nl.avans.netnietflix.domain.MediaItem;
import nl.avans.netnietflix.domain.Review;
import nl.avans.netnietflix.repository.API.DetailedMediaItemController;
import nl.avans.netnietflix.repository.API.ReviewController;

public class DetailActivity extends AppCompatActivity implements DetailedMediaItemController.DetailedMediaItemListener, ReviewController.ReviewListener {

    private final String TAG = getClass().getSimpleName();
    final static String INTENT_EXTRA_MEDIA_ITEM = "media_item";
    public static final String BASE_URL = "https://image.tmdb.org/t/p/w500";
    private DetailedMediaItem mediaItem;
    private TextView mTitle;
    private ImageView mImage;
    private TextView mOverview;
    private TextView mRating;
    private TextView mWatchtime;
    private TextView mReleaseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTitle = (TextView) findViewById(R.id.media_item_title) ;
        mImage = (ImageView) findViewById(R.id.media_item_image);
        mOverview = (TextView) findViewById(R.id.media_item_overview);
        mRating = (TextView) findViewById(R.id.media_item_rating);
        mWatchtime = (TextView) findViewById(R.id.media_item_watchtime);
        mReleaseDate = (TextView) findViewById(R.id.media_item_release_date);

        Intent intent = getIntent();

        if(intent != null) {
            if (intent.hasExtra(INTENT_EXTRA_MEDIA_ITEM)) {
                MediaItem mediaItem = (MediaItem) intent.getExtras().getSerializable(INTENT_EXTRA_MEDIA_ITEM);
                mTitle.setText(mediaItem.getTitle());
                Log.d("Test", BASE_URL + mediaItem.getImgLink());
                Picasso.
                        get()
                        .load(BASE_URL + mediaItem.getImgLink())
//                        .resize(200, 200) //TODO Resize properly
                        .into(mImage);

                mOverview.setText(mediaItem.getOverview());
                String rating = mediaItem.getVoteAverage() + " ("+ mediaItem.getVoteCount() + ")";
                mRating.setText(rating);
                mWatchtime.setText("2h 03m");
                mReleaseDate.setText(mediaItem.getReleaseDate());
            }
        }
    }
    @Override
    public void onMediaItemsAvailable(DetailedMediaItem mediaItem) {
        this.mediaItem = mediaItem;
        Log.d(TAG,"Lorem Ipsum");
    }

    @Override
    public void onReviewsAvailable(List<Review> reviews) {
    }
}