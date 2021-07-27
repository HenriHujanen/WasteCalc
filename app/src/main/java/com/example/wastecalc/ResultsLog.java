package com.example.wastecalc;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ResultsLog extends AppCompatActivity {

    private TextView menuButton;
    private Button graphButton;
    Context context;
    String[] datesList;
    String[] CO2entry;
    String[] combined;
    String[] entry;

    public static ResultsList resultslist;

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

            combined = new String[count];
            datesList = new String[count];
            CO2entry = new String[count];
            entry = new String[1];
            int i = 0;
            BufferedReader br2 = new BufferedReader(new FileReader(yourFilePath));
            while ((line = br2.readLine()) != null) {
                combined[i] = line;
                entry = line.split(":");
                datesList[i] = entry[0];
                CO2entry[i] = entry[1];
                i++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultslist = new ResultsList(CO2entry, datesList);
        resultslist.setCombined(combined);

        /* Display the results in a ListView */
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, resultslist.getCombined());

        ListView listview = (ListView)findViewById(R.id.listViewResults);
        listview.setAdapter(adapter);

        /* The user can go back to the main menu using the back to menu button */
        menuButton = findViewById(R.id.textViewLogmenubutton);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultsLog.this, MainMenu.class));
            }
        });

        /* Show graph based on results */
        graphButton = findViewById(R.id.buttonShowGraph);
        graphButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultsLog.this, ResultsGraph.class));
            }
        });
    }
}