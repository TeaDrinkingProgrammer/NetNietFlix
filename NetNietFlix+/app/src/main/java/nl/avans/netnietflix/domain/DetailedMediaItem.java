package nl.avans.netnietflix.domain;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "DetailedMediaItem")
public class DetailedMediaItem extends MediaItem{
    @SerializedName("runtime")
    private String watchTime;
    public DetailedMediaItem(String posterPath, String overview, String releaseDate, List<Integer> genreIds, Integer id, String originalTitle, String originalLanguage, String title, String backdropPath, Double popularity, Integer voteCount, Boolean video, Double voteAverage) {
        super(posterPath, overview, releaseDate, genreIds, id, originalTitle, originalLanguage, title, backdropPath, popularity, voteCount, video, voteAverage);
    }

    public String getWatchTime() {
        return watchTime;
    }
}
