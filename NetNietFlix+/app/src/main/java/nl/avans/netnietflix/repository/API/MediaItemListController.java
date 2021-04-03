package nl.avans.netnietflix.repository.API;

import android.util.Log;

import java.util.List;

import nl.avans.netnietflix.domain.APIResponse;
import nl.avans.netnietflix.domain.MediaItemList;
import nl.avans.netnietflix.domain.Review;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MediaItemListController extends GenericController<APIResponse<MediaItemList>> implements Callback<APIResponse<MediaItemList>> {

    private MediaItemListListener listener;
    public MediaItemListController(MediaItemListListener listener){
        this.listener = listener;
    }

    public void getMediaItemLists() {
        Call<APIResponse<MediaItemList>> call = api.getMediaItemLists(ACCOUNT_ID,API_KEY,SESSION_ID);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<APIResponse<MediaItemList>> call, Response<APIResponse<MediaItemList>> response) {
        Log.d(TAG, "onResponse() - statuscode: " + response.code());
        if (response.isSuccessful()) {
            Log.d(TAG, "response: " + response.body());
            // Deserialization
            List<MediaItemList> mediaItemList = response.body().getResults();
            listener.onMediaItemListsAvailable(mediaItemList);
        }
    }

    public interface MediaItemListListener {
        public void onMediaItemListsAvailable(List<MediaItemList> mediaItemLists);
    }
}
