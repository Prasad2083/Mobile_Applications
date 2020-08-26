package com.example.conversion_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Display_Page extends AppCompatActivity {
    String dataxml,datajson;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display__page);
        textView=findViewById(R.id.data);
        datajson=getIntent().getStringExtra("jsondata");
        dataxml=getIntent().getStringExtra("xmldata");
        textView.setText(datajson);
        textView.setText(dataxml);


    }
}
