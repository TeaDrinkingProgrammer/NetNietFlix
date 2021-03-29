package nl.avans.netnietflix.domain;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BBCharacter implements Serializable {
    private String name;
    private String birthday;
    private List<String> occupations;
    private String imgLink;
    private String status;
    private String nickname;
    private List<Integer> appearsInSeasons;

    public BBCharacter(String name, String birthday, List<String> occupations, String imgLink, String status, String nickname, List<Integer> appearsInSeasons) {
        this.name = name;
        this.birthday = birthday;
        this.occupations = occupations;
        this.imgLink = imgLink;
        this.status = status;
        this.nickname = nickname;
        this.appearsInSeasons = appearsInSeasons;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public List<String> getOccupations() {
        return occupations;
    }

    public String getImgLink() {
        return imgLink;
    }

    public String getStatus() {
        return status;
    }

    public String getNickname() {
        return nickname;
    }

    public List<Integer> getAppearsInSeasons() {
        return appearsInSeasons;
    }

    public int getAmountOfSeasons(){
       return appearsInSeasons.size();
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("name: %s , birthday: %s, occupations: %s, status: %s, nickname: %s, appearsInSeasons: %s, amountofseasons: %d",name,birthday,occupations.toString(),status,nickname,appearsInSeasons.toString(),appearsInSeasons.size());
    }
}
