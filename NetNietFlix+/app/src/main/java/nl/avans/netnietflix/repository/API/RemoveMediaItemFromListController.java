package nl.avans.netnietflix.repository.API;

import android.util.Log;

import nl.avans.netnietflix.domain.PostResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoveMediaItemFromListController extends GenericController<PostResponse> implements Callback<PostResponse> {

    private RemoveMediaItemFromListListener listener;
    public RemoveMediaItemFromListController(RemoveMediaItemFromListListener listener){
        this.listener = listener;
    }
    public void addMediaItemToList(int listId,int mediaId) {
        Call<PostResponse> call = api.removeMediaItemFromList(listId,API_KEY,SESSION_ID);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
        Log.d(TAG, "onResponse() - statuscode: " + response.code());
        if(response.isSuccessful()) {
            Log.d(TAG, "response: " + response.body());
            // Deserialization
            listener.onPost(true);

        } else {
            Log.e(TAG, "Not successful! Message: " + response.message());
        }
    }
    public interface RemoveMediaItemFromListListener {
        public void onPost(Boolean isSuccessful);
    }
}
