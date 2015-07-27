package com.jds.dsd.geograpp.herramientas;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.jds.dsd.geograpp.R;

public class CalidadDeSuelo_SinAreaG extends AppCompatActivity {
    private TextView TWtoolbar;
    private  String titulo = "Calidad de suelo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calidad_de_suelo_sin_area_g);


       // TWtoolbar = (TextView) findViewById(R.id.textViewToolBar_app2);
       // TWtoolbar.setText(titulo);

        /*+if (savedInstanceState == null){
            f_CalidadDelSuelo_sinArea fragment = new f_CalidadDelSuelo_sinArea();
            getFragmentManager().beginTransaction().add(android.R.id.content, fragment, fragment.getClass().getSimpleName()).commit();

        }*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calidad_de_suelo_sin_area_g, menu);
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
}
