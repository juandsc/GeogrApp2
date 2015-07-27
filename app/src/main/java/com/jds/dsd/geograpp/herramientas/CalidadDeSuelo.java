package com.jds.dsd.geograpp.herramientas;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.jds.dsd.geograpp.R;

public class CalidadDeSuelo extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calidad_de_suelo);

        if (savedInstanceState == null){
            //int idArea= this.getIntent().getIntExtra("idArear", -1);
            int idCompass = this.getIntent().getIntExtra("idCompass", -1);
            Bundle bundle = new Bundle();
            bundle.putInt("idCompass", idCompass);

            fragment_calidadDelSuelo fragment = new fragment_calidadDelSuelo();

            getFragmentManager().beginTransaction().add(android.R.id.content, fragment, fragment.getClass().getSimpleName()).commit();

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calidad_de_suelo, menu);
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
