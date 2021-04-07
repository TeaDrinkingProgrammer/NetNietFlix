package nl.avans.netnietflix.repository.API;

import android.util.Log;

import nl.avans.netnietflix.domain.PostResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoveListItemController extends GenericController<PostResponse> implements Callback<PostResponse> {

    private RemoveMediaItemFromListListener listener;
    public RemoveListItemController(RemoveMediaItemFromListListener listener){
        this.listener = listener;
    }
    public void removeMediaItemFromList(int listId,int media_id) {
        Call<PostResponse> call = api.removeMediaItemFromList(listId, media_id, API_KEY, SESSION_ID);
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
