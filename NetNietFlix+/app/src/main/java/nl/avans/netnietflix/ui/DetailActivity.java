package nl.avans.netnietflix.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import nl.avans.netnietflix.R;
import nl.avans.netnietflix.domain.MediaItem;

public class DetailActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();
    final static String INTENT_EXTRA_MEDIA_ITEM = "media_item";
    public static final String BASE_URL = "https://image.tmdb.org/t/p/w500";
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
                        .resize(200, 200)
                        .into(mImage);

                mOverview.setText(mediaItem.getOverview());
                String rating = mediaItem.getVoteAverage() + " ("+ mediaItem.getVoteCount() + ")";
                mRating.setText(rating);
                mWatchtime.setText("2h 03m"); //TODO Add new attribute in MediaItems
                mReleaseDate.setText(mediaItem.getReleaseDate());
            }
        }
    }
}