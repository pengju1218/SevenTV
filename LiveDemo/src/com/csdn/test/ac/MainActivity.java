package com.csdn.test.ac;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.csdn.test.Base.BaseActivity;
import com.csdn.test.R;
import com.csdn.test.ac.play.PLMediaPlayerActivity;
import com.csdn.test.ac.push.HWCameraStreamingActivity;
import com.csdn.test.ac.push.HWCodecCameraStreamingActivity;
import com.csdn.test.ac.push.SWCodecCameraStreamingActivity;

/**
 * Created by Administrator on 2016/11/12.
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {
    private android.widget.Button btnpili;
    private android.widget.Button btnplay;
    private android.widget.RelativeLayout activitymain;

    @Override
    public void initView() {

        setContentView(R.layout.activity_main);
        this.activitymain = (RelativeLayout) findViewById(R.id.activity_main);
        this.btnplay = (Button) findViewById(R.id.btn_play);
        this.btnpili = (Button) findViewById(R.id.btn_pili);
        btnplay.setOnClickListener(this);
        btnpili.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_pili) {
            //  Intent intent = new Intent(MainActivity.this, HWCameraStreamingActivity.class);
            //   Intent intent = new Intent(MainActivity.this, HWCodecCameraStreamingActivity.class);
            Intent intent = new Intent(MainActivity.this, SWCodecCameraStreamingActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_play) {
            Intent intent = new Intent(MainActivity.this, PLMediaPlayerActivity.class);
            startActivity(intent);
        }
    }
}
