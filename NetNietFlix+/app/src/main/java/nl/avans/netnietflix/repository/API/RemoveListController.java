package nl.avans.netnietflix.repository.API;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import nl.avans.netnietflix.domain.PostResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoveListController extends GenericController<PostResponse> implements Callback<PostResponse> {


    private RemoveListListener listener;
    public void removeList(RemoveListListener listener,int listId) {
        Call<PostResponse> call = api.removeList(listId,API_KEY,SESSION_ID);
        call.enqueue(this);
        this.listener = listener;
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
            listener.onPost(true);
        }
    }

    @Override
    public void onFailure(@NotNull Call<PostResponse> call, Throwable t) {
        super.onFailure(call, t);
    }

    public interface RemoveListListener {
        public void onPost(Boolean isSuccessful);
    }
}
