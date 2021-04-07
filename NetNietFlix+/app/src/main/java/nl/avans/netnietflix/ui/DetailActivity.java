package nl.avans.netnietflix.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import nl.avans.netnietflix.R;
import nl.avans.netnietflix.applogic.DataManager;
import nl.avans.netnietflix.domain.DetailedMediaItem;
import nl.avans.netnietflix.domain.MediaItem;
import nl.avans.netnietflix.domain.Review;
import nl.avans.netnietflix.repository.API.DetailedMediaItemController;
import nl.avans.netnietflix.repository.API.ReviewController;

public class DetailActivity extends AppCompatActivity implements DetailedMediaItemController.DetailedMediaItemListener, ReviewController.ReviewListener {

    private final String TAG = getClass().getSimpleName();
    final static String INTENT_EXTRA_MEDIA_ITEM = "media_item";
    public static final String BASE_URL = "https://image.tmdb.org/t/p/w500";
    private DetailedMediaItem detailedMediaItem;
    private TextView mTitle;
    private ImageView mImage;
    private TextView mOverview;
    private TextView mRating;
    private TextView mReleaseDate;
    private TextView mWatchtime;
    private TextView ProgressLabel;
    private TextView mReviewTitle1;
    private TextView mReviewDescription1;
    private TextView mReviewTitle2;
    private TextView mReviewDescription2;

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
        mReviewTitle1 = (TextView) findViewById(R.id.media_item_review_title_1);
        mReviewDescription1 = (TextView) findViewById(R.id.media_item_review_description_1);
        mReviewTitle2 = (TextView) findViewById(R.id.media_item_review_title_2);
        mReviewDescription2 = (TextView) findViewById(R.id.media_item_review_description_2);
        SeekBar seekBar = findViewById(R.id.media_item_seekbar);
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        double progress = seekBar.getProgress();
        ProgressLabel = findViewById(R.id.media_item_sheezer_text);
        ProgressLabel.setText("Rating " + progress/10.0);


    Intent intent = getIntent();

        if(intent != null) {
            if (intent.hasExtra(INTENT_EXTRA_MEDIA_ITEM)) {
                MediaItem mediaItem = (MediaItem) intent.getExtras().getSerializable(INTENT_EXTRA_MEDIA_ITEM);
                DataManager dataManager = new DataManager();
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
            }
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
        if(reviews.size() > 0){
            Review review = reviews.get(0);
            mReviewTitle1.setText("A review by " + review.getAuthor() + " | " + review.getRating());
            mReviewDescription1.setText(review.getContent());
            if(reviews.size() > 1){
                Review review2 = reviews.get(1);
                mReviewTitle2.setText("A review by " + review2.getAuthor() + " | " + review2.getRating());
                mReviewDescription2.setText(review2.getContent());
            }
        }

    }

    SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            ProgressLabel.setText("Rating " + progress/10.0);
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // called when the user first touches the SeekBar
        }
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // called after the user finishes moving the SeekBar
        }
    };
}