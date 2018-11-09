package com.starwars.emerson.desafiostarwars;

import android.content.Context;
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
import com.swapi.models.People;
import com.swapi.sw.StarWarsApi;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton botaoAdicionar;
    private ListView personagens;
    private ArrayList<People> persona;
    private ArrayAdapter<People> personaAdapter;
    private int[] controle = new int[88];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StarWarsApi.init();

        for(int i = 0; i<88; controle[i++] = -1);

        configList();
        configBotaoAdicionar();

    }

    public void scanBarcode(View view) {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setOrientationLocked(false);
        integrator.initiateScan();// `this` is the current Activity

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(resultCode, data);

        if (result.getContents() == null) {
            Log.d("MainActivity", "Cancelled scan");
            Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show();
        } else {
            Log.d("MainActivity", "Scanned");
            Toast.makeText(this, "Escaneado: " + result.getContents(), Toast.LENGTH_SHORT).show();
            try {
                final int a = Integer.parseInt(result.getContents());
                if (a > 88 | a < 1) {
                    Toast.makeText(this, "não existe esse registro", Toast.LENGTH_LONG).show();
                    return;
                }
                StarWarsApi.getApi().getPeople(a, new Callback<People>() {
                    @Override
                    public void success(People people, Response response) {
                        people.setId(a);

                        insereNaLista(people);

                        Log.i("objectprint",persona.toString());
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

    private void insereNaLista(People people) {
        if (persona.contains(people)) {
            Toast.makeText(MainActivity.this,"oops, você já registrou esse",Toast.LENGTH_SHORT).show();
        } else {
            controle[people.getId()] = 1;
            persona.add(people);
            personaAdapter.notifyDataSetChanged();
            Toast.makeText(MainActivity.this,"inseri",Toast.LENGTH_SHORT).show();
            Toast.makeText(MainActivity.this,"você já possui " + persona.size(),Toast.LENGTH_SHORT).show();
        }
    }

    private void configList() {
        persona = new ArrayList<>();
        personagens = findViewById(R.id.listaPersonagens);
        personaAdapter = new ArrayAdapter<People>(this, android.R.layout.simple_list_item_1, persona);
        personagens.setAdapter(personaAdapter);

        personagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                People personagem = persona.get(position);
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

