package com.example.projektmobileapplikationen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Ergebnis extends AppCompatActivity {

    TextView textTitel;
    TextView textATag;
    TextView textAMonat;
    TextView textAJahr;
    TextView textAStunde;
    TextView textAMinute;
    TextView textETag;
    TextView textEMonat;
    TextView textEJahr;
    TextView textEStunde;
    TextView textEMinute;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ergebnis);

        textTitel = (TextView) findViewById((R.id.textViewBezeichnung));
        textATag = (TextView) findViewById(R.id.textviewATag);
        textAMonat = (TextView) findViewById(R.id.textviewAMonat);
        textAJahr = (TextView) findViewById(R.id.textviewAJahr);
        textAStunde = (TextView) findViewById(R.id.textviewAStunde);
        textAMinute = (TextView) findViewById(R.id.textviewAMinute);
        textETag = (TextView) findViewById(R.id.textviewETag);
        textEMonat = (TextView) findViewById(R.id.textviewEMonat);
        textEJahr = (TextView) findViewById(R.id.textviewEJahr);
        textEStunde = (TextView) findViewById(R.id.textviewEStunde);
        textEMinute = (TextView) findViewById(R.id.textviewEMinute);

        Intent intent = getIntent();
        int[] werte = intent.getIntArrayExtra("Werte");
        String titel = intent.getStringExtra("Titel");

        try {
            textATag.setText("" + werte[0]);
            textAMonat.setText("" + werte[1]);
            textAJahr.setText("" + werte[2]);
            textAStunde.setText("" + werte[3]);
            textAMinute.setText("" + werte[4]);
            textETag.setText("" + werte[5]);
            textEMonat.setText("" + werte[6]);
            textEJahr.setText("" + werte[7]);
            textEStunde.setText("" + werte[8]);
            textEMinute.setText("" + werte[9]);
            textTitel.setText(titel);

        } catch(Exception e){
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
