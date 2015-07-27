package com.jds.dsd.geograpp.herramientas;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.jds.dsd.geograpp.R;


public class Herramientas extends AppCompatActivity implements View.OnClickListener{
    private Button brujula;
    private Button cargarMapa;
    private Button calidadDelSuelo;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_herramientas);
        setContentView(R.layout.activity_herramientas);
        brujula = (Button)findViewById(R.id.button_brujula);

        calidadDelSuelo=(Button)findViewById(R.id.button_calidadSuelo);
        brujula.setOnClickListener(this);
        calidadDelSuelo.setOnClickListener(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar_app);
        toolbar.setTitle("Herramientas");
        setSupportActionBar(toolbar);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_herramientas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_brujula:
                startActivity(new Intent(this, BrujulaSinArea.class));
                break;
            case R.id.button_calidadSuelo:
                startActivity(new Intent(this, CalidadDeSuelo_SinAreaG.class));
            default:break;
        }
    }
}
