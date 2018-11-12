package com.swapi.database

import android.arch.persistence.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromList(lista : List<String>): String {
        var sb = StringBuilder()
        for (s in lista) {
            if(s != " " && s.isNotEmpty()) {
                sb.append(s.trim())
                sb.append(",")
            }
        }
        return sb.toString()
    }

    @TypeConverter
    fun toList(texto : String): List<String> {
        val list = texto.split(",")
        return list
    }

}