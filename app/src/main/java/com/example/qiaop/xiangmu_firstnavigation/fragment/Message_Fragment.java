package com.example.qiaop.xiangmu_firstnavigation.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qiaop.xiangmu_firstnavigation.R;
import com.example.qiaop.xiangmu_firstnavigation.adapter.FragmentStaeAdapter;
import com.example.qiaop.xiangmu_firstnavigation.base.fragment.BaseFragment;
import com.example.qiaop.xiangmu_firstnavigation.contact.ListNewsInterFace;
import com.example.qiaop.xiangmu_firstnavigation.entity.ListDataBean;
import com.example.qiaop.xiangmu_firstnavigation.presenter.IListNewsPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
//资讯
public class Message_Fragment extends BaseFragment<ListNewsInterFace.ListNewsView, IListNewsPresenter<ListNewsInterFace.ListNewsView>> implements ListNewsInterFace.ListNewsView {
    @BindView(R.id.tablayout_message)
    TabLayout tablayoutMessage;
    @BindView(R.id.viewpage_message)
    ViewPager viewpageMessage;
    Unbinder unbinder;
    private List<ListDataBean.NewsChannelListBean> newList = new ArrayList<>();
    private List<String> strings = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    private FragmentStaeAdapter fragmentStaeAdapter;

    public Message_Fragment() {
        // Required empty public constructor
    }


    @Override
    public int createLayoutId() {
        return R.layout.fragment_message_;
    }

    @Override
    protected void initEventAndData() {
        presenter.getListNewsTab("");

    }

    @Override
    protected IListNewsPresenter<ListNewsInterFace.ListNewsView> createPresenter() {
        return new IListNewsPresenter<>();
    }

    @Override
    public void showListNewsTab(ListDataBean listDataBean) {
        Log.e("1111111111", "listDataBean:" + listDataBean);
        List<ListDataBean.NewsChannelListBean> newsChannelList = listDataBean.getNewsChannelList();
        for (int i = 0; i < newsChannelList.size(); i++) {
            strings.add(newsChannelList.get(i).getChannelName());
            fragments.add(new Reuse_Fragment(newsChannelList.get(i).getChannelId()));
        }
        Log.e("1111111111", "strings:" + strings);
        fragmentStaeAdapter = new FragmentStaeAdapter(getChildFragmentManager(),fragments,strings);
        viewpageMessage.setAdapter(fragmentStaeAdapter);
        tablayoutMessage.setupWithViewPager(viewpageMessage);
    }

    @Override
    public void showError(String error) {

    }

    /*@Override
    public void ShowLoadingAnimtion() {
        dialog.show();
    }

    @Override
    public void HideLoadingAnimtion() {
        dialog.hide();
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }
}
