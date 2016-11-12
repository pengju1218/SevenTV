package com.csdn.test.Base;

import android.app.Application;

import com.qiniu.pili.droid.streaming.StreamingEnv;

public class SimplePlayerApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        StreamingEnv.init(getApplicationContext());
    }
}