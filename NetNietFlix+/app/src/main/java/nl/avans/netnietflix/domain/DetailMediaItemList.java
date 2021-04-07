package nl.avans.netnietflix.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailMediaItemList extends MediaItemList{
    @SerializedName("items")
    List<MediaItem> mediaItemList;
    public DetailMediaItemList(String name, String posterPath, int id, String description, int itemCount, String listType,List<MediaItem> mediaItemList) {
        super(name, posterPath, id, description, itemCount, listType);
        this.mediaItemList = mediaItemList;
    }

    public List<MediaItem> getMediaItemList() {
        return mediaItemList;
    }


}
