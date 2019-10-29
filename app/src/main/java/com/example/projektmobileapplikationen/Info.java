package com.example.projektmobileapplikationen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Info extends AppCompatActivity implements View.OnClickListener {

    Button buttonZurück;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        buttonZurück = (Button) findViewById(R.id.buttonZurück);

        buttonZurück.setOnClickListener(this);
    }

    public void onClick (View view){
        //Schließen der Info Activity
        finish();
    }
}
