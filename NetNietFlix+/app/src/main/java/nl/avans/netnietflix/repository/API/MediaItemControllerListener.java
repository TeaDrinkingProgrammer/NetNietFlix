package nl.avans.netnietflix.repository.API;

import java.util.List;

import nl.avans.netnietflix.domain.MediaItem;

public interface MediaItemControllerListener {
    public void onMediaItemsAvailable(List<MediaItem> mediaItems, int id);
}
