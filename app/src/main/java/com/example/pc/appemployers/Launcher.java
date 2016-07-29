package com.example.pc.appemployers;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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

public class Launcher extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView profile;
   protected DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        profile= (TextView) findViewById(R.id.profile);


         drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        profileView();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.launcher, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.alertsettings) {
            Intent intent = new Intent(Launcher.this, Alert.class);
            startActivity(intent);

        }
        else if(id==R.id.updateprofile){
            Intent intent=new Intent(Launcher.this,UpdateProfile.class);
            startActivity(intent);
        return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.dashboard) {

        } else if (id == R.id.jobresponses) {

        } else if (id == R.id.search) {

        } else if (id == R.id.reports) {
            Intent intent=new Intent(Launcher.this,Reports.class);
            startActivity(intent);

        } else if (id == R.id.organization) {

        } else if (id == R.id.logout) {
            //remove sharedpreferences
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(Launcher.this);
            SharedPreferences.Editor editor = pref.edit();
            editor.remove("clLoginId");
            editor.remove("emailId");
            editor.apply();
            //removing history
            Intent intent=new Intent(Launcher.this,MainActivity.class);
             intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
             intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
           this.startActivity(intent);
            this.finish();


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void profileView(){
        RequestQueue requestQueue= Volley.newRequestQueue(Launcher.this);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(Launcher.this);
        int clLoginId=pref.getInt("clLoginId",0);
        String loginid=Integer.toString(clLoginId);
        final String PROFILEVIEW_URL="http://10.100.100.35:8080/ews/clLogins/getClientProfile/"+loginid+"?key=HHEemmppllooyyeerraappii";
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, PROFILEVIEW_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) throws JSONException {
               JSONObject ob1=response.getJSONObject("responseObject");
                JSONObject ob2=ob1.getJSONObject("data");
                String name=ob2.getString("name");
                String designation=ob2.getString("designationName");
                String company=ob2.getString("companyName");
                profile.setText("Hi,"+ "  "+name+"  "+designation+" "+"@"+" "+company);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Launcher.this,"not working",Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }


    public void Search_candidate(View view) {
        Intent intent=new Intent(Launcher.this,Search.class);
        startActivity(intent);
    }

    public void Post_job(View view) {
        Intent intent=new Intent(Launcher.this,JobsView.class);
        startActivity(intent);
    }

    public void Jobs(View view) {
        Intent intent=new Intent(Launcher.this,JobsView.class);
        startActivity(intent);
    }

    public void Pending_applications(View view) {
        Intent intent=new Intent(Launcher.this,JobsView.class);
        startActivity(intent);


        }

    }

