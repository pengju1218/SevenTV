package com.csdn.test.Base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public abstract class BaseFragment extends Fragment {

    private String title;
    private int iconId;
    private int bgColor;
    private int textColor;


    public int getBgColor() {
        return bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /*
    * 返回一个需要展示的View
    */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = initRootView(inflater);
        findView(view);

        return view;
    }
    protected boolean isVisible;
    /**
     * 在这里实现Fragment数据的缓加载.
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /**
     * 加载数据
     */
    protected void onVisible(){
        lazyLoad();
    }

    protected abstract void lazyLoad();

    protected void onInvisible(){}

    /**
     * 子类实现此抽象方法返回View进行展示
     *
     * @return
     */
    public abstract View initRootView(LayoutInflater inflater);


    /**
     * 初始化控件
     */
    protected abstract void findView(View view);


    /*
     * 当Activity初始化之后可以在这里进行一些数据的初始化操作
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }




    public  onChangeListener onChangeListener;

    public void setOnChangeListener(BaseFragment.onChangeListener onChangeListener) {
        this.onChangeListener = onChangeListener;
    }

    public BaseFragment.onChangeListener getOnChangeListener() {
        return onChangeListener;
    }

    public  interface  onChangeListener{
        public void initSelect(int index);
    }
}