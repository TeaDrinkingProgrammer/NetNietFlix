package nl.avans.netnietflix.repository.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import nl.avans.netnietflix.domain.MediaItem;

@Dao
public interface MediaItemDAO {
    @Insert
    void insertAll(MediaItem... mediaItem);
    @Query("SELECT * FROM MediaItem WHERE mediaItemType = 'trending'")
    LiveData<List<MediaItem>> getAllTrending();
    @Query("SELECT * FROM MediaItem WHERE mediaItemType = 'topRated'")
    LiveData<List<MediaItem>> getAllTopRated();

}
