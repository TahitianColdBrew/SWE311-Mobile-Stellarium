package org.xmum.stellarium.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;
import org.xmum.stellarium.R;
import org.xmum.stellarium.model.QuestionModel;
import org.xmum.stellarium.utils.DbQuery;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    private List<QuestionModel> questionList;

    public QuestionAdapter(List<QuestionModel> questionList) {
        this.questionList = questionList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView question;
        private AppCompatButton optionA, optionB, optionC, optionD, preSelectedBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            question = itemView.findViewById(R.id.tvQuestion);
            optionA = itemView.findViewById(R.id.optionA);
            optionB = itemView.findViewById(R.id.optionB);
            optionC = itemView.findViewById(R.id.optionC);
            optionD = itemView.findViewById(R.id.optionD);
        }

        private void setData(final int pos){
            question.setText(questionList.get(pos).getQuestion());
            optionA.setText(questionList.get(pos).getOptionA());
            optionB.setText(questionList.get(pos).getOptionB());
            optionC.setText(questionList.get(pos).getOptionC());
            optionD.setText(questionList.get(pos).getOptionD());

            optionA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectOption(optionA, 1, pos);
                }
            });

            optionB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectOption(optionB, 2, pos);
                }
            });

            optionC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectOption(optionC, 3, pos);
                }
            });

            optionD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectOption(optionD, 4, pos);
                }
            });
        }

        private void selectOption(AppCompatButton button, int selectedAns, int questionID) {
            if (preSelectedBtn == null) {
                button.setBackgroundResource(R.drawable.selected_button);
                DbQuery.g_questionList.get(questionID).setSelectedAns(selectedAns);

                preSelectedBtn = button;
            } else {
                if (preSelectedBtn.getId() == button.getId()) {
                    // cancel selection
                    button.setBackgroundResource(R.drawable.unselected_button);
                    DbQuery.g_questionList.get(questionID).setSelectedAns(-1);

                    preSelectedBtn = null;
                } else {
                    preSelectedBtn.setBackgroundResource(R.drawable.unselected_button);
                    button.setBackgroundResource(R.drawable.selected_button);
                    DbQuery.g_questionList.get(questionID).setSelectedAns(selectedAns);
                    preSelectedBtn = button;
                }
            }
        }
    }
}
