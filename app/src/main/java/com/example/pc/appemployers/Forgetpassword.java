package com.example.pc.appemployers;


import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class Forgetpassword extends Activity {
    EditText etupdateemail;
    TextView tv1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
        etupdateemail = (EditText) findViewById(R.id.updateemail);


    }

    public void updatepass(View view) {
        String updateemail = etupdateemail.getText().toString();
        String UPDATE_URL = "http://10.100.100.35:8080/ews/forgotPassword/sendResetLink?emailId=" + updateemail + "&key=HHEemmppllooyyeerraappii";
        RequestQueue requestQueue = Volley.newRequestQueue(Forgetpassword.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, UPDATE_URL, null, new Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) throws JSONException {
               tv1.setText(response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

requestQueue.add(jsonObjectRequest);
    }


}