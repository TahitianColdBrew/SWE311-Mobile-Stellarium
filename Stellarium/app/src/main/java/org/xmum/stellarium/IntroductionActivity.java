package org.xmum.stellarium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import org.xmum.stellarium.utils.DbQuery;

public class IntroductionActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button btnStart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        mAuth = FirebaseAuth.getInstance();
        DbQuery.g_firestore = FirebaseFirestore.getInstance();

        btnStart = findViewById(R.id.btn_start);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAuth.getCurrentUser() != null){
                    DbQuery.loadCategories(new MyCompleteListener() {
                        @Override
                        public void onSuccess() {
                            Intent intent = new Intent(IntroductionActivity.this, MainActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure() {

                        }
                    });
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