package com.example.projektmobileapplikationen;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.view.*;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
