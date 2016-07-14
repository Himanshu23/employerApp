package com.example.pc.appemployers;

import android.app.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class registration extends Activity {
EditText etname,etemail,etpassword,etconfirmpassword,etcompanyname,etdesignation,etmobile,etmobilecode;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        etname= (EditText) findViewById(R.id.name);
        etemail= (EditText) findViewById(R.id.email);
        etpassword= (EditText) findViewById(R.id.password);
        etconfirmpassword= (EditText) findViewById(R.id.confirmpassword);
        etcompanyname= (EditText) findViewById(R.id.companyname);
        etdesignation= (EditText) findViewById(R.id.designation);
        etmobile= (EditText) findViewById(R.id.mobile);
        etmobilecode= (EditText) findViewById(R.id.code);
        tv= (TextView) findViewById(R.id.tv1);
    }


    public void register(View view) {
        String name=etname.getText().toString();
        String email=etemail.getText().toString();
        String password=etpassword.getText().toString();
        String confirmpassword=etconfirmpassword.getText().toString();
        String companyname=etcompanyname.getText().toString();
        String designation=etdesignation.getText().toString();
        String mobile=etmobile.getText().toString();
        String mobilecode=etmobilecode.getText().toString();
        String REGISTER_URL="http://10.100.100.35:8080/ews/clLogins/postLogins?key=HHEemmppllooyyeerraappii";
        RequestQueue requestQueue= Volley.newRequestQueue(registration.this);
        if(password.equals(confirmpassword)) {
            Map<String, String> params = new HashMap<>();
            params.put("firstName", name);
            params.put("password", password);
            params.put("emailId", email);
            params.put("mobileNo", mobile);
            params.put("mobileCode", mobilecode);
            params.put("companyName", companyname);
            params.put("designationName", designation);

            JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.POST, REGISTER_URL,new JSONObject(params), new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) throws JSONException {
                    Toast.makeText(registration.this,response.toString(),Toast.LENGTH_LONG).show();
                   /* JSONObject object=response.getJSONObject("responseObject");
                    JSONObject object1=object.getJSONObject("data");
                    int loginId=object1.getInt("clLoginId");
                    String firstName=object1.getString("firstName");
                    String lastName=object1.getString("lastName");
                    String emailId=object1.getString("emailId");
                    SharedPreferences pref = getSharedPreferences("MyPref", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putInt("loginId",loginId);
                    editor.putString("firstName",firstName);
                    editor.putString("lastName",lastName);
                    editor.putString("emailId",emailId);
                    editor.commit();

                    Intent intent=new Intent(registration.this,Launcher.class);
                    startActivity(intent);*/

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            requestQueue.add(objectRequest);
        }
        else
            Toast.makeText(registration.this,"password not matched",Toast.LENGTH_LONG).show();
    }
}
