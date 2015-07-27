package com.jds.dsd.geograpp.areasGeograficas;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jds.dsd.geograpp.R;
import com.jds.dsd.geograpp.herramientas.Brujula;
import com.jds.dsd.geograpp.herramientas.CalidadDeSuelo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import info.sqlite.helper.AreasContract;
import info.sqlite.helper.CompassMeasure;
import info.sqlite.helper.RockQuality;

public class CreateNewQuality extends ActionBarActivity implements View.OnClickListener{
    private EditText newName, newDescription;
    private Button crearQuality;
    private AreasContract areasContract;
    private RockQuality rockQuality;
    private int idArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_quality);

        idArea = this.getIntent().getIntExtra("idArea", -1);
        newName = (EditText) findViewById(R.id.newNameQuality);
        newDescription = (EditText) findViewById(R.id.newNameQuality);
        crearQuality = (Button) findViewById(R.id.buttonCrearQuality);
        crearQuality.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        areasContract = new AreasContract(this);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String dateTime = dateFormat.format(new Date());

        String nameQuality = newName.getText().toString();
        String descriptionQuality = newDescription.getText().toString();
        if(nameQuality.isEmpty()){
            int numQuality = areasContract.getCountRockQualityByArea(idArea);
            if(numQuality == 0){
                numQuality = 1;
            }
            else{
                numQuality = numQuality+1;
            }
            nameQuality = "Medida de Calidad de Roca "+numQuality;
        }
        if(descriptionQuality.isEmpty()){
            descriptionQuality = "Estos son los resultados obtenidos para la calidad de esta roca";
        }
        rockQuality = new RockQuality(nameQuality, descriptionQuality,dateTime, idArea);
        areasContract.createRockQuality(rockQuality);

        rockQuality = areasContract.getLastRockQuality();
        int idQuality = rockQuality.getId();
        Intent intent = new Intent(this, CalidadDeSuelo.class);
        intent.putExtra("idArea", idArea);
        intent.putExtra("idQuality", idQuality);
        startActivity(intent);
    }
}
