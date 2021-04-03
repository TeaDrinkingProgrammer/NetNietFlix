package nl.avans.netnietflix.repository.API;

import android.util.Log;

import java.util.List;

import nl.avans.netnietflix.domain.APIResponse;
import nl.avans.netnietflix.domain.Review;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewController extends GenericController<APIResponse<Review>> implements Callback<APIResponse<Review>> {

    private ReviewListener listener;
    public ReviewController(ReviewListener listener){
        this.listener = listener;
    }

    public void getReviewDetails(int mediaItemId) {
        Call<APIResponse<Review>> call = api.getReviewsForMovieId(mediaItemId,API_KEY);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<APIResponse<Review>> call, Response<APIResponse<Review>> response) {
        Log.d(TAG, "onResponse() - statuscode: " + response.code());
        if (response.isSuccessful()) {
            Log.d(TAG, "response: " + response.body());
            // Deserialization
            List<Review> reviews = response.body().getResults();
            listener.onReviewsAvailable(reviews);
        }
    }

    public interface ReviewListener {
        public void onReviewsAvailable(List<Review> reviews);
    }
}
