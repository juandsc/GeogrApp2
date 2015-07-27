package com.jds.dsd.geograpp.areasGeograficas;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.jds.dsd.geograpp.R;
import com.jds.dsd.geograpp.herramientas.Brujula;

public class CompassMeasures extends ActionBarActivity implements View.OnClickListener{
    private Button newMeasure;
    private int idArea;
    long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass_measures);

        idArea = this.getIntent().getIntExtra("idArea", -1);
        id = idArea;
        newMeasure = (Button) findViewById(R.id.newCompass);
        newMeasure.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_compass_measures, menu);
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
        startActivity(new Intent(this, CreateNewCompass.class).putExtra("idArea", idArea));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, DetailArea.class).putExtra("id", id));
    }
}
