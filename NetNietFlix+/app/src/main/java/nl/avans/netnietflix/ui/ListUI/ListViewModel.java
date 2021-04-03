package nl.avans.netnietflix.ui.ListUI;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import nl.avans.netnietflix.applogic.DataManager;
import nl.avans.netnietflix.domain.MediaItemList;
import nl.avans.netnietflix.repository.API.MediaItemListController;

public class ListViewModel extends ViewModel implements MediaItemListController.MediaItemListListener {

    private MutableLiveData<String> mText;
    private final String TAG = this.getClass().getSimpleName();
    private DataManager dataManager;

    public ListViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
        dataManager = new DataManager();
        dataManager.getMediaItemLists(this);
    }

    public LiveData<String> getText() {
        return mText;
    }

    @Override
    public void onMediaItemListsAvailable(List<MediaItemList> mediaItemLists) {
        Log.d(TAG,mediaItemLists.get(0).getDescription());
    }
}