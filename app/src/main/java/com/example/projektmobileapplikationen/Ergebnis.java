package com.example.projektmobileapplikationen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Ergebnis extends AppCompatActivity {

    Date start = new Date();
    Date ende = new Date();

    TextView textTitel;
    TextView textADatum;
    TextView textAUhrzeit;
    TextView textEDatum;
    TextView textEUhrzeit;
    TextView textErgebnis;
    Intent intent;

    private int[] werte;
    DBHandler db;
    private Button buttonZurücksetzen;

    public Ergebnis(){

    }

    public Ergebnis (Date s , Date e){
        start = s;
        ende = e;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ergebnis);

        buttonZurücksetzen = (Button) findViewById(R.id.buttonspeichern);

        textTitel = (TextView) findViewById((R.id.textviewTitel));
        textADatum = (TextView) findViewById(R.id.textviewADatum);
        textAUhrzeit = (TextView) findViewById(R.id.textviewAUhrzeit);
        textEDatum = (TextView) findViewById(R.id.textviewEDatum);
        textEUhrzeit = (TextView) findViewById(R.id.textviewEUhrzeit);
        textErgebnis = (TextView) findViewById((R.id.textviewBetrag));

        intent = getIntent();
        werte = intent.getIntArrayExtra("Werte");
        String titel = intent.getStringExtra("Titel");
        db = new DBHandler(this);

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mmm");
            String start = (""+ werte[0] + "." + werte[1]+ "." + werte[2] + " " + werte[3] + ":" + werte[4]);
            Date startdatum = simpleDateFormat.parse(start);
            //startdatum = simpleDateFormat.parse(start);
            String end = (""+ werte[5] + "." + werte[6]+ "." + werte[7] + " " + werte[8] + ":" + werte[9]);
            Date enddatum = new Date();
            enddatum = simpleDateFormat.parse(end);

            textADatum.setText(""+ werte[0] +"."+ werte[1] + "." +werte[2]);
            textAUhrzeit.setText(startdatum.toString().substring(startdatum.toString().length()-17,startdatum.toString().length()-12));
            textEDatum.setText(""+ werte[5] +"."+ werte[6] + "." +werte[7]);
            textEUhrzeit.setText(enddatum.toString().substring(11,enddatum.toString().length()-12));
            textTitel.setText(titel);

        } catch(Exception e){
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

        int ergebnis = 0;

        GregorianCalendar temp = new GregorianCalendar(werte[2], werte[1], werte[0]);
        GregorianCalendar temp2 = new GregorianCalendar(werte[7], werte[6], werte[5]);

        if (temp.compareTo(temp2) == 0){
            if((werte[8]*60 + werte[9] - werte[3]* 60 + werte[4] > 720)){
                ergebnis = ergebnis +12;
            }
        }



        if (temp.compareTo(temp2) < 0){
            if (werte[3] < 12 || (werte[3] == 12 && werte[4] == 0)){
                ergebnis = ergebnis + 12;
            }
            if (werte[8] > 12 || (werte[8] == 12 && werte[9] == 0)){
                ergebnis = ergebnis + 12;
            }

            long difference = temp2.getTimeInMillis() - temp.getTimeInMillis();
            ergebnis = ergebnis + 24 * ((int)(difference / (1000 * 60 * 60 * 24))-1);
        }


        textErgebnis.setText("" + ergebnis + "€");

        buttonZurücksetzen.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.buttonspeichern:
                        Toast.makeText(getApplicationContext(), "Daten werden gespeichert.", Toast.LENGTH_SHORT).show();
                        save();
                        break;
                }
            }
        });

    }

    private void save(){
        Toast.makeText(getApplicationContext(), "Daten werden gespeichert.", Toast.LENGTH_SHORT).show();
        String[] tmp = new String[werte.length];
        for(int i = 0; i < tmp.length; i++){
            if(werte[i] < 10){
                tmp[i] = "0" + werte[i];
            } else {
                tmp[i] = "" + werte[i];
            }
        }
        Double ergebnis = Double.parseDouble(("" + textErgebnis.getText()).replace(',', '.'));

        Reise reise = new Reise("" + textTitel.getText(), tmp[3] + ":" + tmp[4] + ":00.000", tmp[8] + ":" + tmp[9] + ":00.000", tmp[2] + "-" + tmp[1] + "-" + tmp[0], tmp[7] + "-" + tmp[6] + "-" + tmp[5], ergebnis);

        db.addTrip(reise);
    }

    public Date getStartdatum(){
        return start;
    }

    public Date getEnddatum(){
        return ende;
    }

    public void setStartdatum (Date s){
        this.start = s;
    }

    public void setEnddatum (Date e){
        this.ende = e;
    }

}

