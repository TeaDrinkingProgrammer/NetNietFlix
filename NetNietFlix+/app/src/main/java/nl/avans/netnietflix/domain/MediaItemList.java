package nl.avans.netnietflix.domain;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "MediaItemList")
public class MediaItemList {
    @SerializedName("name")
    private String name;
    @SerializedName("poster_path")
    private String posterPath;
    @PrimaryKey
    @NonNull
    @SerializedName("id")
    private int id;
    @SerializedName("description")
    private String description;
    @SerializedName("item_count")
    private int itemCount;
    @SerializedName("list_type")
    private String listType;

    public MediaItemList(String name, String posterPath, int id, String description, int itemCount, String listType) {
        this.name = name;
        this.posterPath = posterPath;
        this.id = id;
        this.description = description;
        this.itemCount = itemCount;
        this.listType = listType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }
}
