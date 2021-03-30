package nl.avans.netnietflix.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MediaItemResponse {
    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<MediaItem> results;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("total_pages")
    private int totalPages;

    public MediaItemResponse(int page, List<MediaItem> results, int totalResults, int totalPages) {
        this.page = page;
        this.results = results;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<MediaItem> getResults() {
        return results;
    }

    public void setResults(List<MediaItem> results) {
        this.results = results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
