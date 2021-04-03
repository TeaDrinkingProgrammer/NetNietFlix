package nl.avans.netnietflix.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Review {
    @SerializedName("author")
    private String author;
    @SerializedName("rating")
    private double rating;
    @SerializedName("author_details")
    private AuthorDetails authorDetails;
    @SerializedName("content")
    private String content;

    public Review(String author, double rating, String content) {
        this.author = author;
        this.rating = rating;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getRating() {
        return authorDetails.getRating();
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public class AuthorDetails{
        @SerializedName("avatar_path")
        private String avatarPath;
        @SerializedName("rating")
        private double rating;

        public AuthorDetails(String avatarPath, double rating) {
            this.avatarPath = avatarPath;
            this.rating = rating;
        }

        public String getAvatarPath() {
            return avatarPath;
        }

        public void setAvatarPath(String avatarPath) {
            this.avatarPath = avatarPath;
        }

        public double getRating() {
            return rating;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }
    }
}
