package nl.avans.netnietflix.repository.API;

import android.util.Log;

import nl.avans.netnietflix.domain.PostResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RatingController extends GenericController<PostResponse> implements Callback<PostResponse> {

    private RatingListener listener;
    public RatingController(RatingListener listener){
        this.listener = listener;
    }

    public void addRatingToMovie(int movieId, double rating) {
        Call<PostResponse> call = api.addRatingToMovie(movieId, rating, API_KEY, SESSION_ID);
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
    public interface RatingListener {
        public void onPost(Boolean isSuccessful);
    }
}
