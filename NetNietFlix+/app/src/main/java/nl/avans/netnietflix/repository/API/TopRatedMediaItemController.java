package nl.avans.netnietflix.repository.API;

import android.util.Log;

import java.util.List;

import nl.avans.netnietflix.domain.MediaItem;
import nl.avans.netnietflix.domain.MediaItemResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopRatedMediaItemController extends GenericMediaItemController<MediaItemResponse> implements Callback<MediaItemResponse> {

    private MediaItemControllerListener listener;
    public TopRatedMediaItemController(MediaItemControllerListener listener){
        this.listener = listener;
    }
    public void loadTopRatedMoviesPerWeek() {
        Call<MediaItemResponse> call = api.getTopRatedMediaItems(API_KEY);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<MediaItemResponse> call, Response<MediaItemResponse> response) {
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