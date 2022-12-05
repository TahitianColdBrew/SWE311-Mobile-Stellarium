package org.xmum.stellarium;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Venus extends AppCompatActivity {

    ImageButton btnVenus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venus);

        btnVenus=findViewById(R.id.btnVenus);
        btnVenus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Venus.this,Education_SolarSystem.class);
                startActivity(intent);
            }
        });

    }
}