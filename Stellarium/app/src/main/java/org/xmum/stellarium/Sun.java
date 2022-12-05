package org.xmum.stellarium;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Sun extends AppCompatActivity {

    ImageButton btnSun;
    CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sun);

        btnSun = findViewById(R.id.btnSun);
        cardView = findViewById(R.id.cardView);

        btnSun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Sun.this,Education_SolarSystem.class);
                startActivity(intent);
            }
        });
    }
}