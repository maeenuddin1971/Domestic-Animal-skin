package com.example.domesticanimalskin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class All_Activity extends AppCompatActivity {
Button b1,b2,b3,b4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_);

        b1=findViewById(R.id.cow);
        b2=findViewById(R.id.duck);
        b3=findViewById(R.id.hen);
        b4=findViewById(R.id.goat);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(All_Activity.this,MainActivity.class);
                intent.putExtra("animal","cow");
                startActivity(intent);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(All_Activity.this,MainActivity.class);
                intent.putExtra("animal","duck");
                startActivity(intent);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(All_Activity.this,MainActivity.class);
                intent.putExtra("animal","hen");
                startActivity(intent);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(All_Activity.this,MainActivity.class);
                intent.putExtra("animal","goat");
                startActivity(intent);
            }
        });

    }
}