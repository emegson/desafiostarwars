package com.starwars.emerson.desafiostarwars.com.starwars.emerson.desafiostarwars.filme

class SearchMovieResponse {
    private var results: List<FilmEntity>? = null

    fun getResults(): List<FilmEntity>? {
        return results
    }

    fun setResults(results: List<FilmEntity>) {
        this.results = results
    }
}
