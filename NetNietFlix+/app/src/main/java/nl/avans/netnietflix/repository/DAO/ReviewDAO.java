package nl.avans.netnietflix.repository.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import nl.avans.netnietflix.domain.Review;

@Dao
public interface ReviewDAO {
    @Insert
    void insertAll(Review... reviews);
    @Query("SELECT * FROM Review WHERE mediaItemId = :id")
    List<Review> getForId(int id);
}
