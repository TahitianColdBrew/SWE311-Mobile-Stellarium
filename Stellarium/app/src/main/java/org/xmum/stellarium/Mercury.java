package org.xmum.stellarium;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Mercury extends AppCompatActivity {

    ImageButton btnMercury;
    CardView cardView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mercury);

        btnMercury = findViewById(R.id.btnMercury);
        cardView = findViewById(R.id.cardView);

        btnMercury.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Mercury.this,Education_SolarSystem.class);
                startActivity(intent);
            }
        });
    }
}