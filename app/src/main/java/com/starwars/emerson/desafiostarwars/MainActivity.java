package com.starwars.emerson.desafiostarwars;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView personagens;
    private ArrayList<String> persona;
    private ArrayAdapter<String> personaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StarWarsApi.init();
        persona = new ArrayList<>();

        persona.add("PEDRO1");
        persona.add("PEDRO5");
        persona.add("PEDRO4");
        persona.add("PEDRO3");
        persona.add("PEDRO2");

        personaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, persona);

        personagens = findViewById(R.id.listaPersonagens);
        personagens.setAdapter(personaAdapter);

        FloatingActionButton botaoAdicionar = (FloatingActionButton) findViewById(R.id.botaoAdicionar);
        botaoAdicionar.setOnClickListener ( new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Toast.makeText(MainActivity.this, "ADICIONAR QR CODE", Toast.LENGTH_LONG).show();

                try{
                    String torneio = ("Torneio ");
                    persona.add(torneio);
                    personaAdapter.notifyDataSetChanged();
                }
                catch (Exception e){
                    Log.e ("erro",  "deu ruim");
                }
            }
        });
    }
}
