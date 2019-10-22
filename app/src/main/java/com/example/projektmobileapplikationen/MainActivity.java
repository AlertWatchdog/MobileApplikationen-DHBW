package com.example.projektmobileapplikationen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonZurücksetzen;
    Button buttonVerlauf;
    Button buttonBerechnen;
    EditText editTitel;
    EditText editATag;
    EditText editAMonat;
    EditText editAJahr;
    EditText editAStunde;
    EditText editAMinute;
    EditText editETag;
    EditText editEMonat;
    EditText editEJahr;
    EditText editEStunde;
    EditText editEMinute;

    String titel;
    int atag, amonat, ajahr, astunde, aminute, etag, emonat, ejahr, estunde, eminute = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            buttonZurücksetzen = (Button) findViewById(R.id.buttonZurücksetzen);
            buttonVerlauf = (Button) findViewById((R.id.buttonverlauf));
            buttonBerechnen = (Button) findViewById((R.id.buttonberechnen));
            editTitel = (EditText) findViewById((R.id.editTitel));
            editATag = (EditText) findViewById((R.id.editATag));
            editAMonat = (EditText) findViewById((R.id.editAMonat));
            editAJahr = (EditText) findViewById((R.id.editAJahr));
            editAStunde = (EditText) findViewById((R.id.editAStunde));
            editAMinute = (EditText) findViewById((R.id.editAMinute));
            editETag = (EditText) findViewById((R.id.editETag));
            editEMonat = (EditText) findViewById((R.id.editEMonat));
            editEJahr = (EditText) findViewById((R.id.editEJahr));
            editEStunde = (EditText) findViewById((R.id.editEStunde));
            editEMinute = (EditText) findViewById((R.id.editEMinute));

            buttonZurücksetzen.setOnClickListener(this);
            buttonVerlauf.setOnClickListener(this);
            buttonBerechnen.setOnClickListener(this);
    }


    //ClickEvents für die Button auf dieser Seite
    @Override
    public void onClick (View view){

        switch(view.getId()){
            case R.id.buttonZurücksetzen:
                editTitel.setText("");
                editATag.setText("dd");
                editAMonat.setText("mm");
                editAJahr.setText("yyyy");
                editAStunde.setText("hh");
                editAMinute.setText("mm");
                editETag.setText("dd");
                editEMonat.setText("mm");
                editEJahr.setText("yyyy");
                editEStunde.setText("hh");
                editEMinute.setText("mm");
                break;

            case R.id.buttonberechnen:

                try {
                    titel = editTitel.toString();
                    atag = Integer.parseInt(editATag.getText().toString());
                    amonat = Integer.parseInt(editAMonat.getText().toString());
                    ajahr = Integer.parseInt(editAJahr.getText().toString());
                    astunde = Integer.parseInt(editAStunde.getText().toString());
                    aminute = Integer.parseInt(editAMinute.getText().toString());
                    etag = Integer.parseInt(editETag.getText().toString());
                    emonat = Integer.parseInt(editEMonat.getText().toString());
                    ejahr = Integer.parseInt(editEJahr.getText().toString());
                    estunde = Integer.parseInt(editEStunde.getText().toString());
                    eminute = Integer.parseInt(editEMinute.getText().toString());

                } catch (NumberFormatException nfw){
                    Toast.makeText(getApplicationContext(), nfw.toString(), Toast.LENGTH_LONG).show();
                    break;
                }catch (Exception e ){
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                }

                if (amonat > 12){
                    amonat = 12;
                }
                if (atag < 0){
                    atag = 0;
                }
                if (atag > 28 && amonat ==2){
                    atag = 28;
                }
                if (atag > 30 && (amonat == 4 || amonat == 6 || amonat == 9 || amonat == 11)){
                    atag = 30;
                }
                if (atag > 30 && (amonat == 1 || amonat == 3 || amonat == 5 || amonat == 7 || amonat == 8 || amonat == 10 || amonat == 12)){
                    atag = 30;
                }
                if (ajahr > 2019){
                    ajahr = 2019;
                }
                if (astunde > 23){
                    astunde = 23;
                }
                if (aminute > 59){
                    aminute = 59;
                }
                if (emonat > 12){
                    emonat = 12;
                }
                if (etag < 0){
                    etag = 0;
                }
                if (etag > 28 && emonat ==2){
                    etag = 28;
                }
                if (etag > 30 && (emonat == 4 || emonat == 6 || emonat == 9 || emonat == 11)){
                    etag = 30;
                }
                if (etag > 30 && (emonat == 1 || emonat == 3 || emonat == 5 || emonat == 7 || emonat == 8 || emonat == 10 || emonat == 12)){
                    etag = 30;
                }
                if (ejahr > 2019){
                    ejahr = 2019;
                }
                if (estunde > 23){
                    estunde = 23;
                }
                if (eminute > 59){
                    eminute = 59;
                }

                try {

                    int wert[] = new int[10];
                    wert[0] = atag;
                    wert[1] = amonat;
                    wert[2] = ajahr;
                    wert[3] = astunde;
                    wert[4] = aminute;
                    wert[5] = etag;
                    wert[6] = emonat;
                    wert[7] = ejahr;
                    wert[8] = estunde;
                    wert[9] = eminute;

                    Intent intent = new Intent(MainActivity.this, Ergebnis.class);
                    intent.putExtra("Werte", wert);
                    intent.putExtra("Titel", titel);
                    startActivity(intent);
                    break;
                } catch (Exception e){
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }

            case R.id.buttonverlauf:
                Toast.makeText(getApplicationContext(), "Hier passiert noch nichts. Wir arbeiten daran. Haben sie etwas Geduld.", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    //Erstellung des Menüs
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    //ClickEvents für die Items im Menü
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.info:
                //Öffnen der Info Activity
                Intent intent = new Intent(MainActivity.this, Info.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
