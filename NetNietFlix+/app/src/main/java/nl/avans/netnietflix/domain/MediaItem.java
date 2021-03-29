package nl.avans.netnietflix.domain;

import androidx.annotation.NonNull;

import java.util.List;

public class MediaItem {
    private String title;
    private String duration;
    private String genre;
    private String description;
    private String releaseDate;
    private String mediaType;
    private String imgLink;

    public MediaItem(String title, String duration, String genre, String description, String releaseDate, String mediaType, String imgLink) {
        this.title = title;
        this.duration = duration;
        this.genre = genre;
        this.description = description;
        this.releaseDate = releaseDate;
        this.mediaType = mediaType;
        this.imgLink = imgLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }
}
