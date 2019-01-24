package com.example.qiaop.xiangmu_firstnavigation.base.activity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.qiaop.xiangmu_firstnavigation.R;
import com.example.qiaop.xiangmu_firstnavigation.base.presenter.BasePresenter;
import com.example.qiaop.xiangmu_firstnavigation.base.view.BaseView;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity <V, P extends BasePresenter<V>> extends AppCompatActivity implements BaseView {
    public P mPresenter;
    private Unbinder bind;
    private Context context;
    protected View mview;
    protected PopupWindow popupWindow;
    private View view;
    protected Dialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup view= (ViewGroup) LayoutInflater.from(this).inflate(createLayoutId(),null);

        setContentView(view);
        bind = ButterKnife.bind(this);
        CreateLoadingAnimtion(this);
        mPresenter= createPresenter();
        if (mPresenter!=null){
            mPresenter.attachView((V) this);
        }
        initEventAndData();
    }

    protected abstract void initEventAndData();

    protected abstract P createPresenter();

    protected void CreateLoadingAnimtion(Context context) {
        View viewa = LayoutInflater.from(context).inflate(R.layout.animation_view, null);
        AVLoadingIndicatorView avloadingIndicatorView = viewa.findViewById(R.id.AVLoadingIndicatorView_);
        avloadingIndicatorView.smoothToShow();
 /*       dialog.setContentView(viewa, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));*/
        dialog = new Dialog(context, R.style.loading_dialog_style);
        dialog.setCancelable(false);
        dialog.setContentView(viewa, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
    }

    protected abstract int createLayoutId();

    @Override
    public void ShowLoadingAnimtion() {
        dialog.show();
    }

    @Override
    public void HideLoadingAnimtion() {
        dialog.hide();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind!=null){
            bind.unbind();
        }
        if (mPresenter!=null){
            mPresenter.destoryView();
            mPresenter = null;
        }
    }
}
