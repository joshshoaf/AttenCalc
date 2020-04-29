package com.example.attencalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.InputStream;

public class SettingsActivity extends AppCompatActivity {

  //Default Values
    final static float defPassiveAtten1310=2.9f;
    final static float defConnectorAtten1310=0.75f;
    final static float defSpliceAtten1310=0.1f;
    final static float defDistAtten1310=0.35f;
    final static float defPassiveAtten1550=2.7f;
    final static float defConnectorAtten1550=0.75f;
    final static float defSpliceAtten1550=0.1f;
    final static float defDistAtten1550=0.22f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_settings);

        final TextView spliceLoss1310Input = findViewById(R.id.spliceLoss1310Input);
        final TextView spliceLoss1550Input = findViewById(R.id.spliceLoss1550Input);
        final TextView passiveLoss1310Input = findViewById(R.id.passiveLoss1310Input);
        final TextView passiveLoss1550Input = findViewById(R.id.passiveLoss1550Input);
        final TextView connectorLoss1310Input = findViewById(R.id.connectorLoss1310Input);
        final TextView connectorLoss1550Input = findViewById(R.id.connectorLoss1550Input);
        final TextView distanceLoss1310Input = findViewById(R.id.distanceLoss1310Input);
        final TextView distanceLoss1550Input = findViewById(R.id.distanceLoss1550Input);
        final Button save = findViewById(R.id.saveButton);
        final Button restore = findViewById(R.id.restoreButton);
        final TextView website = findViewById(R.id.websiteLink);
        final TextView readme = findViewById(R.id.readmeLink);
        final Intent navigateReadme = new Intent(this, ReadmeViewActivity.class);
        final Intent navigateMain = new Intent(this,MainActivity.class);

        SharedPreferences sharedPref = getSharedPreferences("appPref",MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();

        //Retrieves values from Shared Preferences
        spliceLoss1310Input.setText(String.valueOf(sharedPref.getFloat("spliceAtten1310",defSpliceAtten1310)));
        passiveLoss1310Input.setText(String.valueOf(sharedPref.getFloat("passiveAtten1310",defPassiveAtten1310)));
        connectorLoss1310Input.setText(String.valueOf(sharedPref.getFloat("connectorAtten1310",defConnectorAtten1310)));
        distanceLoss1310Input.setText(String.valueOf(sharedPref.getFloat("distAtten1310",defDistAtten1310)));

        spliceLoss1550Input.setText(String.valueOf(sharedPref.getFloat("spliceAtten1550",defSpliceAtten1550)));
        passiveLoss1550Input.setText(String.valueOf(sharedPref.getFloat("passiveAtten1550",defPassiveAtten1550)));
        connectorLoss1550Input.setText(String.valueOf(sharedPref.getFloat("connectorAtten1550",defConnectorAtten1550)));
        distanceLoss1550Input.setText(String.valueOf(sharedPref.getFloat("distAtten1550",defDistAtten1550)));


        final View.OnClickListener buttonListener =new View.OnClickListener() {

            public void onClick(View v) {
                final int id = v.getId();
                switch (id) {
                    //save Button
                    case R.id.saveButton:

                     //Stores new values to Shared Preferences
                       editor.putFloat("spliceAtten1310",Float.parseFloat(spliceLoss1310Input.getText().toString()));
                       editor.putFloat("passiveAtten1310",Float.parseFloat(passiveLoss1310Input.getText().toString()));
                       editor.putFloat("connectorAtten1310",Float.parseFloat(connectorLoss1310Input.getText().toString()));
                       editor.putFloat("distAtten1310",Float.parseFloat(distanceLoss1310Input.getText().toString()));

                       editor.putFloat("spliceAtten1550",Float.parseFloat(spliceLoss1550Input.getText().toString()));
                       editor.putFloat("passiveAtten1550",Float.parseFloat(passiveLoss1550Input.getText().toString()));
                       editor.putFloat("connectorAtten1550",Float.parseFloat(connectorLoss1550Input.getText().toString()));
                       editor.putFloat("distAtten1550",Float.parseFloat(distanceLoss1550Input.getText().toString()));

                       editor.apply();
                       startActivity(navigateMain);
                        break;

                    //Restore Button
                    case R.id.restoreButton:

                     //Resets default values
                        spliceLoss1310Input.setText(String.valueOf(defSpliceAtten1310));
                        passiveLoss1310Input.setText(String.valueOf(defPassiveAtten1310));
                        connectorLoss1310Input.setText(String.valueOf(defConnectorAtten1310));
                        distanceLoss1310Input.setText(String.valueOf(defDistAtten1310));

                        spliceLoss1550Input.setText(String.valueOf(defSpliceAtten1550));
                        passiveLoss1550Input.setText(String.valueOf(defPassiveAtten1550));
                        connectorLoss1550Input.setText(String.valueOf(defConnectorAtten1550));
                        distanceLoss1550Input.setText(String.valueOf(defDistAtten1550));
                        break;

                        //Will eventually link to company website
                     case R.id.websiteLink:
                        Toast linkError = Toast.makeText(getApplicationContext(),
                                "We haven't launched our website check back soon for updates.",
                                Toast.LENGTH_SHORT);
                        linkError.setGravity(Gravity.CENTER_VERTICAL,0,0);
                        linkError.show();
                        break;

                    //Starts ReadmeView Activity
                    case R.id.readmeLink:
                      startActivity(navigateReadme);
                        break;

                }

            }
        };
        save.setOnClickListener(buttonListener);
        restore.setOnClickListener(buttonListener);
        website.setOnClickListener(buttonListener);
        readme.setOnClickListener(buttonListener);





    }

}
