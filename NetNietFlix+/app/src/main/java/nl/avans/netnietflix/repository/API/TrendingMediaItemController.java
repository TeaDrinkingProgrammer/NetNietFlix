package nl.avans.netnietflix.repository.API;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import nl.avans.netnietflix.repository.API.API;
import nl.avans.netnietflix.domain.MediaItem;
import nl.avans.netnietflix.domain.MediaItemResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TrendingMediaItemController extends GenericMediaItemController<MediaItemResponse> implements Callback<MediaItemResponse> {

    private MediaItemControllerListener listener;
    public void loadTrendingMoviesPerWeek() {
        this.listener = listener;
        Call<MediaItemResponse> call = api.getTrendingMediaItems(API_KEY);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<MediaItemResponse> call, Response<MediaItemResponse> response) {
        Log.d(TAG, "onResponse() - statuscode: " + response.code());
        if(response.isSuccessful()) {
            Log.d(TAG, "response: " + response.body());
            // Deserialization
            List<MediaItem> mediaItems = response.body().getResults();
            listener.onMediaItemsAvailable(mediaItems,0);
        } else {
            Log.e(TAG, "Not successful! Message: " + response.message());
        }
    }
}
