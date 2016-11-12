package com.csdn.test.Base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.widget.Toast;

public abstract class BaseActivity extends FragmentActivity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		//this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
		initView();
		//initListener();
	}
	/**
	 * 初始化控件
	 */
	public abstract void initView();
	
	/**
	 * 获取数据
	 */

	/**
	 * 监听跳转的方法
	 */

	public void showToast(String msg){
		Toast.makeText(BaseActivity.this,msg, Toast.LENGTH_SHORT).show();
	}
}
