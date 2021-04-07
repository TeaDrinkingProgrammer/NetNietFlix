package nl.avans.netnietflix.repository.API;

import nl.avans.netnietflix.domain.DetailMediaItemList;
import nl.avans.netnietflix.domain.DetailedMediaItem;
import nl.avans.netnietflix.domain.APIResponse;
import nl.avans.netnietflix.domain.MediaItem;
import nl.avans.netnietflix.domain.MediaItemList;
import nl.avans.netnietflix.domain.PostResponse;
import nl.avans.netnietflix.domain.Review;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;



public interface API {
    @GET("trending/movie/week")
    Call<APIResponse<MediaItem>> getTrendingMediaItems(@Query("api_key") String apiKey,@Query("language") String language);

    @GET("movie/top_rated")
    Call<APIResponse<MediaItem>> getTopRatedMediaItems(@Query("api_key") String apiKey,@Query("language") String language);

    @GET("movie/{id}")
    Call<DetailedMediaItem> getMediaItemDetails(@Path("id") int id, @Query("api_key") String apiKey,@Query("language") String language);

    @GET("movie/{id}/reviews")
    Call<APIResponse<Review>> getReviewsForMovieId(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("account/{accountId}/lists")
    Call<APIResponse<MediaItemList>> getMediaItemLists(@Path("accountId") int id, @Query("api_key") String apiKey,@Query("session_id") String sessionId);

    @GET("search/movie")
    Call<APIResponse<MediaItem>> getMediaItemSearch(@Query("api_key") String apiKey, @Query("query") String query,@Query("language") String language);

    @GET("list/{listId}")
    Call<DetailMediaItemList> getDetailMediaItemList(@Path("listId") int id, @Query("api_key") String apiKey,@Query("language") String language);

    @POST("list/{listId}/add_item")
    Call<PostResponse> addMediaItemToList(@Path("listId") int id, @Body int mediaId, @Query("api_key") String apiKey, @Query("session_id") String sessionId);

    @POST("movie/{movie_id}/rating")
    Call<PostResponse> addRatingToMovie(@Path("movie_id") int id, @Body int rating, @Query("api_key") String apiKey, @Query("session_id") String sessionId);

    @DELETE("list/{listId}")
    Call<PostResponse> removeList(@Path("listId") int listId, @Query("api_key") String apiKey,@Query("session_id") String sessionId);

    @DELETE("list/{listId}/remove_item")
    Call<PostResponse> removeMediaItemFromList(@Path("listId") int listId, @Body int media_id, @Query("api_key") String apiKey, @Query("session_id") String sessionId);
}
