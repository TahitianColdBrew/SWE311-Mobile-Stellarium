package org.xmum.stellarium;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Earth extends AppCompatActivity {

    ImageButton btnEarth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earth);

        btnEarth = findViewById(R.id.btnEarth);


        btnEarth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Earth.this,Education_SolarSystem.class);
                startActivity(intent);
            }
        });
    }
}