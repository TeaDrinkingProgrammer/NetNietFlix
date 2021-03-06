package nl.avans.netnietflix.repository.API;

import android.util.Log;

import java.util.List;

import nl.avans.netnietflix.domain.MediaItem;
import nl.avans.netnietflix.domain.APIResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopRatedController extends GenericController<APIResponse<MediaItem>> implements Callback<APIResponse<MediaItem>> {

    private MediaItemControllerListener listener;
    public TopRatedController(MediaItemControllerListener listener){
        this.listener = listener;
    }
    public void loadTopRatedMoviesPerWeek(String language) {
        Call<APIResponse<MediaItem>> call = api.getTopRatedMediaItems(API_KEY,language);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<APIResponse<MediaItem>> call, Response<APIResponse<MediaItem>> response) {
        Log.d(TAG, "onResponse() - statuscode: " + response.code());
        if(response.isSuccessful()) {
            Log.d(TAG, "response: " + response.body());
            // Deserialization
            List<MediaItem> mediaItems = response.body().getResults();
            listener.onMediaItemsAvailable(mediaItems,1);
        } else {
            Log.e(TAG, "Not successful! Message: " + response.message());
        }
    }
}