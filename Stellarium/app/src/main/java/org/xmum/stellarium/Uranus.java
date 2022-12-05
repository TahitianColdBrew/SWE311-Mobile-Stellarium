package org.xmum.stellarium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Uranus extends AppCompatActivity {

    ImageButton btnUranus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uranus);

        btnUranus = findViewById(R.id.btnUranus);

        btnUranus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Uranus.this,Education_SolarSystem.class);
                startActivity(intent);
            }
        });
    }
}