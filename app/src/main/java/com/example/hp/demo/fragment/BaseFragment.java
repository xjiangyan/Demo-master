package com.example.hp.demo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment{

    /**
     * 界面可见
     */
    protected boolean isVisible;
    protected View rootView;
    /**
     * 界面准备就绪
     */
    private boolean isPrepared;

    /**
     * 界面数据加载完成
     */
    protected boolean isDataLoaded;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateInitData();
    }

    @Override
    public void onResume() {
        super.onResume();
        onResumeLoadData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView != null) {
            isPrepared = true;
            return rootView;
        }
        rootView = initView(inflater, container, savedInstanceState);
        isPrepared = true;
        lazyLoad();
        return rootView;
    }

    protected void onVisible() {
        if (isPrepared && isVisible && !isDataLoaded) {
            lazyLoad();
        }
    }
    protected void onInvisible() {}

    protected abstract void lazyLoad();
    protected abstract View initView(LayoutInflater inflater, ViewGroup container,
                                     Bundle savedInstanceState);
    protected abstract void onCreateInitData();
    protected abstract void onResumeLoadData();
}
