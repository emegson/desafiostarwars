package com.starwars.emerson.desafiostarwars.com.starwars.emerson.desafiostarwars.filme

import com.google.gson.annotations.SerializedName

class FilmEntity {

    @SerializedName("id")
    var id : Int? = null

    @SerializedName("poster_path")
    var posterpath : String? = null

}