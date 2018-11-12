package com.starwars.emerson.desafiostarwars.com.starwars.emerson.desafiostarwars.filme

import retrofit.Callback
import retrofit.RestAdapter
import retrofit.http.GET
import retrofit.http.Query


class TheMovieDbAPI {

    companion object {
        val apiKey = "3d6c1a8295b0a5904d09e3f135842fa9"
        val BASE_URL = "http://api.themoviedb.org/3"

        fun http(): ROUTES {
            return RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build()
                .create(ROUTES::class.java)
        }
    }

    interface ROUTES {

        //MOVIE SEARCH AUTOCOMPLETE
        @GET("/search/movie")
        fun search(@Query("api_key") apiKey: String = "3d6c1a8295b0a5904d09e3f135842fa9",
                   @Query("query") query: String,
                   callback: Callback<SearchMovieResponse>)

    }



}
