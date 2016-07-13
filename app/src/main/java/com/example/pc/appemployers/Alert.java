package com.example.pc.appemployers;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Alert extends Activity {
    RadioGroup rg1,rg2,rg3,rg4;
    RequestQueue requestQueue;
    int jobAlert,recommendationsStatus,monthlyActivity,appAlertDaily,appAlertWeekly;
    SharedPreferences pref ;
    int clLoginId;
    String loginid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_alert);
      pref  = PreferenceManager.getDefaultSharedPreferences(Alert.this);
         clLoginId=pref.getInt("clLoginId",0);
         loginid=Integer.toString(clLoginId);
        rg1= (RadioGroup) findViewById(R.id.radioGroup1);
        rg2= (RadioGroup) findViewById(R.id.radioGroup2);
        rg3= (RadioGroup) findViewById(R.id.radioGroup3);
        rg4= (RadioGroup) findViewById(R.id.radioGroup4);
        requestQueue=Volley.newRequestQueue(this);
        getsettings();


    }
    public void getsettings(){

        String GETCLIENTSETTINGS_URL="http://10.100.100.35:8080/ews/ClSettings/getClientSettings?"+loginid+"&key=HHEemmppllooyyeerraappii";
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, GETCLIENTSETTINGS_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) throws JSONException {
                JSONObject object=response.getJSONObject("responseObject");
                JSONObject object1=object.getJSONObject("data");
                appAlertDaily=object1.getInt("appAlertDaily");
                appAlertWeekly=object1.getInt("appAlertWeekly");
                jobAlert=object1.getInt("jobAlert");
                recommendationsStatus=object1.getInt("recommendationsStatus");
                monthlyActivity=object1.getInt("monthlyActivity");
                Toast.makeText(Alert.this," "+appAlertDaily+" "+appAlertWeekly+" "+jobAlert+" "+recommendationsStatus+" "+monthlyActivity,Toast.LENGTH_LONG).show();
                if(appAlertWeekly==1) {
                    rg1.check(R.id.applicationalertsweekly);
                }
                else {
                    rg1.check(R.id.applicationalertsdaily);
                }
                if(jobAlert==0) {
                    rg2.check(R.id.jobpostingalertsno);
                }
                else {
                    rg2.check(R.id.jobpostingalertsyes);
                }
                if(recommendationsStatus==0) {
                    rg3.check(R.id.recommendationno);
                }
                else
                    rg3.check(R.id.recommendationyes);
                if(monthlyActivity==0) {
                    rg4.check(R.id.monthlyno);
                }
                else
                    rg4.check(R.id.monthlyyes);

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(jsonObjectRequest);
    }




    public void updatesettings(View view) {

          if(rg1.getCheckedRadioButtonId()==R.id.applicationalertsdaily){
              appAlertDaily=1;
              appAlertWeekly=0;
          }
             else {
              appAlertWeekly=1;
              appAlertDaily=0;
          }

if(rg2.getCheckedRadioButtonId()==R.id.jobpostingalertsyes) {
    jobAlert = 1;
}
else {
    jobAlert = 0;
}
        if(rg3.getCheckedRadioButtonId()==R.id.recommendationyes) {
            recommendationsStatus = 1;
        }
        else {
            recommendationsStatus = 0;
        }
        if(rg4.getCheckedRadioButtonId()==R.id.monthlyyes) {
            monthlyActivity = 1;
        }
        else {
            monthlyActivity = 0;

        }
        String UPDATESETTINGS_URL="http://10.100.100.35:8080/ews/ClSettings/updateClientSettings?"+loginid+"&key=HHEemmppllooyyeerraappii";
        Map<String,Integer> params=new HashMap<>();
        params.put("appAlertDaily",appAlertDaily);
        params.put("appAlertWeekly",appAlertWeekly);
        params.put("jobAlert",jobAlert);
        params.put("recommendationsStatus",recommendationsStatus);
        params.put("monthlyActivity",monthlyActivity);
        params.put("loginId",clLoginId);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, UPDATESETTINGS_URL,new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) throws JSONException {
Toast.makeText(Alert.this," "+appAlertDaily+" " +appAlertWeekly+" "+jobAlert+" "+recommendationsStatus+" "+monthlyActivity,Toast.LENGTH_LONG).show();
                Intent intent=new Intent(Alert.this,Dashboard.class);
                startActivity(intent);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(jsonObjectRequest);

    }

}



