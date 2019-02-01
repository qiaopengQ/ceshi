package com.example.qiaop.xiangmu_firstnavigation.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.qiaop.xiangmu_firstnavigation.R;
import com.example.qiaop.xiangmu_firstnavigation.adapter.FragmentAdapter;
import com.example.qiaop.xiangmu_firstnavigation.fragment.Circle_Fragment;
import com.example.qiaop.xiangmu_firstnavigation.fragment.Message_Fragment;
import com.example.qiaop.xiangmu_firstnavigation.fragment.My_Fragment;
import com.example.qiaop.xiangmu_firstnavigation.fragment.Topic_Fragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.seek_main)
    ImageView seekMain;
    @BindView(R.id.relativeLayout)
    RelativeLayout relativeLayout;

    @BindView(R.id.message_main)
    RadioButton messageMain;
    @BindView(R.id.topic_main)
    RadioButton topicMain;
    @BindView(R.id.circle_main)
    RadioButton circleMain;
    @BindView(R.id.my_main)
    RadioButton myMain;
    @BindView(R.id.radiogroup_main)
    RadioGroup radiogroupMain;
    private List<Fragment> fragments = new ArrayList<>();
    private FragmentManager manager;
    private Message_Fragment message_fragment;
    private Topic_Fragment topic_fragment;
    private Circle_Fragment circle_fragment;
    private My_Fragment my_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        message_fragment = new Message_Fragment();
        /*topic_fragment = new Topic_Fragment();
        circle_fragment = new Circle_Fragment();
        my_fragment = new My_Fragment();*/
        manager = getSupportFragmentManager();

        /*fragments.add(new Message_Fragment());
        fragments.add(new Topic_Fragment());
        fragments.add(new Circle_Fragment());
        fragments.add(new My_Fragment());
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),fragments);
        viewpageMain.setAdapter(fragmentAdapter);*/
        radiogroupMain.setOnCheckedChangeListener(this);
        manager.beginTransaction().add(R.id.fra, message_fragment).commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction transaction = manager.beginTransaction();
        switch (checkedId) {
            case R.id.message_main:
                transaction.replace(R.id.fra,new Message_Fragment());


                //viewpageMain.setCurrentItem(0);
                break;
            case R.id.topic_main:
                transaction.replace(R.id.fra,new Topic_Fragment());
                //viewpageMain.setCurrentItem(1);
                break;
            case R.id.circle_main:
                transaction.replace(R.id.fra,new Circle_Fragment());
                //viewpageMain.setCurrentItem(2);
                break;
            case R.id.my_main:
                transaction.replace(R.id.fra,new My_Fragment());
                //viewpageMain.setCurrentItem(3);
                break;
        }
        transaction.commit();
    }
}
