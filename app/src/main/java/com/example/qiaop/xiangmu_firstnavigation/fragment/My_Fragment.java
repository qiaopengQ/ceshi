package com.example.qiaop.xiangmu_firstnavigation.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qiaop.xiangmu_firstnavigation.R;

/**
 * A simple {@link Fragment} subclass.
 */
//我的
public class My_Fragment extends Fragment {


    public My_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_, container, false);
    }

}
