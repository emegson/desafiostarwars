package com.swapi.database

import android.arch.persistence.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromList(lista: List<String>): String {
        var sb = StringBuilder()
        for (s in lista) {
            sb.append(s)
            sb.append(", ")
        }
        return sb.toString()
    }

    @TypeConverter
    fun toList(lista: String): List<String> {
        val list = lista.split(", ")
        return list
    }

}