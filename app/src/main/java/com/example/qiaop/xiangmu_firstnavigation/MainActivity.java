package com.example.qiaop.xiangmu_firstnavigation;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.example.qiaop.xiangmu_firstnavigation.adapter.FragmentAdapter;
import com.example.qiaop.xiangmu_firstnavigation.base.activity.BaseActivity;
import com.example.qiaop.xiangmu_firstnavigation.contact.ListNewsInterFace;
import com.example.qiaop.xiangmu_firstnavigation.entity.ListDataBean;
import com.example.qiaop.xiangmu_firstnavigation.fragment.Circle_Fragment;
import com.example.qiaop.xiangmu_firstnavigation.fragment.Message_Fragment;
import com.example.qiaop.xiangmu_firstnavigation.fragment.My_Fragment;
import com.example.qiaop.xiangmu_firstnavigation.fragment.Topic_Fragment;
import com.example.qiaop.xiangmu_firstnavigation.presenter.IListNewsPresenter;
import com.example.qiaop.xiangmu_firstnavigation.utils.GetWindowManagerUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

   /* @BindView(R.id.seek_main)
    ImageView seekMain;
    @BindView(R.id.relativeLayout)
    RelativeLayout relativeLayout;
    @BindView(R.id.viewpage_main)
    ViewPager viewpageMain;
    @BindView(R.id.message_main)
    RadioButton messageMain;
    @BindView(R.id.topic_main)
    RadioButton topicMain;
    @BindView(R.id.circle_main)
    RadioButton circleMain;
    @BindView(R.id.my_main)
    RadioButton myMain;
    @BindView(R.id.radiogroup_main)
    RadioGroup radiogroupMain;*/
    private List<Fragment> fragments = new ArrayList<>();
    /*@Override
    protected void initEventAndData() {
        fragments.add(new Message_Fragment());
        fragments.add(new Topic_Fragment());
        fragments.add(new Circle_Fragment());
        fragments.add(new My_Fragment());
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),fragments);
        viewpageMain.setAdapter(fragmentAdapter);
        radiogroupMain.setOnCheckedChangeListener(this);
        //mPresenter.getListNewsTab("");
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

 /*   @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.message_main:
                viewpageMain.setCurrentItem(1);
                break;
            case R.id.topic_main:
                viewpageMain.setCurrentItem(2);
                break;
            case R.id.circle_main:
                viewpageMain.setCurrentItem(3);
                break;
            case R.id.my_main:
                viewpageMain.setCurrentItem(4);
                break;
        }
    }*/
}
