package com.example.wastecalc;

import java.util.Arrays;
import java.util.List;

public class ResultsList {

    private String[] Combined;
    private String[] CO2entry;
    private String[] datesList;

    ResultsList(String[] co2entry, String[] datesList){
        this.CO2entry = co2entry;
        this.datesList = datesList;
    }

    public String[] getCombined() { return Combined; }

    public void setCombined(String[] combined) { Combined = combined; }

    public String[] getCO2entry() { return CO2entry; }

    public void setCO2entry(String[] CO2entry) { this.CO2entry = CO2entry; }

    public String[] getDatesList() { return datesList; }

    public void setDatesList(String[] datesList) { this.datesList = datesList; }
}
