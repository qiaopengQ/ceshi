package com.example.qiaop.xiangmu_firstnavigation.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.qiaop.xiangmu_firstnavigation.Identifying.RefreshtheloadApi;
import com.example.qiaop.xiangmu_firstnavigation.R;
import com.example.qiaop.xiangmu_firstnavigation.base.fragment.BaseFragment;
import com.example.qiaop.xiangmu_firstnavigation.contact.RefreshtheloadFace;
import com.example.qiaop.xiangmu_firstnavigation.entity.DownListBean;
import com.example.qiaop.xiangmu_firstnavigation.presenter.RefreshtheloadListPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * 复用
 */
@SuppressLint("ValidFragment")
public class Reuse_Fragment extends BaseFragment<RefreshtheloadFace.ShowView, RefreshtheloadListPresenter<RefreshtheloadFace.ShowView>> implements RefreshtheloadFace.ShowView {
    @BindView(R.id.rv_reuse)
    RecyclerView rvReuse;
    @BindView(R.id.smart_reuse)
    SmartRefreshLayout smartReuse;
    Unbinder unbinder;
    private String ChannelId = null;
    private HashMap<String, Object> map = new HashMap<>();

    public Reuse_Fragment(String s) {
        ChannelId = s;
        Log.e("Reuse_Fragment", ChannelId);
    }

    @Override
    public int createLayoutId() {
        return R.layout.fragment_reuse_;
    }

    @Override
    protected void initEventAndData() {
        Toast.makeText(getContext(), ChannelId, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected RefreshtheloadListPresenter<RefreshtheloadFace.ShowView> createPresenter() {
        return new RefreshtheloadListPresenter<>();
    }

    @Override
    public void load() {
       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               JSONObject jsonObject = new JSONObject();
               try {
                   jsonObject.put("userId", "049de01db14a4c8184faa0aca7facf8a");
                   jsonObject.put("channelId", ChannelId);
                   jsonObject.put("cursor", "0");
               } catch (JSONException e) {
                   e.printStackTrace();
               }
               String s = jsonObject.toString();
               map.put("url", "news/upListNews");
               map.put("json", s);
               map.put("header", "application/json");
               presenter.getList(map, RefreshtheloadApi.UPLISTNEWS);
           }
       },30);
    }

    @Override
    public void showList(DownListBean downListBean, RefreshtheloadApi refreshtheloadApi) {
        switch (refreshtheloadApi) {
            case UPLISTNEWS:
                String s = downListBean.getNewList().toString();
                Log.e("aaaaaaaaaaaaaaaaaa", s);
                break;
            case DOWNLISTNEWS:
                break;
        }
    }

    @Override
    public void showError(String error) {
        Log.e("errorshuaxin", error);
    }


}
