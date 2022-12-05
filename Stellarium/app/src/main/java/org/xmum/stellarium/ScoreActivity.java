package org.xmum.stellarium;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.xmum.stellarium.utils.DbQuery;

import java.util.concurrent.TimeUnit;

public class ScoreActivity extends AppCompatActivity {
    private TextView tvScore, tvTimeConsumed, tvCorrectNo, tvWrongNo, tvNotAttemptedNo;
    private AppCompatButton btnLeaderboard, btnTryAgain, btnViewAnswer;
    private long timeTaken;
    private Toolbar toolbar;
    private Dialog progressDialog;
    private TextView dialogText;
    private int finalScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Result");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new Dialog(ScoreActivity.this);
        progressDialog.setContentView(R.layout.dialog_layout);
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_background);
        progressDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        dialogText = progressDialog.findViewById(R.id.dialog_text);
        dialogText.setText("Saving score...");

        progressDialog.show();

        init();

        loadData();

        btnViewAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tryAgain();
            }
        });

        saveResult();
    }

    private void saveResult() {
        DbQuery.saveResult(finalScore, new MyCompleteListener() {
            @Override
            public void onSuccess() {
                progressDialog.dismiss();
            }

            @Override
            public void onFailure() {
                Toast.makeText(ScoreActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    private void tryAgain() {
        for (int i = 0; i < DbQuery.g_questionList.size(); i++) {
            DbQuery.g_questionList.get(i).setSelectedAns(-1);
        }

        Intent intent = new Intent(ScoreActivity.this, StartTestActivity.class);
        startActivity(intent);
        finish();
    }

    private void init() {
        tvScore = findViewById(R.id.tvScore);
        tvTimeConsumed = findViewById(R.id.tvTimeConsumed);
        tvCorrectNo = findViewById(R.id.tvCorrectNo);
        tvWrongNo = findViewById(R.id.tvWrongNo);
        tvNotAttemptedNo = findViewById(R.id.tvNotAttempedNo);
        btnLeaderboard = findViewById(R.id.btnLeaderboard);
        btnTryAgain = findViewById(R.id.btnTryAgain);
        btnViewAnswer = findViewById(R.id.btnViewAnswer);
    }

    private void loadData() {
        int correctQ = 0, wrongQ = 0, unattempted = 0;

        for (int i = 0; i < DbQuery.g_questionList.size(); i++) {
            if (DbQuery.g_questionList.get(i).getSelectedAns() == -1) {
                unattempted++;
            } else if (DbQuery.g_questionList.get(i).getSelectedAns() == DbQuery.g_questionList.get(i).getCorrectAns()) {
                correctQ++;
            } else {
                wrongQ++;
            }
        }

        tvCorrectNo.setText(String.valueOf(correctQ));
        tvWrongNo.setText(String.valueOf(wrongQ));
        tvNotAttemptedNo.setText(String.valueOf(unattempted));

        finalScore = (correctQ * 100) / (DbQuery.g_questionList.size());
        tvScore.setText(String.valueOf(finalScore));

        timeTaken = getIntent().getLongExtra("TIME_TAKEN", 0);
        String time = String.format(
                "%02d : %02d",
                TimeUnit.MILLISECONDS.toMinutes(timeTaken),
                TimeUnit.MILLISECONDS.toSeconds(timeTaken) - TimeUnit.MILLISECONDS.toMinutes(timeTaken) * 60
        );
        tvTimeConsumed.setText(time);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            ScoreActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}