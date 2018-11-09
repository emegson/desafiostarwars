package com.starwars.emerson.desafiostarwars;

import android.app.Person;
import android.content.Intent;
import android.provider.CalendarContract;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.swapi.models.People;
import com.swapi.models.Planet;
import com.swapi.sw.StarWars;
import com.swapi.sw.StarWarsApi;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DetailsActivity extends AppCompatActivity {
    private Intent intent;
    private People personagem;
    private TextInputEditText textname;
    private TextView textbirthYear;
    private TextView textgender;
    private TextView texthairColor;
    private TextView textheight;
    private TextView texthomeWorld;
    private TextView textmass;
    private TextView textskingColor;
    private TextView textfilme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        intent = getIntent();
        personagem = (People) intent.getSerializableExtra("personagem");
        montaTextview(personagem);
        Toast.makeText(DetailsActivity.this, personagem.name, Toast.LENGTH_SHORT).show();
        Toast.makeText(DetailsActivity.this, "do planeta "+ personagem.homeWorldId, Toast.LENGTH_SHORT).show();
    }

    private void montaTextview(People personagem) {

        textname = findViewById(R.id.personName);
        textbirthYear = findViewById(R.id.personBirthYear);
        textgender = findViewById(R.id.personGender);
        texthairColor = findViewById(R.id.personHairColor);
        textheight = findViewById(R.id.personHeight);
//        texthomeWorld = findViewById(R.id.personHomeWorld);
        textmass = findViewById(R.id.personMass);
        textskingColor = findViewById(R.id.personSkingColor);
//        textfilme = findViewById(R.id.personFilmsUrls);
        textname.setText(personagem.name);
        textbirthYear.setText(personagem.birthYear);
        textgender.setText(personagem.gender);
        texthairColor.setText(personagem.hairColor);
        textheight.setText(personagem.height);
//        texthomeWorld.setText(planeta[0]);
        textmass.setText(personagem.mass);
        textskingColor.setText(personagem.skinColor);
//        textfilme.setText(personagem.filmsUrls.toString());


    }
}
