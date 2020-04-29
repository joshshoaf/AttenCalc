package com.example.attencalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //splice counter
        final ImageButton splicePlus = findViewById(R.id.splicePlus);
        final ImageButton spliceMinus = findViewById(R.id.spliceMinus);
        final TextView spliceView = findViewById(R.id.spliceView);

        //passive-mux counter
        final ImageButton muxPlus = findViewById(R.id.muxPlus);
        final ImageButton muxMinus = findViewById(R.id.muxMinus);
        final TextView muxView = findViewById(R.id.muxView);

        //connector counter
        final ImageButton connectorPlus = findViewById(R.id.connectorPlus);
        final ImageButton connectorMinus = findViewById(R.id.connectorMinus);
        final TextView connectorView = findViewById(R.id.connectorView);

        //calculate, clear, options buttons
        final Button clear = findViewById(R.id.clear);
        final Button calculate = findViewById(R.id.calculate);
        final Button options = findViewById(R.id.options);
        final Intent navigate = new Intent(this, SettingsActivity.class);


        //1310nm output
        final TextView linkLoss1310 = findViewById(R.id.linkLoss1);
        final TextView spliceLoss1310 = findViewById(R.id.spliceLoss1);
        final TextView passiveLoss1310 = findViewById(R.id.muxLoss1);
        final TextView connectorLoss1310 = findViewById(R.id.connectorLoss1);
        final TextView finalLoss1310 = findViewById(R.id.totalLoss1);
        final TextView finalLevel1310 = findViewById(R.id.finalLevel1);

        //1550nm output
        final TextView linkLoss1550 = findViewById(R.id.linkLoss2);
        final TextView spliceLoss1550 = findViewById(R.id.spliceLoss2);
        final TextView passiveLoss1550 = findViewById(R.id.muxLoss2);
        final TextView connectorLoss1550 = findViewById(R.id.connectorLoss2);
        final TextView finalLoss1550 = findViewById(R.id.totalLoss2);
        final TextView finalLevel1550 = findViewById(R.id.finalLevel2);

        //input
        final EditText inputDistance = findViewById(R.id.linkDistance);
        final EditText inputLevel = findViewById(R.id.startLevel);


        final View.OnClickListener buttonListener =new View.OnClickListener() {

            int spliceCount=0;
            int muxCount=0;
            int connectorCount=0;

            float linkValue1310=0.00f;
            float spliceValue1310=0.00f;
            float passiveValue1310=0.00f;
            float connectorValue1310=0.00f;
            float totalLossValue1310=0.00f;
            float totalLevelValue1310=0.00f;

            float linkValue1550=0.00f;
            float spliceValue1550=0.00f;
            float passiveValue1550=0.00f;
            float connectorValue1550=0.00f;
            float totalLossValue1550=0.00f;
            float totalLevelValue1550=0.00f;

            float startValue=0.00f;
            float distanceValue=0.00f;

            //default values
            SharedPreferences sharedPref = getSharedPreferences("appPref",MODE_PRIVATE);
            float passiveAtten1310 = sharedPref.getFloat("passiveAtten1310", 2.9f);
            float connectorAtten1310 = sharedPref.getFloat("connectorAtten1310", 0.75f);
            float spliceAtten1310 = sharedPref.getFloat("spliceAtten1310",0.1f);
            float distAtten1310 = sharedPref.getFloat("distAtten1310",0.35f);

            float passiveAtten1550 = sharedPref.getFloat("passiveAtten1550",2.7f);
            float connectorAtten1550 = sharedPref.getFloat("connectorAtten1550",0.75f);
            float spliceAtten1550 = sharedPref.getFloat("spliceAtten1550",0.1f);
            float distAtten1550 = sharedPref.getFloat("distAtten1550",0.22f);

            @Override
            public void onClick(View v) {
                final int id = v.getId();
                switch (id){
                    //splice counter
                    case R.id.splicePlus:
                        spliceCount++;
                        spliceView.setText(String.valueOf(spliceCount));
                        break;
                    case R.id.spliceMinus:
                        spliceCount--;
                        if(spliceCount<0) spliceCount=0;
                        spliceView.setText(String.valueOf(spliceCount));
                        break;

                    //passive-mux counter
                    case R.id.muxPlus:
                        muxCount++;
                        muxView.setText(String.valueOf(muxCount));
                        break;
                    case R.id.muxMinus:
                        muxCount--;
                        if(muxCount<0) muxCount=0;
                        muxView.setText(String.valueOf(muxCount));
                        break;

                    //connector counter
                    case R.id.connectorPlus:
                        connectorCount++;
                        connectorView.setText(String.valueOf(connectorCount));
                        break;
                    case R.id.connectorMinus:
                        connectorCount--;
                        if(connectorCount<0) connectorCount=0;
                        connectorView.setText(String.valueOf(connectorCount));
                        break;

                    //calculate button
                    case R.id.calculate:
                        if(inputLevel.getText().length()<=0){
                            startValue=0;
                        }else {
                            startValue = Float.parseFloat(inputLevel.getText().toString());
                        }

                        if(inputDistance.getText().length()<=0) {
                            distanceValue=0;
                        }else {
                            distanceValue = Float.parseFloat(inputDistance.getText().toString());
                        }

                        //calculate 1310 output
                        linkValue1310 = round(distanceValue*distAtten1310,2);
                        spliceValue1310 = round(spliceCount*spliceAtten1310,2);
                        passiveValue1310 = round(muxCount*passiveAtten1310,2);
                        connectorValue1310 = round(connectorCount*connectorAtten1310,2);

                        totalLossValue1310 = round(linkValue1310+spliceValue1310+passiveValue1310
                                +connectorValue1310,2);
                        totalLevelValue1310 = round(startValue-totalLossValue1310,2);

                        //prints 1310 output
                        linkLoss1310.setText(String.valueOf(linkValue1310));
                        spliceLoss1310.setText(String.valueOf(spliceValue1310));
                        passiveLoss1310.setText(String.valueOf(passiveValue1310));
                        connectorLoss1310.setText(String.valueOf(connectorValue1310));

                        finalLoss1310.setText(String.valueOf(totalLossValue1310));
                        finalLevel1310.setText(String.valueOf(totalLevelValue1310));


                        //calculate 1550 output
                        linkValue1550=round(distanceValue*distAtten1550,2);
                        spliceValue1550=round(spliceCount*spliceAtten1550,2);
                        passiveValue1550=round(muxCount*passiveAtten1550,2);
                        connectorValue1550=round(connectorCount*connectorAtten1550,2);

                        totalLossValue1550=round(linkValue1550+spliceValue1550+passiveValue1550
                                +connectorValue1550,2);
                        totalLevelValue1550=round(startValue-totalLossValue1550,2);


                        //prints 1550 output
                        linkLoss1550.setText(String.valueOf(linkValue1550));
                        spliceLoss1550.setText(String.valueOf(spliceValue1550));
                        passiveLoss1550.setText(String.valueOf(passiveValue1550));
                        connectorLoss1550.setText(String.valueOf(connectorValue1550));

                        finalLoss1550.setText(String.valueOf(totalLossValue1550));
                        finalLevel1550.setText(String.valueOf(totalLevelValue1550));
                        break;

                    //clear button
                    case R.id.clear:
                        spliceCount=0;
                        muxCount=0;
                        connectorCount=0;
                        linkValue1310=0;
                        spliceValue1310=0;
                        passiveValue1310=0;
                        connectorValue1310=0;
                        totalLossValue1310=0;
                        totalLevelValue1310=0;
                        linkValue1550=0;
                        spliceValue1550=0;
                        passiveValue1550=0;
                        connectorValue1550=0;
                        totalLossValue1550=0;
                        totalLevelValue1550=0;
                        distanceValue=0;
                        startValue=0;

                        spliceView.setText("0");
                        muxView.setText("0");
                        connectorView.setText("0");
                        linkLoss1310.setText("0");
                        spliceLoss1310.setText("0");
                        passiveLoss1310.setText("0");
                        connectorLoss1310.setText("0");
                        finalLoss1310.setText("0");
                        finalLevel1310.setText("0");
                        linkLoss1550.setText("0");
                        spliceLoss1550.setText("0");
                        passiveLoss1550.setText("0");
                        connectorLoss1550.setText("0");
                        finalLoss1550.setText("0");
                        finalLevel1550.setText("0");
                        inputDistance.setText("");
                        inputLevel.setText("");
                        break;

                    //Options button
                    case R.id.options:
                        startActivity(navigate);
                        break;
                }


            }


        };


        splicePlus.setOnClickListener(buttonListener);
        spliceMinus.setOnClickListener(buttonListener);
        muxPlus.setOnClickListener(buttonListener);
        muxMinus.setOnClickListener(buttonListener);
        connectorPlus.setOnClickListener(buttonListener);
        connectorMinus.setOnClickListener(buttonListener);
        clear.setOnClickListener(buttonListener);
        calculate.setOnClickListener(buttonListener);
        options.setOnClickListener(buttonListener);

    }

    //rounds numbers
 public static float round(float d, int decimalPlace) {
        BigDecimal number = new BigDecimal(Float.toString(d));
        number = number.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return number.floatValue();
    }
}
