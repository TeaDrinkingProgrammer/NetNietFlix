package nl.avans.netnietflix.repository.API;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import nl.avans.netnietflix.domain.DetailedMediaItem;
import nl.avans.netnietflix.domain.MediaItem;
import nl.avans.netnietflix.domain.MediaItemResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailMediaItemController extends GenericMediaItemController<DetailedMediaItem> implements Callback<DetailedMediaItem> {

    private DetailedMediaItemListener listener;

    public void getMediaItemDetails(int MediaItemid) {
        Call<DetailedMediaItem> call = api.getMediaItemDetails(MediaItemid,API_KEY);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<DetailedMediaItem> call, Response<DetailedMediaItem> response) {
        Log.d(TAG, "onResponse() - statuscode: " + response.code());
        if (response.isSuccessful()) {
            Log.d(TAG, "response: " + response.body());
            // Deserialization
            DetailedMediaItem mediaItem = response.body();
            listener.onMediaItemsAvailable(mediaItem);
        }
    }

    public interface DetailedMediaItemListener {
        public void onMediaItemsAvailable(DetailedMediaItem mediaItem);
    }
}
