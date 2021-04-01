package nl.avans.netnietflix.repository.API;

import nl.avans.netnietflix.domain.MediaItemResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;



public interface API {
    @GET("trending/movie/week")
    Call<MediaItemResponse> getTrendingMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Call<MediaItemResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MediaItemResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
}
