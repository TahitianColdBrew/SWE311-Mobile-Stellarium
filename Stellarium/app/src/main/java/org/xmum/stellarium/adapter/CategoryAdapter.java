package org.xmum.stellarium.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.xmum.stellarium.R;
import org.xmum.stellarium.model.CategoryModel;

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
               //TODO: choose an category
            }
        });

        TextView catName = myView.findViewById(R.id.catName);
        ImageView catImg = myView.findViewById(R.id.catImg);

        catName.setText(cat_list.get(position).getName());
        Picasso.get().load(cat_list.get(position).getUrl()).into(catImg);

        return myView;
    }
}
