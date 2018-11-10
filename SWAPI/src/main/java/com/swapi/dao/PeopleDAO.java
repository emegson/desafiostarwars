package com.swapi.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.swapi.models.People;

import java.util.List;

@Dao
public interface PeopleDAO {

    @Query("select * from People")
    List<People> buscaPeople();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void inserePeople(People people);

    @Query("select * from people where id = :id")
    boolean findById(int id);
}
