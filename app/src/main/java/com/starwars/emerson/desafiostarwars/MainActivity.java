package com.starwars.emerson.desafiostarwars;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.swapi.dao.PeopleDAO;
import com.swapi.database.Converters;
import com.swapi.database.GeradorBancoDados;
import com.swapi.models.Film;
import com.swapi.models.People;
import com.swapi.sw.StarWarsApi;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton botaoAdicionar;
    private ListView personagens;
    private List<People> peopleList;
    private ArrayAdapter<People> personaAdapter;
    private int quantidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StarWarsApi.init();
        configList();
        configBotaoAdicionar();

        quantidade = 0;
    }

    public void scanBarcode(View view) {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setOrientationLocked(false);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(resultCode, data);

        if (result.getContents() == null) {
            Log.d("MainActivity", "Cancelled scan");
            Toast.makeText(this, "cancelado", Toast.LENGTH_LONG).show();
        } else {
            Log.d("MainActivity", "Scanned");
            Toast.makeText(this, "escaneado: " + result.getContents(), Toast.LENGTH_SHORT).show();
            try {
                final int peopleId = Integer.parseInt(result.getContents());
                if (peopleId > 88 | peopleId < 1) {
                    Toast.makeText(this, "esse registro não existe...", Toast.LENGTH_LONG).show();
                    return;
                }
                StarWarsApi.getApi().getPeople(peopleId, new Callback<People>() {
                    @Override
                    public void success(People people, Response response) {
                        people.setId(peopleId);
                        people = formataPeopleFilmTitle(people);
                        insereNaLista(people);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("CALLBACK-PERSON", error.getMessage());
                    }
                });
            } catch (Exception e) {
                Log.e("erro", "deu ruim");
            }
        }

    }

    private People formataPeopleFilmTitle(People people) {
        List<String> filmes = people.filmsUrls;
        people.filmsTitle = trataUrl(filmes);
        return people;
    }

    private List<String> trataUrl(List<String> filmes) {
        final List<String> filmeTitle = new ArrayList<>();
        String aux;
        int i;
        for(i=0; i < filmes.size(); i++){
            aux = People.retornaId(filmes.get(i));
            StarWarsApi.getApi().getFilm(Integer.parseInt(aux), new Callback<Film>() {
                @Override
                public void success(Film film, Response response) {
                    filmeTitle.add(film.title);
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e("film Erro", "falha ao retornar o filme" + error.getMessage(), error);
                }
            });
        }
        return filmeTitle;
    }

    private void insereNaLista(People people) {
        if (peopleList.contains(people)) {
            Toast.makeText(MainActivity.this, "oops, você já registrou esse", Toast.LENGTH_SHORT).show();
        } else {
            peopleList.add(people);
            personaAdapter.notifyDataSetChanged();

            Toast.makeText(MainActivity.this, "inseri", Toast.LENGTH_SHORT).show();
            Toast.makeText(MainActivity.this, "você já possui " + peopleList.size(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        GeradorBancoDados gerador = new GeradorBancoDados();
        PeopleDAO peopleDAO = gerador.gera(MainActivity.this).getPeopleDAO();
        super.onPause();
        List<String> listateste;
        Converters conversor = new Converters();
        Toast.makeText(MainActivity.this, "ONRESUME", Toast.LENGTH_SHORT);
        for (int i = 0; i < peopleList.size(); i++) {
            peopleDAO.inserePeople(peopleList.get(i));
            listateste = conversor.toList(conversor.fromList(peopleList.get(i).filmsUrls));
            Log.i("print", listateste.toString());
        }
    }

    private void configList() {
        GeradorBancoDados gerador = new GeradorBancoDados();
        PeopleDAO peopleDAO = gerador.gera(MainActivity.this).getPeopleDAO();

        peopleList = peopleDAO.buscaPeople();

        personagens = findViewById(R.id.listaPersonagens);
        personaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, peopleList);
        personagens.setAdapter(personaAdapter);

        personagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                People personagem = peopleList.get(position);

                intent.putExtra("personagem", personagem);
                startActivity(intent);
            }
        });
    }

    private void configBotaoAdicionar() {
        botaoAdicionar = findViewById(R.id.botaoAdicionar);

        botaoAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                scanBarcode(v);
            }
        });
    }

}

