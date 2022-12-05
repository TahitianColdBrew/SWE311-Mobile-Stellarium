package org.xmum.stellarium;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Neptune extends AppCompatActivity {

    ImageButton btnNeptune;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neptune);

        btnNeptune = findViewById(R.id.btnNeptune);
        btnNeptune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Neptune.this,Education_SolarSystem.class);
                startActivity(intent);
            }
        });
    }
}