package nl.avans.netnietflix.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import nl.avans.netnietflix.R;

public class DetailActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();
    private final static String SAVED_INSTANCE_OF_CHARACTER ="character";
    private TextView mCharacterName;
    private TextView mNickname;
    private TextView mStatus;
    private TextView mBirthday;
    private ImageView mImage;
    private TextView mOccupation;
    private TextView mAppearance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

//        mCharacterName = (TextView) findViewById(R.id.character_detail_characterName);
//        mNickname = (TextView) findViewById(R.id.character_detail_nickname);
//        mStatus = (TextView) findViewById(R.id.character_detail_status);
//        mImage = (ImageView) findViewById(R.id.character_detail_image);
//        mBirthday = (TextView) findViewById(R.id.character_detail_birthday);
//        mOccupation = (TextView) findViewById(R.id.character_detail_occupation);
//        mAppearance = (TextView) findViewById(R.id.character_detail_appearance);

        Intent intentThatStartedThisActivity = getIntent();
    }
}