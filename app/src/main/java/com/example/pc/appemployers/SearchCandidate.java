package com.example.pc.appemployers;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;


public class SearchCandidate extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_candidate);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);


// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.LastActive, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner2.setAdapter(adapter1);

        Spinner spinner3 = (Spinner) findViewById(R.id.employersspinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.Employer1, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner3.setAdapter(adapter2);
        Spinner spinner4 = (Spinner) findViewById(R.id.excludeemployersspinner);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.Employer2, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner4.setAdapter(adapter3);
        Spinner spinner5 = (Spinner) findViewById(R.id.designationspinner);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,
                R.array.Designation, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner5.setAdapter(adapter4);
        ArrayList<String> experience = new ArrayList<String>();

        for (int i = 1; i <=39 ; i++) {
            experience.add(Integer.toString(i)+"years");
        }
        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, experience);
        ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, experience);
        Spinner minexperience = (Spinner)findViewById(R.id.minexperi);
        minexperience.setAdapter(adapter6);
        Spinner maxexperience = (Spinner)findViewById(R.id.maxexperience);
        maxexperience.setAdapter(adapter7);
        ArrayList<String> salary=new ArrayList<>();
        for (int i=10;i<96;i++) {
            if (i > 25)
                i = i + 4;
            salary.add(Integer.toString(i)+"lakh");

        }
        ArrayAdapter<String> adapter8 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, salary);
        ArrayAdapter<String> adapter9 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, salary);
        Spinner minsalary = (Spinner)findViewById(R.id.minsal);
        minsalary.setAdapter(adapter8);
        Spinner maxsalary = (Spinner)findViewById(R.id.maxsal);
        maxsalary.setAdapter(adapter9);
    }
}
