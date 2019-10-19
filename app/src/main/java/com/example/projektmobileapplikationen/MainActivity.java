package com.example.projektmobileapplikationen;

import androidx.appcompat.app.AppCompatActivity;
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
                Toast.makeText(getApplicationContext(), "Hier passiert noch nichts. Wir arbeiten daran. Haben sie etwas Geduld", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonverlauf:
                Toast.makeText(getApplicationContext(), "Hier passiert noch nichts. Wir arbeiten daran. Haben sie etwas Geduld.", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.info:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
