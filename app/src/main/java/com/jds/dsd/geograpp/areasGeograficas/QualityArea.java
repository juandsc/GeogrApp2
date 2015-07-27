package com.jds.dsd.geograpp.areasGeograficas;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.jds.dsd.geograpp.R;

public class QualityArea extends ActionBarActivity implements View.OnClickListener{
    private Button newQuality;
    private int idArea;
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quality_area);

        newQuality = (Button) findViewById(R.id.newQuality);
        newQuality.setOnClickListener(this);

        idArea = this.getIntent().getIntExtra("idArea", -1);
        id = idArea;
    }


    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, CreateNewQuality.class).putExtra("idArea", idArea));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, DetailArea.class).putExtra("id", id));
    }
}
