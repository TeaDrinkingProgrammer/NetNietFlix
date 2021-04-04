package nl.avans.netnietflix.repository.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import nl.avans.netnietflix.domain.DetailedMediaItem;
import nl.avans.netnietflix.domain.MediaItem;

@Dao
public interface DetailedMediaItemDAO {
    @Insert
    void insertAll(DetailedMediaItem... detailedMediaItems);
    @Query("SELECT * FROM DetailedMediaItem WHERE id = :id")
    LiveData<List<MediaItem>> getAllForId(int id);

}
