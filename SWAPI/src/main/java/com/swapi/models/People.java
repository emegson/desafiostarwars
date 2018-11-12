package com.swapi.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import com.google.gson.annotations.SerializedName;
import com.swapi.database.Converters;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    public List<String> filmsUrls;


    public List<String> filmsTitle;

    @Ignore
    @SerializedName("species")
    public ArrayList<String> speciesUrls;

    @Ignore
    @SerializedName("starships")
    public ArrayList<String> starshipsUrls;

    @Ignore
    @SerializedName("vehicles")
    public ArrayList<String> vehiclesUrls;

    @Override
    public String toString() {
        return this.name.toLowerCase();
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

    public static String retornaId(String url) {
        int fim, inicio = url.length();
        fim = url.length() - 1;

        if (url.length() == 32)
            inicio = inicio - 3;
        else
            inicio = inicio - 2;

        if (inicio != url.length())
            return url.substring(inicio, fim);
        else
            return "-1";
    }
}
