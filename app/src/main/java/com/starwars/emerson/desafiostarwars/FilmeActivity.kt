package com.starwars.emerson.desafiostarwars

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import com.squareup.picasso.Picasso
import com.starwars.emerson.desafiostarwars.com.starwars.emerson.desafiostarwars.filme.SearchMovieResponse
import com.starwars.emerson.desafiostarwars.com.starwars.emerson.desafiostarwars.filme.TheMovieDbAPI
import com.swapi.models.People
import retrofit.Callback
import retrofit.RetrofitError
import retrofit.client.Response

class FilmeActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView

    private lateinit var listaAdapter : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filme)

        var intent = intent
        var people : People

        people = intent.getSerializableExtra("people") as People

        imageView = findViewById(R.id.imageView)

        configuraListaFilmes(people)

        this@FilmeActivity.title = people.name.toString()

    }

    private fun configuraListaFilmes(people: People) {
        var aux : ArrayList<String> = ArrayList()
        listaAdapter = findViewById(R.id.listaPersonagens)

        for(i in 0 until people.filmsTitle.size){
            if(people.filmsTitle[i] != " " && people.filmsTitle[i] != ""){
                aux.add(people.filmsTitle[i])
            }
        }

        val adapter = ArrayAdapter(this@FilmeActivity, android.R.layout.simple_list_item_1, aux)
        listaAdapter.adapter = adapter

        listaAdapter.setOnItemClickListener { parent, view, position, id ->
            val filme : String = aux[position]
            TheMovieDbAPI.http().search(
                query = filme,
                callback = object : Callback<SearchMovieResponse> {
                    override fun success(movie : SearchMovieResponse?, response: Response?) {
                        var results = movie?.getResults()?.get(0)
                        var path = results?.posterpath
                        Picasso.get().load("https://image.tmdb.org/t/p/w500"+path).into(imageView)
                    }

                    override fun failure(error: RetrofitError?) {
                        if (error != null) {
                            Log.e("Erro Imagem", error.message, error)
                        }
                    }


                }
            )

        }

    }
}
