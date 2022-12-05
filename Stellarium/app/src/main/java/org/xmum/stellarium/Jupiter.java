package org.xmum.stellarium;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Jupiter extends AppCompatActivity {

    ImageButton btnJupiter;
    CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jupiter);

        btnJupiter = findViewById(R.id.btnJupiter);
        cardView = findViewById(R.id.cardView);

        btnJupiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Jupiter.this,Education_SolarSystem.class);
                startActivity(intent);
            }
        });
    }
}