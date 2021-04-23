package com.example.wastecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultsLog extends AppCompatActivity {

    private TextView menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_log);

        /* The user can go back to the main menu using the back to menu button*/
        menuButton = findViewById(R.id.textViewLogmenubutton);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultsLog.this, MainMenu.class));
            }
        });
    }
}