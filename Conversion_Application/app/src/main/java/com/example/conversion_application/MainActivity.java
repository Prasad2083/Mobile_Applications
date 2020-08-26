package com.example.conversion_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    Spinner spinner;
    private String SelectedData;
    int Dataposition;
    String xmltojsonstring;
    String XMLstring;
    String jsondata;
    String xml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=findViewById(R.id.conversion);
        button=findViewById(R.id.btnconvert);
        spinner=findViewById(R.id.selectspinner);

        List<String> Selecttoconvert= new ArrayList<>();

        Selecttoconvert.add(0,"XML TO JSON");

        Selecttoconvert.add(1,"JSON TO XML");

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,Selecttoconvert);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
         SelectedData=parent.getItemAtPosition(position).toString();
         Dataposition=position;
        Toast.makeText(MainActivity.this,"Selected "+SelectedData,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
});

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Dataposition==0){
                    DataConverttoJson();
                    Intent intent=new Intent(getApplicationContext(),Display_Page.class);
                    intent.putExtra("jsondata",xmltojsonstring);
                    startActivity(intent);
                }
                else if (Dataposition==1){
                    DataConvertertoXML();
                    Intent xmlintent=new Intent(getApplicationContext(),Display_Page.class);
                    xmlintent.putExtra("xmldata",xml);
                    startActivity(xmlintent);
                }


            }
        });
    }
    public String DataConverttoJson(){

            int PRETTY_PRINT_INDENT_FACTOR=4;
           XMLstring=editText.getText().toString();
            try {
                JSONObject jsonObject= XML.toJSONObject(XMLstring);
                xmltojsonstring=jsonObject.toString(PRETTY_PRINT_INDENT_FACTOR);
                Log.i("data","json"+xmltojsonstring);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return xmltojsonstring;
        }
        public String DataConvertertoXML(){
        //String xml=<Root>;
           jsondata=editText.getText().toString();

            System.out.println(jsondata);
            Log.i("vara","json "+jsondata);


            try {
                JSONObject jsonObject=new JSONObject(jsondata);
                xml=XML.toString(jsonObject);
                Log.i("data","xml1"+xml);

            } catch (JSONException e) {
                e.printStackTrace();

                Log.i("Exception","error"+e);

            }

           // xml=xml+"</Root>";
            Log.i("data","json2"+xml);
            return xml;

        }

    }

