package com.example.pc.appemployers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
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

public class MainActivity extends Activity {
EditText etemail,etpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        int clLoginId=pref.getInt("clLoginId",0);
        String emailId=pref.getString("emailId",null);
        if(clLoginId!=0&&emailId!=null){
            Intent intent=new Intent(MainActivity.this,Dashboard.class);
            startActivity(intent);
        }
        else {
            setContentView(R.layout.activity_main);
            etemail = (EditText) findViewById(R.id.emaillogin);
            etpassword = (EditText) findViewById(R.id.passwordlogin);
        }
    }

    public void register(View view) {
        Intent intent = new Intent(MainActivity.this, registration.class);
        startActivity(intent);
    }

    public void login(View view) {
        String email=etemail.getText().toString();
        String password=etpassword.getText().toString();
        String LOGIN_URL = "http://10.100.100.35:8080/ews/clLogins/authenticateLogin?key=HHEemmppllooyyeerraappii";
        RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
        Map<String,String> params=new HashMap<>();
        params.put("emailId",email);
        params.put("password",password);
        JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.POST, LOGIN_URL, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)throws JSONException {
                JSONObject object=response.getJSONObject("responseObject");
                JSONObject object1=object.getJSONObject("data");
                int clLoginId=object1.getInt("clLoginId");
                String firstName=object1.getString("firstName");
                String lastName=object1.getString("lastName");
                String emailId=object1.getString("emailId");
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("clLoginId",clLoginId);
                editor.putString("firstName",firstName);
                editor.putString("lastName",lastName);
                editor.putString("emailId",emailId);
                editor.commit();
Intent intent=new Intent(MainActivity.this, Launcher.class);
                startActivity(intent);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
requestQueue.add(objectRequest);

    }

    public void forgetpass(View view) {
        Intent intent=new Intent(MainActivity.this,Forgetpassword.class);
        startActivity(intent);
    }

    public void postAJob(View view) {
Intent intent=new Intent(MainActivity.this,PostAJob.class);
        startActivity(intent);
    }

    public void searchCandidate(View view) {
        Intent intent=new Intent(MainActivity.this,SearchCandidate.class);
        startActivity(intent);
    }
}