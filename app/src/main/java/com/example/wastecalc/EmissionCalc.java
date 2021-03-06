package com.example.wastecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.util.Calendar;
import javax.xml.parsers.ParserConfigurationException;

public class EmissionCalc extends AppCompatActivity {

    private TextView menuButton;

    ClimateDietURL URL = new ClimateDietURL();

    ClimateDietEntry entry = new ClimateDietEntry();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emission_calc);

        Spinner spinnerBio = findViewById(R.id.spinnerBiowaste);
        Spinner spinnerCarton = findViewById(R.id.spinnerCarton);
        Spinner spinnerElectric = findViewById(R.id.spinnerElectronic);
        Spinner spinnerGlass = findViewById(R.id.spinnerGlass);
        Spinner spinnerHazardous = findViewById(R.id.spinnerHazardous);
        Spinner spinnerMetal = findViewById(R.id.spinnerMetal);
        Spinner spinnerPaper = findViewById(R.id.spinnerPaper);
        Spinner spinnerPlastic = findViewById(R.id.spinnerPlastic);
        Spinner spinnerEstimate = findViewById(R.id.spinnerAmountestimate);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.choices, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.choices2, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        /* Set the answer values for the drop down menus */
        spinnerBio.setAdapter(adapter);
        spinnerCarton.setAdapter(adapter);
        spinnerElectric.setAdapter(adapter);
        spinnerGlass.setAdapter(adapter);
        spinnerHazardous.setAdapter(adapter);
        spinnerMetal.setAdapter(adapter);
        spinnerPaper.setAdapter(adapter);;
        spinnerPlastic.setAdapter(adapter);
        spinnerEstimate.setAdapter(adapter2);

        /* The user can go back to the main menu using the back to menu button */
        menuButton = findViewById(R.id.textViewMenubutton);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EmissionCalc.this, MainMenu.class));
            }
        });
    }

    Context context;

    public void readJSON (View v) throws ParserConfigurationException, IOException, SAXException {
        context = EmissionCalc.this;

        /* Make the URL based on the answers */
        Spinner spinnerBio = findViewById(R.id.spinnerBiowaste);
        URL.setQueryBio("query.bioWaste=" + spinnerBio.getSelectedItem().toString() + "&");

        Spinner spinnerCarton = findViewById(R.id.spinnerCarton);
        URL.setQueryCarton("query.carton=" + spinnerCarton.getSelectedItem().toString() + "&");

        Spinner spinnerElectric = findViewById(R.id.spinnerElectronic);
        URL.setQueryElectric("query.electronic=" + spinnerElectric.getSelectedItem().toString() + "&");

        Spinner spinnerGlass = findViewById(R.id.spinnerGlass);
        URL.setQueryGlass("query.glass=" + spinnerGlass.getSelectedItem().toString() + "&");

        Spinner spinnerHazard = findViewById(R.id.spinnerHazardous);
        URL.setQueryHazard("query.hazardous=" + spinnerHazard.getSelectedItem().toString() + "&");

        Spinner spinnerMetal = findViewById(R.id.spinnerMetal);
        URL.setQueryMetal("query.metal=" + spinnerMetal.getSelectedItem().toString() + "&");

        Spinner spinnerPaper = findViewById(R.id.spinnerPaper);
        URL.setQueryPaper("query.paper=" + spinnerPaper.getSelectedItem().toString() + "&");

        Spinner spinnerPlastic = findViewById(R.id.spinnerPlastic);
        URL.setQueryPlastic("query.plastic=" + spinnerPlastic.getSelectedItem().toString() + "&");

        Spinner spinnerEstimate = findViewById(R.id.spinnerAmountestimate);
        URL.setQueryEstimate("query.amountEstimate=" + spinnerEstimate.getSelectedItem().toString() + "&");

        URL.setURL("https://ilmastodieetti.ymparisto.fi/ilmastodieetti/calculatorapi/v1/WasteCalculator?" + URL.getQueryBio() + URL.getQueryCarton() + URL.getQueryElectric() + URL.getQueryGlass() + URL.getQueryHazard() + URL.getQueryMetal() + URL.getQueryPaper() + URL.getQueryPlastic() + URL.getQueryEstimate());
        String url = URL.getURL();

        /* Get the response form the API, send it to the result viewing activity and log the result */
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest StrReq = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        entry.setYearlyConsumption(response);
                        Log.e("Rest response", entry.getYearlyConsumption());
                        Intent intent = new Intent(EmissionCalc.this, ViewResults.class);
                        intent.putExtra("key", entry.getYearlyConsumption());
                        startActivity(intent);
                        Calendar calendar = Calendar.getInstance();
                        entry.setEntryDate(DateFormat.getDateInstance().format(calendar.getTime()));
                        String logString = (entry.getEntryDate() +": " + entry.getYearlyConsumption() + "kg CO2 / year.\n");
                        try {
                            OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput("CalcLog.csv", Context.MODE_APPEND));
                            ows.write(logString);
                            ows.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest response", error.toString());
                    }
                }
        );

        requestQueue.add(StrReq);

    }
}