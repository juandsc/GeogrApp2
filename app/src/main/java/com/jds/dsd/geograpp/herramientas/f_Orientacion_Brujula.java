package com.jds.dsd.geograpp.herramientas;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jds.dsd.geograpp.R;
import com.jds.dsd.geograpp.areasGeograficas.DetailsCompass;

import info.sqlite.helper.AreasContract;

/**
 * A simple {@link Fragment} subclass.
 */
public class f_Orientacion_Brujula extends Fragment implements SensorEventListener, View.OnClickListener {

    // Views donde se cargaran los elementos del XML
    private TextView txtAngle;
    private ImageView imgCompass;
    private TextView txtAnguloInclinacion;
    private TextView txtInclinacion;

    private TextView lblLatitud;
    private TextView lblLongitud;

    private AreasContract areasContract;
    private int idCompass;
    private int idArea;
    private int numDegree = 0;
    private int numInclinacion = 0;
    private int numPosicion = 0;
    private float degreeSave;
    private float inclinacionSave;
    private double latitudSave;
    private double longitudSave;
    private int itemSave = 0;

    // guarda el angulo (grado) actual del compass
    private float currentDegree = 0f;

    // El sensor manager del dispositivo
    private SensorManager mSensorManager;
    // Los dos sensores que son necesarios porque TYPE_ORINETATION esta deprecated
    private Sensor accelerometer;
    private Sensor magnetometer;

    private LocationManager locationManager;
    private LocationListener locationListener;

    private RelativeLayout RLBrujula;


    float inclinacion;
    // Los angulos del movimiento de la flecha que señala al norte
    float degree;
    // Guarda el valor del pitch
    float pitch;
    // Guarda el valor del azimut
    float azimut;
    // Guarda los valores que cambián con las variaciones del sensor TYPE_ACCELEROMETER
    float[] mGravity;
    // Guarda los valores que cambián con las variaciones del sensor TYPE_MAGNETIC_FIELD
    float[] mGeomagnetic;
    public f_Orientacion_Brujula() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        idCompass = getActivity().getIntent().getIntExtra("idCompass", -1);
        idArea = getActivity().getIntent().getIntExtra("idArea", -1);
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_f__orientacion__brujula, container, false);
        final View view = inflater.inflate(R.layout.fragment_f__orientacion__brujula, container, false);

        // Se guardan en variables los elementos del layout
        imgCompass = (ImageView) view.findViewById(R.id.imgViewCompass);
        imgCompass.setOnClickListener(this);

        txtAngle = (TextView) view.findViewById(R.id.txtAngle);

        txtAnguloInclinacion = (TextView) view.findViewById(R.id.txt_Inclinacion);
        txtAnguloInclinacion.setOnClickListener(this);
        txtInclinacion =(TextView)view.findViewById(R.id.textView6);
        txtInclinacion.setOnClickListener(this);

        lblLatitud = (TextView)view.findViewById(R.id.LblPosLatitud);
        lblLatitud.setOnClickListener(this);
        lblLongitud = (TextView)view.findViewById(R.id.LblPosLongitud);
        lblLongitud.setOnClickListener(this);
        RLBrujula = (RelativeLayout)view.findViewById(R.id.relativeLayout);

        // Se inicializa los sensores del dispositivo android
        mSensorManager = (SensorManager)getActivity().getSystemService(Context.SENSOR_SERVICE);
        accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mGravity = null;
        mGeomagnetic = null;


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Se registra un listener para los sensores del accelerometer y el         	magnetometer
        mSensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_UI);
        actualizarPosicion();
    }

    @Override
    public void onPause() {
        super.onPause();

        // Se detiene el listener para no malgastar la bateria
        mSensorManager.unregisterListener(this);
        locationManager.removeUpdates(locationListener);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        // Se comprueba que tipo de sensor está activo en cada momento
        switch (event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                mGravity = event.values;
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                mGeomagnetic = event.values;
                break;
        }

        if ((mGravity != null) && (mGeomagnetic != null)) {
            float RotationMatrix[] = new float[16];
            boolean success = SensorManager.getRotationMatrix(RotationMatrix,                                                         	null, mGravity, mGeomagnetic);
            if (success) {
                float orientation[] = new float[3];
                SensorManager.getOrientation(RotationMatrix, orientation);
                azimut = orientation[0] * (180 / (float) Math.PI);
                pitch = orientation[1] * (180 / (float) Math.PI);
            }
        }
        degree = azimut;
        inclinacion =pitch;
        txtAngle.setText(Float.toString(degree) + " Grados");
        txtAnguloInclinacion.setText( Float.toString(inclinacion) + "°");
        // se crea la animacion de la rottacion (se revierte el giro en grados, negativo)
        RotateAnimation ra = new RotateAnimation(
                currentDegree,
                degree,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f);
        // el tiempo durante el cual la animación se llevará a cabo
        ra.setDuration(1000);
        // establecer la animación después del final de la estado de reserva
        ra.setFillAfter(true);
        // Inicio de la animacion
        imgCompass.startAnimation(ra);
        currentDegree = -degree;
    }

    private void actualizarPosicion()
    {
//Obtenemos una referencia al LocationManager
        locationManager =
                (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
//Obtenemos la última posición conocida
        Location location =
                locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//Mostramos la última posición conocida
        muestraPosicion(location);
//Nos registramos para recibir actualizaciones de la posición
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                muestraPosicion(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 10000, 0, locationListener);
    }

    private void muestraPosicion(Location loc) {
        if(loc != null)
        {
            latitudSave = loc.getLatitude();
            longitudSave = loc.getLongitude();
            lblLatitud.setText("Latitud: " + String.valueOf(loc.getLatitude()));
            lblLongitud.setText("Longitud: " + String.valueOf(loc.getLongitude()));
        }
        else
        {
            lblLatitud.setText("Latitud: (sin_datos)");
            lblLongitud.setText("Longitud: (sin_datos)");
        }
    }

    private void setNewCompass(){
        areasContract = new AreasContract(getActivity());
        areasContract.updateCompassMeasure(idCompass, degreeSave, inclinacionSave, latitudSave, longitudSave);
        long id = idCompass;

        Intent intent = new Intent(getActivity(), DetailsCompass.class);
        intent.putExtra("idCompass", id);
        intent.putExtra("idArea", idArea);
        startActivity(intent);
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgViewCompass:
                degreeSave = degree;
                Toast.makeText(getActivity(), "orientación almacenada: "+degreeSave, Toast.LENGTH_SHORT).show();
                if(numDegree == 0){
                    numDegree = 1;
                    itemSave = itemSave + 1;
                }
                if(itemSave == 3){setNewCompass();}
                break;
            case R.id.txt_Inclinacion:
                inclinacionSave = inclinacion;
                Toast.makeText(getActivity(),"Inclinación almacenada: "+inclinacionSave,Toast.LENGTH_SHORT).show();
                if(numInclinacion == 0){
                    numInclinacion = 1;
                    itemSave = itemSave + 1;
                }
                if(itemSave == 3){setNewCompass();}
                break;
            case R.id.textView6:
                inclinacionSave = inclinacion;
                Toast.makeText(getActivity(),"Inclinación almacenada: "+inclinacionSave,Toast.LENGTH_SHORT).show();
                if(numInclinacion == 0){
                    numInclinacion = 1;
                    itemSave = itemSave + 1;
                }
                if(itemSave == 3){setNewCompass();}
                break;
            case R.id.LblPosLatitud:
                Toast.makeText(getActivity(),"Posicíon almacenada: latitud "+latitudSave+
                        ", longitud "+longitudSave,Toast.LENGTH_SHORT).show();
                if(numPosicion == 0){
                    numPosicion = 1;
                    itemSave = itemSave + 1;
                }
                if(itemSave == 3){setNewCompass();}
                break;
            case R.id.LblPosLongitud:
                Toast.makeText(getActivity(),"Posicíon almacenada: latitud "+latitudSave+
                        ", longitud "+longitudSave,Toast.LENGTH_SHORT).show();
                if(numPosicion == 0){
                    numPosicion = 1;
                    itemSave = itemSave + 1;
                }
                if(itemSave == 3){setNewCompass();}
                break;

        }
    }
}
