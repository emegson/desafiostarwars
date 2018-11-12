package com.swapi.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import android.arch.persistence.room.TypeConverters;
import com.swapi.dao.PeopleDAO;
import com.swapi.models.People;

@Database(entities = {People.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class SWAPIDatabase extends RoomDatabase {

    public abstract PeopleDAO getPeopleDAO();

}
