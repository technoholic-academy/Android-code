package com.example.iot_chatbot;



import androidx.annotation.NonNull;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef;
    public TextView textView;
    public  String text = "hello";
    public String etext;
    public String value;
    public String rvalue;
    public String sensor_val;
    public String value1;
    public String value3;
    public String sensor2;
    public String sensor3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView =  findViewById(R.id.textview);
        EditText editText = findViewById(R.id.editText);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etext = editText.getText().toString();
                //Toast. makeText(getApplicationContext(),etext+"pavan",Toast. LENGTH_SHORT).show();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    func();
                }

            }
        });


// Write a message to the database

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("def_strings");




        //myRef.setValue("12");

        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //value = Objects.requireNonNull(snapshot.getValue()).toString() ;
                sensor_val = snapshot.child("sensors").child("sensor1").getValue().toString();
                sensor2 = snapshot.child("sensors").child("sensor2").getValue().toString();
                sensor3 = snapshot.child("sensors").child("sensor3").getValue().toString();
                value = snapshot.child("strings").child("s1").getValue().toString();
                value1 = snapshot.child("strings").child("s2").getValue().toString();
                value3 = snapshot.child("strings").child("s3").getValue().toString();




                if (value  !=null){
                    rvalue =value;
                }
                //textView.setText(value);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast. makeText(getApplicationContext(),"kuch error he",Toast. LENGTH_SHORT).show();

            }
        });
    }

    public void func(){

        text = "value of moisture";

        if (etext.equals(rvalue)){
            //textView.setText(value);
            textView.setText("Moisture is :" +sensor_val);

            Toast. makeText(getApplicationContext(),"ok",Toast. LENGTH_SHORT).show();
        }

        if (etext.equals(value1)){
            //textView.setText(value);
            textView.setText("Temperature is :" +sensor2);

            Toast. makeText(getApplicationContext(),"ok ",Toast. LENGTH_SHORT).show();
        }

        if (etext.equals(value3)){
            //textView.setText(value);
            textView.setText("Distance is :" +sensor3);

            Toast. makeText(getApplicationContext(),"ok",Toast. LENGTH_SHORT).show();
        }

        else{
            Toast. makeText(getApplicationContext(),"didn't match",Toast. LENGTH_SHORT).show();
        }

    }
}

