package com.starwars.emerson.desafiostarwars;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.swapi.models.People;
import com.swapi.sw.StarWars;
import com.swapi.sw.StarWarsApi;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView personagens;
    private ArrayList<String> persona;
    private ArrayAdapter<String> personaAdapter;
    private int contador = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StarWarsApi.init();


        persona = new ArrayList<>();

        personaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, persona);

        personagens = findViewById(R.id.listaPersonagens);
        personagens.setAdapter(personaAdapter);

        FloatingActionButton botaoAdicionar = (FloatingActionButton) findViewById(R.id.botaoAdicionar);
        botaoAdicionar.setOnClickListener ( new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Toast.makeText(MainActivity.this, "ADICIONAR QR CODE", Toast.LENGTH_LONG).show();

                try{
                    StarWarsApi.getApi().getPeople(contador++, new Callback<People>() {
                        @Override
                        public void success(People people, Response response) {
                            persona.add(people.name);
                            personaAdapter.notifyDataSetChanged();
                        }
                        @Override
                        public void failure(RetrofitError error) {
                            Log.e("CALLBACK-PERSON", error.getMessage());
                        }
                    });
                }
                catch (Exception e){
                    Log.e ("erro",  "deu ruim");
                }
            }
        });

        personagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "SELECIONEI", Toast.LENGTH_SHORT);
            }
        });

    }
}
