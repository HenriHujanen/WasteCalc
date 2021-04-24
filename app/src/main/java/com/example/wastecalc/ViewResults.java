package com.example.wastecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ViewResults extends AppCompatActivity {
    private TextView menuButton;
    private TextView calcResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_results);

        /* Set the TextView into the calculated result */
        calcResults = findViewById(R.id.textViewCalcResults);
        String text = getIntent().getStringExtra("key");
        if (text != null) {
            calcResults.setText(text);
        }

        /* The user can go back to the main menu using the back to menu button*/
        menuButton = findViewById(R.id.textViewBacktomenu);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewResults.this, MainMenu.class));
            }
        });
    }
}