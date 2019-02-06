package com.prueba.sharedpreferencesexample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    private TextView txt_box_1;
    private TextView txt_box_2;
    private TextView txt_box_3;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_box_1 = findViewById(R.id.box_1);
        txt_box_2 = findViewById(R.id.box_2);
        txt_box_3 = findViewById(R.id.box_3);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        setupBoxOneSharedPreference();
        setupBoxTwoSharedPreference();
        setupBoxThreeSharedPreference();
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    private void setupBoxOneSharedPreference(){
        txt_box_1.setBackgroundColor(sharedPreferences.getBoolean(getString(R.string.pref_key_box_1), true)?
                ContextCompat.getColor(this, R.color.yellow):
                ContextCompat.getColor(this, android.R.color.white));
    }

    private void setupBoxTwoSharedPreference(){
        txt_box_2.setBackgroundColor(sharedPreferences.getBoolean(getString(R.string.pref_key_box_2), true)?
                ContextCompat.getColor(this, R.color.green):
                ContextCompat.getColor(this, android.R.color.white));
    }
    private void setupBoxThreeSharedPreference(){
        txt_box_3.setBackgroundColor(sharedPreferences.getBoolean(getString(R.string.pref_key_box_3), true)?
                ContextCompat.getColor(this, R.color.blue):
                ContextCompat.getColor(this, android.R.color.white));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings){
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.pref_key_box_1))) setupBoxOneSharedPreference();
        if (key.equals(getString(R.string.pref_key_box_2))) setupBoxTwoSharedPreference();
        if (key.equals(getString(R.string.pref_key_box_3))) setupBoxThreeSharedPreference();
    }
}
