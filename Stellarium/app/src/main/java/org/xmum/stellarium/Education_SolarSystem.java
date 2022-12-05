package org.xmum.stellarium;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Education_SolarSystem extends AppCompatActivity {

    ImageButton btnSun,btnEarth,btnBStar,btnMercury,btnVenus,btnMars,btnJupiter,btnSaturn,btnUranus,btnNeptune,btnPluto;
    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_solar_system);

        btnSun = findViewById(R.id.btnSun);
        btnEarth = findViewById(R.id.btnEarth);
        btnBStar = findViewById(R.id.btnBStar);
        btnJupiter =findViewById(R.id.btnJupiter);
        btnMars = findViewById(R.id.btnMars);
        btnMercury = findViewById(R.id.btnMercury);
        btnVenus = findViewById(R.id.btnVenus);
        btnSaturn = findViewById((R.id.btnSaturn));
        btnUranus = findViewById(R.id.btnUranus);
        btnNeptune = findViewById(R.id.btnNeptune);
        btnPluto = findViewById(R.id.btnPluto);
        layout = findViewById(R.id.myLayout);

        btnSun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnJupiter.setVisibility(View.INVISIBLE);
                btnEarth.setVisibility(View.INVISIBLE);
                btnBStar.setVisibility(View.INVISIBLE);
                btnMercury.setVisibility(View.INVISIBLE);
                btnVenus.setVisibility(View.INVISIBLE);
                btnMars.setVisibility(View.INVISIBLE);
                btnJupiter.setVisibility(View.INVISIBLE);
                btnUranus.setVisibility(View.INVISIBLE);
                btnNeptune.setVisibility(View.INVISIBLE);
                btnPluto.setVisibility(View.INVISIBLE);
                btnSaturn.setVisibility(View.INVISIBLE);

                layout.setBackgroundResource(R.color.blue);
                btnSun.setBackgroundResource(R.color.blue);
                Intent intent=new Intent(Education_SolarSystem.this,Sun.class);
                startActivity(intent);
            }
        });

        btnEarth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnJupiter.setVisibility(View.INVISIBLE);
                btnSun.setVisibility(View.INVISIBLE);
                btnBStar.setVisibility(View.INVISIBLE);
                btnMercury.setVisibility(View.INVISIBLE);
                btnVenus.setVisibility(View.INVISIBLE);
                btnMars.setVisibility(View.INVISIBLE);
                btnJupiter.setVisibility(View.INVISIBLE);
                btnUranus.setVisibility(View.INVISIBLE);
                btnNeptune.setVisibility(View.INVISIBLE);
                btnPluto.setVisibility(View.INVISIBLE);
                btnSaturn.setVisibility(View.INVISIBLE);

                layout.setBackgroundResource(R.color.blue);
                btnEarth.setBackgroundResource(R.color.blue);
                Intent intent=new Intent(Education_SolarSystem.this,Earth.class);
                startActivity(intent);
            }
        });

        btnMercury.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                btnJupiter.setVisibility(View.INVISIBLE);
                btnSun.setVisibility(View.INVISIBLE);
                btnBStar.setVisibility(View.INVISIBLE);
                btnEarth.setVisibility(View.INVISIBLE);
                btnVenus.setVisibility(View.INVISIBLE);
                btnMars.setVisibility(View.INVISIBLE);
                btnJupiter.setVisibility(View.INVISIBLE);
                btnUranus.setVisibility(View.INVISIBLE);
                btnNeptune.setVisibility(View.INVISIBLE);
                btnPluto.setVisibility(View.INVISIBLE);
                btnSaturn.setVisibility(View.INVISIBLE);

                layout.setBackgroundResource(R.color.blue);
                btnMercury.setBackgroundResource(R.color.blue);
                Intent intent=new Intent(Education_SolarSystem.this,Mercury.class);
                startActivity(intent);
            }
        });

        btnVenus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                btnJupiter.setVisibility(View.INVISIBLE);
                btnSun.setVisibility(View.INVISIBLE);
                btnBStar.setVisibility(View.INVISIBLE);
                btnEarth.setVisibility(View.INVISIBLE);
                btnMercury.setVisibility(View.INVISIBLE);
                btnMars.setVisibility(View.INVISIBLE);
                btnJupiter.setVisibility(View.INVISIBLE);
                btnUranus.setVisibility(View.INVISIBLE);
                btnNeptune.setVisibility(View.INVISIBLE);
                btnPluto.setVisibility(View.INVISIBLE);
                btnSaturn.setVisibility(View.INVISIBLE);

                layout.setBackgroundResource(R.color.blue);
                btnVenus.setBackgroundResource(R.color.blue);
                Intent intent=new Intent(Education_SolarSystem.this,Venus.class);
                startActivity(intent);
            }
        });

        btnNeptune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                btnJupiter.setVisibility(View.INVISIBLE);
                btnSun.setVisibility(View.INVISIBLE);
                btnBStar.setVisibility(View.INVISIBLE);
                btnEarth.setVisibility(View.INVISIBLE);
                btnVenus.setVisibility(View.INVISIBLE);
                btnMars.setVisibility(View.INVISIBLE);
                btnJupiter.setVisibility(View.INVISIBLE);
                btnUranus.setVisibility(View.INVISIBLE);
                btnPluto.setVisibility(View.INVISIBLE);
                btnSaturn.setVisibility(View.INVISIBLE);

                layout.setBackgroundResource(R.color.blue);
                btnMercury.setBackgroundResource(R.color.blue);
                Intent intent=new Intent(Education_SolarSystem.this,Neptune.class);
                startActivity(intent);
            }
        });

        btnMars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                btnJupiter.setVisibility(View.INVISIBLE);
                btnSun.setVisibility(View.INVISIBLE);
                btnBStar.setVisibility(View.INVISIBLE);
                btnEarth.setVisibility(View.INVISIBLE);
                btnVenus.setVisibility(View.INVISIBLE);
                btnNeptune.setVisibility(View.INVISIBLE);
                btnJupiter.setVisibility(View.INVISIBLE);
                btnUranus.setVisibility(View.INVISIBLE);
                btnPluto.setVisibility(View.INVISIBLE);
                btnSaturn.setVisibility(View.INVISIBLE);
                layout.setBackgroundResource(R.color.blue);
                btnMercury.setBackgroundResource(R.color.blue);
                Intent intent=new Intent(Education_SolarSystem.this,Mars.class);
                startActivity(intent);
            }
        });

        btnJupiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                btnNeptune.setVisibility(View.INVISIBLE);
                btnSun.setVisibility(View.INVISIBLE);
                btnBStar.setVisibility(View.INVISIBLE);
                btnEarth.setVisibility(View.INVISIBLE);
                btnVenus.setVisibility(View.INVISIBLE);
                btnMars.setVisibility(View.INVISIBLE);
                btnUranus.setVisibility(View.INVISIBLE);
                btnPluto.setVisibility(View.INVISIBLE);
                btnSaturn.setVisibility(View.INVISIBLE);
                layout.setBackgroundResource(R.color.blue);
                btnMercury.setBackgroundResource(R.color.blue);
                Intent intent=new Intent(Education_SolarSystem.this,Jupiter.class);
                startActivity(intent);
            }
        });

        btnSaturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                btnJupiter.setVisibility(View.INVISIBLE);
                btnSun.setVisibility(View.INVISIBLE);
                btnBStar.setVisibility(View.INVISIBLE);
                btnEarth.setVisibility(View.INVISIBLE);
                btnVenus.setVisibility(View.INVISIBLE);
                btnMars.setVisibility(View.INVISIBLE);
                btnJupiter.setVisibility(View.INVISIBLE);
                btnUranus.setVisibility(View.INVISIBLE);
                btnPluto.setVisibility(View.INVISIBLE);
                btnNeptune.setVisibility(View.INVISIBLE);

                layout.setBackgroundResource(R.color.blue);
                btnMercury.setBackgroundResource(R.color.blue);
                Intent intent=new Intent(Education_SolarSystem.this,Saturn.class);
                startActivity(intent);
            }
        });

        btnUranus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                btnJupiter.setVisibility(View.INVISIBLE);
                btnSun.setVisibility(View.INVISIBLE);
                btnBStar.setVisibility(View.INVISIBLE);
                btnEarth.setVisibility(View.INVISIBLE);
                btnVenus.setVisibility(View.INVISIBLE);
                btnMars.setVisibility(View.INVISIBLE);
                btnJupiter.setVisibility(View.INVISIBLE);
                btnNeptune.setVisibility(View.INVISIBLE);
                btnPluto.setVisibility(View.INVISIBLE);
                btnSaturn.setVisibility(View.INVISIBLE);

                layout.setBackgroundResource(R.color.blue);
                btnMercury.setBackgroundResource(R.color.blue);
                Intent intent=new Intent(Education_SolarSystem.this,Uranus.class);
                startActivity(intent);
            }
        });

        btnPluto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                btnJupiter.setVisibility(View.INVISIBLE);
                btnSun.setVisibility(View.INVISIBLE);
                btnBStar.setVisibility(View.INVISIBLE);
                btnEarth.setVisibility(View.INVISIBLE);
                btnVenus.setVisibility(View.INVISIBLE);
                btnMars.setVisibility(View.INVISIBLE);
                btnJupiter.setVisibility(View.INVISIBLE);
                btnUranus.setVisibility(View.INVISIBLE);
                btnNeptune.setVisibility(View.INVISIBLE);
                btnSaturn.setVisibility(View.INVISIBLE);

                layout.setBackgroundResource(R.color.blue);
                btnMercury.setBackgroundResource(R.color.blue);
                Intent intent=new Intent(Education_SolarSystem.this,Pluto.class);
                startActivity(intent);
            }
        });
    }
}