package com.example.pc.appemployers;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
public class Dashboard extends Activity {
    TextView profile,jobview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        profile= (TextView) findViewById(R.id.profile);
        jobview= (TextView) findViewById(R.id.jobview);
        profileView();
    }
    public void profileView(){
        RequestQueue requestQueue=Volley.newRequestQueue(Dashboard.this);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(Dashboard.this);
        int clLoginId=pref.getInt("clLoginId",0);
        String loginid=Integer.toString(clLoginId);
        final String PROFILEVIEW_URL="http://10.100.100.35:8080/ews/clLogins/getClientProfile/"+loginid+"?key=HHEemmppllooyyeerraappii";
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, PROFILEVIEW_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) throws JSONException {
                profile.setText(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Dashboard.this,"not working",Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
    public void jobView(View view) {
        RequestQueue requestQueue=Volley.newRequestQueue(Dashboard.this);
        final String JOBVIEW_URL="http://10.100.100.35:8080/ews/job/getJobCount?clLoginId=10270&status=1&key=HHEemmppllooyyeerraappii";
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, JOBVIEW_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) throws JSONException {
                JSONObject object=response.getJSONObject("responseObject");
                JSONObject object1=object.getJSONObject("data");
                int jobcount=object1.getInt("totalJobCount");
                int thismonth=object1.getInt("jobThisMonth");
                jobview.setText("total job count:"+jobcount+"this month:"+thismonth);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    public void admin(View view) {
        Intent intent=new Intent(Dashboard.this,Alert.class);
        startActivity(intent);
    }
}

