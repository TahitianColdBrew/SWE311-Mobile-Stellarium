package org.xmum.stellarium;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.xmum.stellarium.adapter.QuestionAdapter;
import org.xmum.stellarium.utils.DbQuery;

public class QuestionActivity extends AppCompatActivity {
    private RecyclerView questionView;

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
    }
}