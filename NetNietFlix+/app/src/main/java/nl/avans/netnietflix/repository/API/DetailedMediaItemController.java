package nl.avans.netnietflix.repository.API;

import android.util.Log;

import nl.avans.netnietflix.domain.APIResponse;
import nl.avans.netnietflix.domain.DetailedMediaItem;
import nl.avans.netnietflix.domain.MediaItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailedMediaItemController extends GenericController<DetailedMediaItem> implements Callback<DetailedMediaItem> {

    private DetailedMediaItemListener listener;
    public DetailedMediaItemController(DetailedMediaItemListener listener){
        this.listener = listener;
    }
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
