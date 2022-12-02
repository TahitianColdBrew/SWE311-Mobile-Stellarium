package org.xmum.stellarium.adapter;

import android.app.Dialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;

import com.squareup.picasso.Picasso;

import org.xmum.stellarium.R;
import org.xmum.stellarium.StartTestActivity;
import org.xmum.stellarium.model.CategoryModel;
import org.xmum.stellarium.utils.DbQuery;

import java.util.List;

public class CategoryAdapter extends BaseAdapter {
    private List<CategoryModel> cat_list;

    public CategoryAdapter(List<CategoryModel> cat_list) {
        this.cat_list = cat_list;
    }

    @Override
    public int getCount() {
        return cat_list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View myView;

        if(convertView == null){
            myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_item_layout, parent, false);
        }else{
            myView = convertView;
        }

        myView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DbQuery.g_selectedCatIndex = position;

                Dialog dialog = new Dialog(parent.getContext());
                dialog.setContentView(R.layout.education_choose_dialog);
                dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_background);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCancelable(false);
//                dialog.getWindow().setBackgroundDrawable(new InsetDrawable(new ColorDrawable(Color.TRANSPARENT), 20));

                ImageView catImg, btnClose;
                TextView catName;
                AppCompatButton btnLearn, btnQuiz;

                catImg = dialog.findViewById(R.id.catImg);
                catName = dialog.findViewById(R.id.catName);
                btnLearn = dialog.findViewById(R.id.btn_learn);
                btnQuiz = dialog.findViewById(R.id.btn_quiz);
                btnClose = dialog.findViewById(R.id.btn_close);

                btnClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                btnQuiz.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // TODO: load questions for selected category
                        Intent intent = new Intent(view.getContext(), StartTestActivity.class);
                        view.getContext().startActivity(intent);
                    }
                });

                btnLearn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // TODO: jump to education page
                    }
                });

                Picasso.get().load(cat_list.get(position).getUrl()).into(catImg);
                catName.setText(cat_list.get(position).getName());

                dialog.show();

            }
        });

        TextView catName = myView.findViewById(R.id.catName);
        ImageView catImg = myView.findViewById(R.id.catImg);

        catName.setText(cat_list.get(position).getName());
        Picasso.get().load(cat_list.get(position).getUrl()).into(catImg);

        return myView;
    }
}
