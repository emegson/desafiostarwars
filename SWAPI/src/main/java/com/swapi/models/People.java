package com.swapi.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Oleur on 21/12/2014.
 * People model represents an individual person or character within the Star Wars universe.
 */
@Entity
public class People implements Serializable {

    @PrimaryKey
    private int id;

    public String name;

    @SerializedName("birth_year")
    public String birthYear;

    public String gender;

    @SerializedName("hair_color")
    public String hairColor;

    public String height;

    @SerializedName("homeworld")
    public String homeWorldUrl;

    public int homeWorldId;

    public String mass;

    @SerializedName("skin_color")
    public String skinColor;

    public String created;
    public String edited;
    public String url;

    @SerializedName("films")
    public ArrayList<String> filmsUrls;

    @SerializedName("species")
    public ArrayList<String> speciesUrls;

    @SerializedName("starships")
    public ArrayList<String> starshipsUrls;

    @SerializedName("vehicles")
    public ArrayList<String> vehiclesUrls;

    @Override
    public String toString() {
        return this.name.toLowerCase();
    }

    private void setHomeWorldName() {
        int fim, inicio = homeWorldUrl.length();
        fim = homeWorldUrl.length() - 1;

        if (homeWorldUrl.length() == 32)
            inicio = inicio - 3;
        else
            inicio = inicio - 2;

        if (inicio != homeWorldUrl.length())
            this.homeWorldId = Integer.parseInt(homeWorldUrl.substring(inicio, fim));
        else
            this.homeWorldId = -1;
    }

    public int getHomeWorldId() {
        setHomeWorldName();
        return this.homeWorldId;
    }

    public void setId(int a) {
        this.id = a;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof People)
            return this.getId() == ((People) obj).getId();
        return false;
    }
}
