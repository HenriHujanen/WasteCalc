package com.example.wastecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ResultsLog extends AppCompatActivity {

    private TextView menuButton;
    Context context;
    String[] resultsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_log);
        this.context = getApplicationContext();

        String yourFilePath = context.getFilesDir() + "/" + "CalcLog.csv";
        String line = "";

        /* Read the saved CalcLog.csv file into an array */
        try {
            BufferedReader br = new BufferedReader(new FileReader(yourFilePath));
            int count = 0;
            while ((br.readLine()) != null) {
                count++;
            }

            resultsList = new String[count];
            int i = 0;
            BufferedReader br2 = new BufferedReader(new FileReader(yourFilePath));
            while ((line = br2.readLine()) != null) {
                resultsList[i] = line;
                i++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* Display the array in a ListView */
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, resultsList);

        ListView listview = (ListView)findViewById(R.id.listViewResults);
        listview.setAdapter(adapter);

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