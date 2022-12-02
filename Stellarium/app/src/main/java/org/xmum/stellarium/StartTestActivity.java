package org.xmum.stellarium;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.xmum.stellarium.model.CategoryModel;
import org.xmum.stellarium.utils.DbQuery;

public class StartTestActivity extends AppCompatActivity {
    private ImageView catImg;
    private TextView catName, tvQuestionNo, tvBestScore, tvTimeAllocated;
    private AppCompatButton btnStartQuiz;

    private Dialog progressDialog;
    private TextView dialogText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_test);

        init();

        progressDialog = new Dialog(StartTestActivity.this);
        progressDialog.setContentView(R.layout.dialog_layout);
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        dialogText = progressDialog.findViewById(R.id.dialog_text);
        dialogText.setText("loading questions...");

        progressDialog.show();

        DbQuery.loadQuestions(new MyCompleteListener() {
            @Override
            public void onSuccess() {
                System.out.println(DbQuery.g_questionList);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure() {
                progressDialog.dismiss();
                Toast.makeText(StartTestActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init(){
        catImg = findViewById(R.id.catImg);
        catName = findViewById(R.id.catName);
        tvQuestionNo = findViewById(R.id.tvQuestionNo);
        tvBestScore = findViewById(R.id.tvBestScore);
        tvTimeAllocated = findViewById(R.id.tvTimeAllocated);
        btnStartQuiz = findViewById(R.id.btnStartQuiz);

        CategoryModel categoryModel = DbQuery.g_catList.get(DbQuery.g_selectedCatIndex);
        Picasso.get().load(categoryModel.getUrl()).into(catImg);
        catName.setText(categoryModel.getName());
        tvQuestionNo.setText(String.valueOf(categoryModel.getNoOfQuestions()));
        tvTimeAllocated.setText(String.valueOf(categoryModel.getTime()));
        tvBestScore.setText(String.valueOf(categoryModel.getBestScore()));

        btnStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: create pages to answer questions
            }
        });


    }
}