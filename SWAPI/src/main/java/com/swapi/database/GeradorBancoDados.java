package com.swapi.database;

import android.arch.persistence.room.Room;
import android.content.Context;

public class GeradorBancoDados {

    public SWAPIDatabase gera(Context contexto) {

        SWAPIDatabase swapidb = Room
                .databaseBuilder(contexto, SWAPIDatabase.class, "swapidb")
                .allowMainThreadQueries()
                .build();
        return swapidb;
    }

}
