package org.xmum.stellarium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class IntroductionActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button btnStart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        mAuth = FirebaseAuth.getInstance();

        btnStart = findViewById(R.id.btn_start);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAuth.getCurrentUser() != null){
                    Intent intent = new Intent(IntroductionActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(IntroductionActivity.this, LoginActivity.class);
                    startActivity(intent);
                }

                // so that when clicking on the "back", the application will quit
                // instead of going back to the splash page
                IntroductionActivity.this.finish();
            }
        });
    }
}