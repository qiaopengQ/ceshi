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
//圈子
public class Circle_Fragment extends Fragment {


    public Circle_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_circle_, container, false);
    }

}
