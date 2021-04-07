package nl.avans.netnietflix.applogic;

import nl.avans.netnietflix.repository.API.AddMediaItemToListController;
import nl.avans.netnietflix.repository.API.DetailMediaItemListController;
import nl.avans.netnietflix.repository.API.DetailedMediaItemController;
import nl.avans.netnietflix.repository.API.MediaItemControllerListener;
import nl.avans.netnietflix.repository.API.AllMediaItemListsController;
import nl.avans.netnietflix.repository.API.RatingController;
import nl.avans.netnietflix.repository.API.ReviewController;
import nl.avans.netnietflix.repository.DataController;

public class DataManager {
    private static final String TAG = DataManager.class.getSimpleName();
    private DataController dataController;

    public DataManager(String language){
        dataController = new DataController(language);
    }

    public void loadTrendingMediaItems(MediaItemControllerListener listener){
        dataController.loadTrendingMediaItems(listener);
    }
    public void loadTopRatedMediaItems(MediaItemControllerListener listener){
        dataController.loadTopRatedMediaItems(listener);
    }
    public void getMediaItemDetails(DetailedMediaItemController.DetailedMediaItemListener listener, int mediaItemid){
        dataController.getMediaItemDetails(listener, mediaItemid);
    }
    public void getReviewForMovieId(ReviewController.ReviewListener listener, int mediaItemid){
        dataController.getReviewForMovieId(listener, mediaItemid);
    }
    public void getMediaItemLists(AllMediaItemListsController.MediaItemListListener mediaItemListListener){
        dataController.getMediaItemLists(mediaItemListListener);
    }
    public void getSearchResults(MediaItemControllerListener listener, String query) {
        dataController.getSearchResults(listener, query);
    }
    public void getDetailItemList(DetailMediaItemListController.DetailMediaItemListListener mediaItemListListener, int listId){
        dataController.getDetailMediaItemList(mediaItemListListener,listId);
    }
    public void addMediaItemToList(AddMediaItemToListController.AddMediaItemToListListener listener, int listId, int mediaId){
        dataController.addMediaItemToList(listener,listId,mediaId);
    }
    public void addRatingToMovie(RatingController.RatingListener listener, int movieId, int rating){
        dataController.addRatingToMovie(listener, movieId, rating);
    }
}
