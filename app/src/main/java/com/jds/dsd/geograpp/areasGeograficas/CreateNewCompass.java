package com.jds.dsd.geograpp.areasGeograficas;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jds.dsd.geograpp.R;
import com.jds.dsd.geograpp.herramientas.Brujula;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import info.sqlite.helper.AreasContract;
import info.sqlite.helper.CompassMeasure;

public class CreateNewCompass extends ActionBarActivity implements View.OnClickListener{
    private EditText newName, newDescription;
    private Button crear;
    private AreasContract areasContract;
    private CompassMeasure compassMeasure;
    private String name, description, createdAt;
    private int idArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_compass);
        idArea = this.getIntent().getIntExtra("idArea", -1);
        newName = (EditText) findViewById(R.id.newNameArea);
        newDescription = (EditText) findViewById(R.id.newDescriptionArea);
        crear = (Button) findViewById(R.id.buttonCrearArea);
        crear.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_new_compass, menu);
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
        areasContract = new AreasContract(this);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String dateTime = dateFormat.format(new Date());

        String nameCompassMeasure = newName.getText().toString();
        String descriptionCompass = newDescription.getText().toString();

        if(nameCompassMeasure.isEmpty()){
            int numCompass = areasContract.getCountCompassMeasureByeArea(idArea);
            if(numCompass == 0){
                numCompass = 1;
            }
            else{
                numCompass = numCompass+1;
            }
            nameCompassMeasure = "Medida con Brújula de Geólogo "+numCompass;
        }
        if(descriptionCompass.isEmpty()){
            descriptionCompass = "Estos son los datos obtenidos con la Brújula de Geólogo";
        }
        compassMeasure = new CompassMeasure(nameCompassMeasure, descriptionCompass,dateTime, idArea);
        areasContract.createCompassMeasure(compassMeasure);

        compassMeasure = areasContract.getLastCompassMeasure();
        int idCompass = compassMeasure.getId();
        Intent intent = new Intent(this, Brujula.class);
        intent.putExtra("idArea", idArea);
        intent.putExtra("idCompass", idCompass);
        startActivity(intent);
    }
}
