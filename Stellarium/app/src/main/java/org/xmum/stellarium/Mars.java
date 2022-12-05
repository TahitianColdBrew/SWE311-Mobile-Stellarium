package org.xmum.stellarium;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Mars extends AppCompatActivity {

    ImageButton btnMars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mars);

        btnMars = findViewById(R.id.btnMars);

        btnMars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Mars.this,Education_SolarSystem.class);
                startActivity(intent);
            }
        });
    }
}