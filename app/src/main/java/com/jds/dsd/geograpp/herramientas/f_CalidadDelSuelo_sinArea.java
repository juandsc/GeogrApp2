package com.jds.dsd.geograpp.herramientas;



import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.jds.dsd.geograpp.R;

import java.text.DecimalFormat;

/**
 * A simple {@link Fragment} subclass.
 */
public class f_CalidadDelSuelo_sinArea extends Fragment implements View.OnClickListener  {

    private Button calcular;
    private EditText rqd;
    private EditText jr;
    private EditText jw;
    private EditText jn;
    private EditText ja;
    private EditText srf;
    private TextView resultadoQ1;
    private TextView resultadoQ2;
   // private TextView resultadoCalidad;
    private RadioGroup qs;
    private RadioButton rbq1;
    private Button limpiar;

    float q1=-9999,q2=-9999;
    DecimalFormat df = new DecimalFormat("0.00");
    public f_CalidadDelSuelo_sinArea() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_f__calidad_del_suelo_sin_area, container, false);
        calcular = (Button)view.findViewById(R.id.button_calidadSuelo);
        rqd = (EditText)view.findViewById(R.id.editText2_RQD);
        jr = (EditText)view.findViewById(R.id.editText4_Jr);
        jw = (EditText)view.findViewById(R.id.editText5_Jw);
        jn = (EditText)view.findViewById(R.id.editText6_Jn);
        ja= (EditText)view.findViewById(R.id.editText7_ja);
        srf = (EditText)view.findViewById(R.id.editText8_SRF);
        resultadoQ1=(TextView)view.findViewById(R.id.textView10_Q1resultado);
        resultadoQ2=(TextView)view.findViewById(R.id.textView10_Q2resultado);
        //resultadoCalidad = (TextView)view.findViewById(R.id.textView10_ResultadoCalidad);
        rbq1 = (RadioButton)view.findViewById(R.id.radioButton);
        calcular.setOnClickListener(this);
        limpiar= (Button)view.findViewById(R.id.buttonLimpiar);
        limpiar.setOnClickListener(this);

        return view;
    }



    public float calcularCalidadSuelo(){
        float q,a,b;

        a=Float.parseFloat(rqd.getText().toString())*Float.parseFloat(jr.getText().toString())*
                Float.parseFloat(jw.getText().toString());
        b=Float.parseFloat(jn.getText().toString())*Float.parseFloat(ja.getText().toString())*
                Float.parseFloat(srf.getText().toString());

        q =a/b;

        return q;
    }

    public void limpiarCampos(){
        rqd.setText("");
        jr.setText("");;
        jw.setText("");;
        jn.setText("");;
        ja.setText("");;
        srf.setText("");;

    }

    public String calculaCalidad(Float q1, Float q2){
        String resultado = "";
        Float qMenor;
        if(q1 < q2) qMenor = q1;
        else qMenor= q2;

        if(qMenor <= 0.01){
            resultado= "Este es un macizo de calidad Excepcionalmente Mala";
        }
        else if((qMenor>0.01) && (qMenor<=0.1)){
            resultado= "Este es un macizo de calidad Extremadamente Mala";
        }
        else if((qMenor>0.1) && (qMenor<=1)){
            resultado= "Este es un macizo de calidad Muy Mala";
        }
        else if((qMenor>1) && (qMenor<=4)){
            resultado= "Este es un macizo de calidad Mala";
        }
        else if((qMenor>4) && (qMenor<=10)){
            resultado= "Este es un macizo de calidad Regular o Media";
        }
        else if((qMenor>10) && (qMenor<=40)){
            resultado= "Este es un macizo de calidad Buena";
        }
        else if((qMenor>40) && (qMenor<=100)){
            resultado= "Este es un macizo de calidad Muy Buena";
        }
        else if((qMenor>100) && (qMenor<=400)){
            resultado= "Este es un macizo de calidad Extremadamente Buena";
        }
        else if((qMenor>400)){
            resultado= "Este es un macizo de calidad Excepcionalmente Buena";
        }
        return resultado;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_calidadSuelo:

                if(q1 != -9999 && q2 != -9999){
                    String resultadoFinal;
                    resultadoFinal = calculaCalidad(q1, q2);
                    //resultadoCalidad.setText(resultadoFinal);
                    AlertDialog.Builder builder = new AlertDialog.Builder(calcular.getContext());
                    builder.setMessage(resultadoFinal)
                            .setTitle("")
                            .setCancelable(false)
                            .setPositiveButton("Aceptar",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    })
                            .setNegativeButton("Nueva medición",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            q1 = -9999;
                                            q2 = -9999;
                                            rbq1.setChecked(true);
                                            resultadoQ1.setText("");
                                            resultadoQ2.setText("");
                                            limpiarCampos();
                                            dialog.cancel();
                                        }
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();

                }else {
                    if (rbq1.isChecked() == true) {
                        try {
                            q1 = calcularCalidadSuelo();
                            resultadoQ1.setText("Q1: " + df.format((double) q1));
                            limpiarCampos();


                        } catch (Exception e) {
                            Toast.makeText(getActivity(), "Verifica los datos ingresados en Q1", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        try {
                            q2 = calcularCalidadSuelo();
                            resultadoQ2.setText("Q2: " + df.format((double) q2));
                            limpiarCampos();
                        } catch (Exception e) {
                            Toast.makeText(getActivity(), "Verifica los datos ingresados en Q2", Toast.LENGTH_SHORT).show();
                        }
                    }
                    if (q1 != -9999 && q2 != -9999) {
                        try {

                            String resultadoFinal;
                            resultadoFinal = calculaCalidad(q1, q2);
                            //resultadoCalidad.setText(resultadoFinal);
                            AlertDialog.Builder builder = new AlertDialog.Builder(calcular.getContext());
                            builder.setMessage(resultadoFinal)
                                    .setTitle("")
                                    .setCancelable(false)
                                    .setPositiveButton("Aceptar",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    dialog.cancel();
                                                }
                                            })
                                    .setNegativeButton("Nueva medición",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    q1 = -9999;
                                                    q2 = -9999;
                                                    rbq1.setChecked(true);
                                                    resultadoQ1.setText("");
                                                    resultadoQ2.setText("");
                                                    limpiarCampos();
                                                    dialog.cancel();
                                                }
                                            });
                            AlertDialog alert = builder.create();
                            alert.show();
                        } catch (Exception e) {
                        }
                    }
                }
                 break;
            case R.id.buttonLimpiar:
                limpiarCampos();
                resultadoQ1.setText("");
                resultadoQ2.setText("");
                q1=-9999;
                q2=-9999;
                break;
                 default:break;



        }
    }
}
