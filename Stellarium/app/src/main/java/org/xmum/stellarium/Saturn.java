package org.xmum.stellarium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Saturn extends AppCompatActivity {

    ImageButton btnSaturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saturn);

        btnSaturn = findViewById(R.id.btnSaturn);

        btnSaturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Saturn.this,Education_SolarSystem.class);
                startActivity(intent);
            }
        });
    }
}