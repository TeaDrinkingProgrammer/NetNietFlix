package nl.avans.netnietflix.repository.API;

import android.util.Log;

import java.util.List;

import nl.avans.netnietflix.domain.APIResponse;
import nl.avans.netnietflix.domain.DetailMediaItemList;
import nl.avans.netnietflix.domain.MediaItemList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMediaItemListController extends GenericController<DetailMediaItemList> implements Callback<DetailMediaItemList> {

    private DetailMediaItemListListener listener;
    public DetailMediaItemListController(DetailMediaItemListListener listener){
        this.listener = listener;
    }

    public void getDetailMediaItemList(int itemId,String language) {
        Call<DetailMediaItemList> call = api.getDetailMediaItemList(itemId,API_KEY,language);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<DetailMediaItemList> call, Response<DetailMediaItemList> response) {
        Log.d(TAG, "onResponse() - statuscode: " + response.code());
        if (response.isSuccessful()) {
            Log.d(TAG, "response: " + response.body());
            // Deserialization
            DetailMediaItemList detailMediaItemList = response.body();
            listener.onDetailMediaItemListAvailable(detailMediaItemList);
        }
    }

    public interface DetailMediaItemListListener {
        public void onDetailMediaItemListAvailable(DetailMediaItemList mediaItemList);
    }
}
