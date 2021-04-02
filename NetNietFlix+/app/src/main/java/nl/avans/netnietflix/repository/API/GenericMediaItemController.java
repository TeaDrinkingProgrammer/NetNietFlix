package nl.avans.netnietflix.repository.API;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import nl.avans.netnietflix.domain.DetailedMediaItem;
import nl.avans.netnietflix.domain.MediaItemResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class GenericMediaItemController<T> implements Callback<T> {
    protected final String TAG = this.getClass().getSimpleName();
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String BASE_POSTER_PATH_URL = "https://image.tmdb.org/t/p/w500/";
    protected static final String API_KEY = "f3e724534425b939df9f8942232ebe68";

    protected Context context;

    protected Retrofit retrofit;
    protected Gson gson;
    protected API api;

    public GenericMediaItemController() {
        this.context = context;

        gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        api = retrofit.create(API.class);
    }

    @Override
    abstract public void onResponse(Call<T> call, Response<T> response);

    @Override
    public void onFailure (@NotNull Call<T> call, Throwable t){
        Log.e(TAG, "onFailure - " + t.getMessage());
    }
}
