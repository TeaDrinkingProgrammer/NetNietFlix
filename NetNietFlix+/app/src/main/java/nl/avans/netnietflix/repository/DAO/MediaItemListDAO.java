package nl.avans.netnietflix.repository.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import nl.avans.netnietflix.domain.MediaItemList;

@Dao
public interface MediaItemListDAO {
    @Insert
    void insertAll(MediaItemList... mediaItemLists);
    @Query("SELECT * FROM MediaItemList")
    LiveData<List<MediaItemList>> getAll();
}
