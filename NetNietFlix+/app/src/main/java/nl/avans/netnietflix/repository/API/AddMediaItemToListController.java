package nl.avans.netnietflix.repository.API;

import android.util.Log;

import java.util.List;

import nl.avans.netnietflix.domain.APIResponse;
import nl.avans.netnietflix.domain.MediaItem;
import nl.avans.netnietflix.domain.PostResponse;
import nl.avans.netnietflix.domain.Review;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class AddMediaItemToListController extends GenericController<PostResponse> implements Callback<PostResponse> {

    private AddMediaItemToListListener listener;
    public AddMediaItemToListController(AddMediaItemToListListener listener){
        this.listener = listener;
    }
    public void addMediaItemToList(int listId,int mediaId) {
        Call<PostResponse> call = api.addMediaItemToList(listId, mediaId,API_KEY,SESSION_ID);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
        Log.d(TAG, "onResponse() - statuscode: " + response.code());
        if(response.isSuccessful()) {
            Log.d(TAG, "response: " + response.body());
            // Deserialization
            listener.onPost(response.body().isSuccessful());

        } else {
            Log.e(TAG, "Not successful! Message: " + response.message());
        }
    }
    public interface AddMediaItemToListListener {
        public void onPost(Boolean isSuccessful);
    }
}
