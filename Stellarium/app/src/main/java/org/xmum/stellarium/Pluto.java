package org.xmum.stellarium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Pluto extends AppCompatActivity {

    ImageButton btnPluto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pluto);

        btnPluto = findViewById(R.id.btnPluto);
        btnPluto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Pluto.this, Education_SolarSystem.class);
                startActivity(intent);
            }
        });
    }
}