package com.example.pc.appemployers;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class PostAJob extends Activity  {
    Spinner country,city; RequestQueue queue;
    EditText jobTitle, jobDesCanPro, keyword, jobIndustry, jobFunctions, responseEmail;
    protected void onCreate(Bundle savedInstanceState) {
         queue= Volley.newRequestQueue(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posta_job);

        ArrayList<String> experience = new ArrayList<String>();

        for (int i = 1; i <= 39; i++) {
            experience.add(Integer.toString(i) + "years");
        }
        ArrayAdapter<String> adapterminexp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, experience);
        ArrayAdapter<String> adaptermaxexp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, experience);
        Spinner minimumexperience = (Spinner) findViewById(R.id.minimumexperience);
        minimumexperience.setAdapter(adapterminexp);
        Spinner maximumexperience = (Spinner) findViewById(R.id.maximumexperience);
        maximumexperience.setAdapter(adaptermaxexp);
        ArrayList<String> salary = new ArrayList<String>();
        for (int i = 10; i < 96; i++) {
            if (i > 25)
                i = i + 4;
            salary.add(Integer.toString(i) + "lakh");
        }
        ArrayAdapter<String> adapterminsal = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, salary);
        ArrayAdapter<String> adaptermaxsal = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, salary);
        Spinner minimumsalary = (Spinner) findViewById(R.id.minimumsal);
        minimumsalary.setAdapter(adapterminsal);
        Spinner maximumsalary = (Spinner) findViewById(R.id.maximumsal);
        maximumsalary.setAdapter(adaptermaxsal);

        getCountry();
       getCity();

    }

    public void getCountry() {
        country = (Spinner) findViewById(R.id.country);
       final ArrayList<String> country1=new ArrayList<String>();
        String COUNTRYNAME_URL="http://empapi.headhonchos.com:8080/ews/autoSuggest/getCountries";

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, COUNTRYNAME_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) throws JSONException {

           JSONObject object=response.getJSONObject("responseObject");
               JSONArray object1 =object.getJSONArray("data");

               // JSONArray object1=object.getJSONArray("data");
                for(int i=0;i<object1.length();i++) {
                     JSONObject object2 = object1.getJSONObject(i);
                     String object3 = object2.getString("name");
                     country1.add(object3);
                    //  System.out.println(country1);

                }

            }
        },
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonObjectRequest);
        ArrayAdapter<String> country2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,country1);
        country.setAdapter(country2);

    }



    public void getCity(){
        city= (Spinner) findViewById(R.id.city);
        final ArrayList<String> city1=new ArrayList<>();
String CITY_URL="http://empapi.headhonchos.com:8080/ews/autoSuggest/getCities";
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, CITY_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) throws JSONException {
                JSONObject object=response.getJSONObject("responseObject");
                JSONArray object1 =object.getJSONArray("data");

                // JSONArray object1=object.getJSONArray("data");
                for(int i=0;i<object1.length();i++) {
                    JSONObject object2 = object1.getJSONObject(i);
                    String object3 = object2.getString("name");
                    city1.add(object3);
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        queue.add(jsonObjectRequest);
        ArrayAdapter<String> city2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,city1);
        city.setAdapter(city2);

    }
    public void postData(){
        jobTitle= (EditText) findViewById(R.id.jobtitle);
        jobDesCanPro= (EditText) findViewById(R.id.jobdescription);
        keyword= (EditText) findViewById(R.id.keywordskills);

        String POSTJOB_URL="http://10.100.100.35:8080/ews/job/postJob?key=HHEemmppllooyyeerraappii";

    }




}