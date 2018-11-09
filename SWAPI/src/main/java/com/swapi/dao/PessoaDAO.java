package com.swapi.dao;

import android.app.Person;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

@Dao
public interface PessoaDAO {

    @Insert
    void inserePerson(Person person);

}
