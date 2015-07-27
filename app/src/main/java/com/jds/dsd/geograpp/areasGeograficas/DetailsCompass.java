package com.jds.dsd.geograpp.areasGeograficas;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jds.dsd.geograpp.R;

import info.sqlite.helper.AreasContract;
import info.sqlite.helper.CompassMeasure;

public class DetailsCompass extends ActionBarActivity implements View.OnClickListener{

    private TextView name, description, createdAt, orientation, inclination, latitude, longitude;
    private Button deleteCompass;
    private AreasContract areasContract;
    private CompassMeasure compassMeasure;
    private int idCompass;
    private int idArea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_compass);

        idArea = this.getIntent().getIntExtra("idArea", -1);

        deleteCompass = (Button) findViewById(R.id.buttonDeleteCompas);
        deleteCompass.setOnClickListener(this);
        name = (TextView) findViewById(R.id.nameCompass);
        description = (TextView) findViewById(R.id.descriptionCompass);
        createdAt = (TextView) findViewById(R.id.createdAtCompass);
        orientation = (TextView) findViewById(R.id.orientation);
        inclination = (TextView) findViewById(R.id.inclination);
        latitude = (TextView) findViewById(R.id.latitud);
        longitude = (TextView) findViewById(R.id.longitud);

        long extra = this.getIntent().getLongExtra("idCompass", -1);
        idCompass = (int)extra;
        areasContract = new AreasContract(this);
        compassMeasure = areasContract.getCompassMeasure(idCompass);

        name.setText(compassMeasure.getName());
        description.setText(compassMeasure.getDescription());
        createdAt.setText("Fecha de creacion: "+compassMeasure.getCreated_At());
        orientation.setText("Orientacion de la Formación: "+compassMeasure.getOrientation());
        inclination.setText("Inclinacion de la Formación: "+compassMeasure.getLevel());
        latitude.setText("Ubicación de la Formación: "+compassMeasure.getLatitude());
        longitude.setText("                           "+Double.toString(compassMeasure.getLongitude()));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details_compass, menu);
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
        areasContract.deleteCompassMeasure(idCompass);
        startActivity(new Intent(this, CompassMeasures.class).putExtra("idArea", idArea));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, CompassMeasures.class).putExtra("idArea", idArea));
    }
}
