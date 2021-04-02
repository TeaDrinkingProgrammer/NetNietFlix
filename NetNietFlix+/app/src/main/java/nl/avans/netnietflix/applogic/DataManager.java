package nl.avans.netnietflix.applogic;

import nl.avans.netnietflix.repository.API.DetailMediaItemController;
import nl.avans.netnietflix.repository.API.MediaItemControllerListener;
import nl.avans.netnietflix.repository.DataController;

public class DataManager {
    private static final String TAG = DataManager.class.getSimpleName();
    private DataController dataController;

    public DataManager(){
        dataController = new DataController();
    }

    public void loadTrendingMediaItems(MediaItemControllerListener listener){
        dataController.loadTrendingMediaItems(listener);
    }
    public void loadTopRatedMediaItems(MediaItemControllerListener listener){
        dataController.loadTopRatedMediaItems(listener);
    }
    public void getMediaItemDetails(DetailMediaItemController.DetailedMediaItemListener listener, int mediaItemid){
        dataController.getMediaItemDetails(listener, mediaItemid);
    }
}
