package com.example.attencalc;

import android.content.res.AssetManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadmeViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_readme_view);

        final TextView textView = findViewById(R.id.FullPage);

        textView.setText(TextReader("Readme.txt"));

    }


    //Reads .txt file to String
    public String TextReader(String inFile) {
        String textContent = "";

        try {
            InputStream stream = getAssets().open(inFile);

            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            textContent = new String(buffer);
        }
        catch (IOException e)
        {
            //Displays error toast
            Toast readmeError = Toast.makeText(getApplicationContext(),
                    "Readme.txt not found",
                    Toast.LENGTH_SHORT);
            readmeError.setGravity(Gravity.CENTER_VERTICAL,0,0);
            readmeError.show();

        }

        return textContent;

    }
        }



