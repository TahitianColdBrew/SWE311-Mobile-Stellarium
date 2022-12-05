package org.xmum.stellarium;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import org.xmum.stellarium.adapter.QuestionAdapter;
import org.xmum.stellarium.utils.DbQuery;

import java.util.concurrent.TimeUnit;

public class QuestionActivity extends AppCompatActivity {
    private RecyclerView questionView;
    private ImageView btnLast, btnNext, btnBookmark;
    private TextView tvTimer, tvQuestionID;
    private AppCompatButton btnSubmit;
    private int questionID;
    private long timeLeft;
    private CountDownTimer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        init();

        QuestionAdapter adapter = new QuestionAdapter(DbQuery.g_questionList);
        questionView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        questionView.setLayoutManager(layoutManager);
    }

    private void init() {
        questionView = findViewById(R.id.questions_view);
        btnLast = findViewById(R.id.btnLast);
        btnNext = findViewById(R.id.btnNext);
        btnBookmark = findViewById(R.id.btnBookmark);
        tvTimer = findViewById(R.id.tvTimer);
        tvQuestionID = findViewById(R.id.tvQuestionID);
        btnSubmit = findViewById(R.id.btn_submit);

        questionID = 0;
        tvQuestionID.setText("QUESTION 1 / " + DbQuery.g_questionList.size());

        setSnapHelper();

        setClickListeners();

        startTimer();
    }

    private void setSnapHelper() {
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(questionView);

        questionView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                View view = snapHelper.findSnapView(recyclerView.getLayoutManager());
                questionID = recyclerView.getLayoutManager().getPosition(view);
                tvQuestionID.setText("QUESTION " + (questionID + 1) + " / " + DbQuery.g_questionList.size());

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private void setClickListeners() {
        btnLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (questionID > 0) {
                    questionView.smoothScrollToPosition(questionID - 1);
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (questionID < DbQuery.g_questionList.size() - 1) {
                    questionView.smoothScrollToPosition(questionID + 1);
                }
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitTest();
            }
        });
    }

    private void submitTest() {
        Dialog dialog = new Dialog(QuestionActivity.this);
        dialog.setContentView(R.layout.submit_quiz_dialog);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_background);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);

        AppCompatButton cancelB = dialog.findViewById(R.id.btn_cancel_dialog);
        AppCompatButton confirmB = dialog.findViewById(R.id.btn_submit_dialog);


        cancelB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        confirmB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.cancel();
                dialog.dismiss();

                Intent intent = new Intent(QuestionActivity.this, ScoreActivity.class);
                long totalTime = DbQuery.g_catList.get(DbQuery.g_selectedCatIndex).getTime() * 60 * 1000;
                intent.putExtra("TIME_TAKEN", totalTime - timeLeft);
                startActivity(intent);
                QuestionActivity.this.finish();
            }
        });

        dialog.show();
    }

    private void startTimer() {
        long totalTime = DbQuery.g_catList.get(DbQuery.g_selectedCatIndex).getTime() * 60 * 1000;

        timer = new CountDownTimer(totalTime, 1000) {
            @Override
            public void onTick(long remainingTime) {
                timeLeft = remainingTime;
                String time = String.format(
                        "%02d : %02d",
                        TimeUnit.MILLISECONDS.toMinutes(remainingTime),
                        TimeUnit.MILLISECONDS.toSeconds(remainingTime) - TimeUnit.MILLISECONDS.toMinutes(remainingTime) * 60
                );

                tvTimer.setText(time);
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(QuestionActivity.this, ScoreActivity.class);
                intent.putExtra("TIME_TAKEN", DbQuery.g_catList.get(DbQuery.g_selectedCatIndex).getTime() * 60 * 1000);
                startActivity(intent);
                QuestionActivity.this.finish();
            }
        };

        timer.start();

    }
}