package nl.avans.netnietflix.repository.API;

import android.util.Log;

import java.util.List;

import nl.avans.netnietflix.domain.MediaItem;
import nl.avans.netnietflix.domain.APIResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrendingController extends GenericController<APIResponse<MediaItem>> implements Callback<APIResponse<MediaItem>> {

    private MediaItemControllerListener listener;
    public TrendingController(MediaItemControllerListener listener){
        this.listener = listener;
    }
    public void loadTrendingMoviesPerWeek() {
        Call<APIResponse<MediaItem>> call = api.getTrendingMediaItems(API_KEY);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<APIResponse<MediaItem>> call, Response<APIResponse<MediaItem>> response) {
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
