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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import info.sqlite.helper.AreasContract;
import info.sqlite.helper.GeograficArea;

public class CrearAreaGeografica extends ActionBarActivity implements View.OnClickListener{
    private Button crearArea;
    private EditText name, description;
    private AreasContract areasContract;
    private GeograficArea geograficArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_area_geografica);

        crearArea = (Button) findViewById(R.id.buttonCrearArea);
        crearArea.setOnClickListener(this);

        name = (EditText) findViewById(R.id.newNameArea);
        description = (EditText) findViewById(R.id.newDescriptionArea);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_crear_area_geografica, menu);
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
        String nameArea = name.getText().toString();
        String descriptionArea = description.getText().toString();
        if(nameArea.isEmpty()){
            geograficArea = areasContract.getLastGeograficArea();
            int numArea;
            if(geograficArea == null){
                numArea = 1;
            }
            else{
                numArea = geograficArea.getId()+1;
            }
            nameArea = "Area Geografica "+numArea;
        }
        if(descriptionArea.isEmpty()){
            descriptionArea = "Esta es una Area Geográfica para el levantamiento de medidas";
        }
        geograficArea = new GeograficArea(nameArea, descriptionArea, dateTime);
        areasContract.createGeograficArea(geograficArea);

        geograficArea = areasContract.getLastGeograficArea();
        long id = geograficArea.getId();

        startActivity(new Intent(this, DetailArea.class).putExtra(areasContract.ID, id));
    }
}
