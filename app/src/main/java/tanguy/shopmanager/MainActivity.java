package tanguy.shopmanager;

import android.content.Intent;
import android.graphics.Color;
import android.media.effect.Effect;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import tanguy.shopmanager.charts.BarChartActivity;
import tanguy.shopmanager.charts.TimeSpentChartActivity;
import tanguy.shopmanager.weather.WeatherManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Ajoute le logo du magasin
        TextView logo = (TextView) findViewById(R.id.logoTextView);
        logo.setText("To Be Or To Have");
        logo.setTextColor(Color.WHITE);


        TextView welcomeMessage = (TextView) findViewById(R.id.welcomeTextView);
        welcomeMessage.setTextColor(Color.WHITE);

        //Adapte le texte a la meteo actuelle du magasin
        TextView textView = (TextView) findViewById(R.id.weatherTextView);
        textView.setTextColor(Color.WHITE);
        try {
            WeatherManager weather = new WeatherManager("Biot", this.getApplicationContext());
            Log.d("generateWeatherReport", "test");
            Log.d("generateWeatherReport", weather.generateWeatherReport());
            textView.setText(weather.generateWeatherReport());
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_table) {
            TableActivity tableActivity = new TableActivity();
            Intent myIntent = new Intent(this, TableActivity.class);
            this.startActivity(myIntent);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_share) {
            BarChartActivity barChartActivity = new BarChartActivity();
            Intent myIntent = new Intent(this, BarChartActivity.class);
            this.startActivity(myIntent);
        } else if (id == R.id.nav_send) {
            TimeSpentChartActivity timeSpentChartActivity = new TimeSpentChartActivity();
            Intent myIntent = new Intent(this, TimeSpentChartActivity.class);
            this.startActivity(myIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}