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
import com.jds.dsd.geograpp.herramientas.Brujula;
import com.jds.dsd.geograpp.herramientas.CalidadDeSuelo;

import info.sqlite.helper.AreasContract;
import info.sqlite.helper.CompassMeasure;
import info.sqlite.helper.GeograficArea;

public class DetailArea extends ActionBarActivity implements View.OnClickListener{
    private TextView name, description, createdAt;
    private Button brujula, quality, deleteArea;
    private GeograficArea geograficArea;
    private AreasContract areasContract;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_area);
        name = (TextView) findViewById(R.id.nameArea);
        description = (TextView) findViewById(R.id.description);
        createdAt = (TextView) findViewById(R.id.createdAtArea);
        brujula = (Button) findViewById(R.id.brujulaArea);
        quality = (Button) findViewById(R.id.quilityArea);
        deleteArea = (Button) findViewById(R.id.buttonDeleteArea);

        brujula.setOnClickListener(this);
        quality.setOnClickListener(this);
        deleteArea.setOnClickListener(this);

        areasContract = new AreasContract(this);
        long extra = this.getIntent().getLongExtra(areasContract.ID, -1);
        id = (int) extra;
        geograficArea = areasContract.getGeograficArea(id);

        name.setText(geograficArea.getName());
        description.setText(geograficArea.getDescription());
        createdAt.setText("Fecha de creación: "+geograficArea.getCreated_at());


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail_area, menu);
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
            case R.id.brujulaArea:
                startActivity(new Intent(this, CompassMeasures.class).putExtra("idArea", id));
                break;
            case R.id.quilityArea:
                startActivity(new Intent(this, QualityArea.class).putExtra("idArea", id));
                break;
            case R.id.buttonDeleteArea:
                areasContract.deleteGeograficArea(id);
                startActivity(new Intent(this, AreasGeograficas.class));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, AreasGeograficas.class));
    }
}
