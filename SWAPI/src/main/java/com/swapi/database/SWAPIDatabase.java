package com.swapi.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.swapi.dao.PeopleDAO;
import com.swapi.models.People;

@Database(entities = {People.class}, version = 1)
public abstract class SWAPIDatabase extends RoomDatabase {

    public abstract PeopleDAO getPeopleDAO();

}
