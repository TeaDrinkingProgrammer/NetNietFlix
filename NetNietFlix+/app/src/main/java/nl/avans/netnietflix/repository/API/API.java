package nl.avans.netnietflix.repository.API;

import nl.avans.netnietflix.domain.DetailedMediaItem;
import nl.avans.netnietflix.domain.MediaItemResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;



public interface API {
    @GET("trending/movie/week")
    Call<MediaItemResponse> getTrendingMediaItems(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Call<MediaItemResponse> getTopRatedMediaItems(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<DetailedMediaItem> getMediaItemDetails(@Path("id") int id, @Query("api_key") String apiKey);
}
