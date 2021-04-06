package nl.avans.netnietflix.repository.API;

import android.util.Log;

import java.util.List;

import nl.avans.netnietflix.domain.APIResponse;
import nl.avans.netnietflix.domain.MediaItemList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMediaItemListController extends GenericController<APIResponse<MediaItemList>> implements Callback<APIResponse<MediaItemList>> {

    private DetailMediaItemListListener listener;
    public DetailMediaItemListController(DetailMediaItemListListener listener){
        this.listener = listener;
    }

    public void getDetailMediaItemList(int itemId) {
        Call<APIResponse<MediaItemList>> call = api.getDetailMediaItemList(itemId,API_KEY);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<APIResponse<MediaItemList>> call, Response<APIResponse<MediaItemList>> response) {
        Log.d(TAG, "onResponse() - statuscode: " + response.code());
        if (response.isSuccessful()) {
            Log.d(TAG, "response: " + response.body());
            // Deserialization
            List<MediaItemList> mediaItemList = response.body().getResults();
            listener.onDetailMediaItemListAvailable(mediaItemList);
        }
    }

    public interface DetailMediaItemListListener {
        public void onDetailMediaItemListAvailable(List<MediaItemList> mediaItemLists);
    }
}
