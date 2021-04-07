package nl.avans.netnietflix.repository.API;

import android.util.Log;

import nl.avans.netnietflix.domain.PostResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoveListController extends GenericController<PostResponse> implements Callback<PostResponse> {



    public void removeList(int listId) {
        Call<PostResponse> call = api.removeList(listId,API_KEY,SESSION_ID);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
        Log.d(TAG, "onResponse() - statuscode: " + response.code());
        if(response.isSuccessful()) {
            Log.d(TAG, "response: " + response.body());
            // Deserialization
        } else {
            Log.e(TAG, "Not successful! Message: " + response.message());
        }
    }
}
