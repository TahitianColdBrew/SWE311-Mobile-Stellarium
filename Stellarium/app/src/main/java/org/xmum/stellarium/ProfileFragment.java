package org.xmum.stellarium;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;


public class ProfileFragment extends Fragment {

    private Button btnLogOut;

    public ProfileFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        btnLogOut = view.findViewById(R.id.btn_log_out);

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1. log out user
                FirebaseAuth.getInstance().signOut();
                // 2. redirect to login page
                Intent intent = new Intent(getContext(),LoginActivity.class);
                getActivity().finish();
                startActivity(intent);
            }
        });

        return view;
    }
}