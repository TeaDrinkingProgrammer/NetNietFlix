package nl.avans.netnietflix.DAO;

import nl.avans.netnietflix.domain.MediaItemResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;



public interface API {
    @GET("trending/all/week")
    Call<MediaItemResponse> getTrendingMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MediaItemResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
}
