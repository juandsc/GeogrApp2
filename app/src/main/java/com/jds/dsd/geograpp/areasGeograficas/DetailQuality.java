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
import info.sqlite.helper.RockQuality;

public class DetailQuality extends ActionBarActivity implements View.OnClickListener{
    private TextView name, description, createdAt, q1, q2, quality;
    private Button deleteQuality;
    private AreasContract areasContract;
    private RockQuality rockQuality;
    private int idQuality, idArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_quality);

        name = (TextView) findViewById(R.id.nameQuality);
        description = (TextView) findViewById(R.id.descriptionQuality);
        createdAt = (TextView) findViewById(R.id.createdAtQuality);
        q1 = (TextView) findViewById(R.id.q1);
        q2 = (TextView) findViewById(R.id.q2);
        quality = (TextView) findViewById(R.id.quality);

        deleteQuality = (Button) findViewById(R.id.deleteQuality);
        deleteQuality.setOnClickListener(this);

        long extra = this.getIntent().getLongExtra("idQuality", -1);
        idQuality = (int) extra;
        idArea = this.getIntent().getIntExtra("idArea", -1);

        areasContract = new AreasContract(this);
        rockQuality = areasContract.getRockQuality(idQuality);

        name.setText(rockQuality.getName());
        description.setText(rockQuality.getDescription());
        createdAt.setText("Fecha de Creacion: "+rockQuality.getCreated_At());
        q1.setText("Q1: "+rockQuality.getQ1());
        q2.setText("Q2: "+rockQuality.getQ2());
        quality.setText("Calidad de Roca: "+rockQuality.getQuality());
    }

    @Override
    public void onClick(View v) {
        areasContract.deleteRockQuality(idQuality);
        startActivity(new Intent(this, QualityArea.class).putExtra("idArea", idArea));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, QualityArea.class).putExtra("idArea", idArea));
    }
}
