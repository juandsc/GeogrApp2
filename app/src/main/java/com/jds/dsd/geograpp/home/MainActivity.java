package com.jds.dsd.geograpp.home;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.Toolbar;

import com.jds.dsd.geograpp.R;
import com.jds.dsd.geograpp.areasGeograficas.AreasGeograficas;
import com.jds.dsd.geograpp.herramientas.Herramientas;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{
    private Button areasGeograficas;
    private Button herramientas;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        areasGeograficas = (Button) findViewById(R.id.button_areas);
        areasGeograficas.setOnClickListener(this);

        herramientas = (Button) findViewById(R.id.button_herramientas);
        herramientas.setOnClickListener(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar_app);
       toolbar.setTitle("GeogrApp");
        setSupportActionBar(toolbar);

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            case R.id.button_herramientas:
                startActivity(new Intent(this, Herramientas.class));
                break;
            case R.id.button_areas:
                startActivity(new Intent(this, AreasGeograficas.class));
                break;
            default:break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);

    }
}
