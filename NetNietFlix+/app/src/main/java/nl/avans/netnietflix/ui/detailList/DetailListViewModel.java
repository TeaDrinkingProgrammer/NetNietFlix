package nl.avans.netnietflix.ui.detailList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import nl.avans.netnietflix.domain.MediaItemList;
import nl.avans.netnietflix.repository.API.MediaItemListController;

public class DetailListViewModel extends ViewModel implements MediaItemListController.MediaItemListListener {

    private MutableLiveData<String> mText;

    public DetailListViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    @Override
    public void onMediaItemListsAvailable(List<MediaItemList> mediaItemLists) {

    }
}