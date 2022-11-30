package org.xmum.stellarium;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import org.xmum.stellarium.adapter.CategoryAdapter;
import org.xmum.stellarium.model.CategoryModel;
import org.xmum.stellarium.utils.DbQuery;

import java.util.ArrayList;
import java.util.List;


public class EducationFragment extends Fragment {
    private GridView catView;
//    private List<CategoryModel> catList = new ArrayList<>();

    public EducationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_education, container, false);

        catView = view.findViewById(R.id.cat_Grid);

//        loadCategories();

        CategoryAdapter adapter = new CategoryAdapter(DbQuery.g_catList);
        catView.setAdapter(adapter);

        return view;
    }

//    private void loadCategories(){
//        catList.clear();
//        catList.add(new CategoryModel("Earth and Moon"));
//        catList.add(new CategoryModel("Solar System"));
//        catList.add(new CategoryModel("Milky Way Galaxy"));
//        catList.add(new CategoryModel("Rocket"));
//        catList.add(new CategoryModel("Black Hole"));
//        catList.add(new CategoryModel("Universe"));
//    }
}