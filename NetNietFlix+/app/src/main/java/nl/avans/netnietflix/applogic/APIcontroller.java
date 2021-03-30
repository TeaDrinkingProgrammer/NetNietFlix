package nl.avans.netnietflix.applogic;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import nl.avans.netnietflix.DAO.API;
import nl.avans.netnietflix.domain.MediaItem;
import nl.avans.netnietflix.domain.MediaItemResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIcontroller implements Callback<MediaItemResponse> {

    private final String LOG_TAG = this.getClass().getSimpleName();
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String BASE_POSTER_PATH_URL = "https://image.tmdb.org/t/p/w500/";
    private static final String API_KEY = "f3e724534425b939df9f8942232ebe68";

    private MediaItemControllerListener listener;
    private Context context;

    private final Retrofit retrofit;
    private final Gson gson;
    private final API api;

    public APIcontroller(MediaItemControllerListener listener, Context context) {
        this.listener = listener;
        this.context = context;

        gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        api = retrofit.create(API.class);
    }

    public void loadTrendingMoviesPerWeek() {
        Call<MediaItemResponse> call = api.getTrendingMovies(API_KEY);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<MediaItemResponse> call, Response<MediaItemResponse> response) {
        Log.d(LOG_TAG, "onResponse() - statuscode: " + response.code());

        if(response.isSuccessful()) {
            Log.d(LOG_TAG, "response: " + response.body());

            // Deserialization
            List<MediaItem> mediaItems = response.body().getResults();
            listener.onMediaItemsAvailable(mediaItems,context);
        } else {
            Log.e(LOG_TAG, "Not successful! Message: " + response.message());
        }
    }

    @Override
    public void onFailure(@NotNull Call<MediaItemResponse> call, Throwable t) {
        Log.e(LOG_TAG, "onFailure - " + t.getMessage());
    }

    public interface MediaItemControllerListener {
        public void onMediaItemsAvailable(List<MediaItem> mediaItems, Context context);
    }
}
