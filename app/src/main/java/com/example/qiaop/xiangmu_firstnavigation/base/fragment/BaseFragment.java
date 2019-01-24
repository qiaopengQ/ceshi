package com.example.qiaop.xiangmu_firstnavigation.base.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.qiaop.xiangmu_firstnavigation.R;
import com.example.qiaop.xiangmu_firstnavigation.base.presenter.BasePresenter;
import com.example.qiaop.xiangmu_firstnavigation.base.view.BaseView;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<V,P extends BasePresenter<V>> extends Fragment implements BaseView {

    private Unbinder bind;
    protected Dialog dialog;
    public P presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(createLayoutId(), null);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind = ButterKnife.bind(this, view);
        CreateLoadingAnimtion(getContext());
        presenter = createPresenter();
        if (presenter!=null){
            presenter.attachView((V) this);
        }
        initEventAndData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            load();
        }
    }

    public void load() {

    }
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
    public abstract int createLayoutId();
    protected abstract void initEventAndData();
    protected abstract P createPresenter();

    @Override
    public void ShowLoadingAnimtion() {
        dialog.show();
    }

    @Override
    public void HideLoadingAnimtion() {
        dialog.hide();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bind!=null){
            bind.unbind();
        }
        if (presenter!=null){
            presenter.destoryView();
            presenter = null;
        }
    }
}
