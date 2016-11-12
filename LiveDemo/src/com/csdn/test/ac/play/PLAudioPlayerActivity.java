package com.csdn.test.ac.play;

import android.view.View;
import android.view.WindowManager;

import com.csdn.test.Base.BaseActivity;
import com.csdn.test.R;
import com.csdn.test.view.MediaController;
import com.pili.pldroid.player.widget.PLVideoTextureView;

/**
 * This demo shows how to use PLMediaPlayer API playing audio stream
 */
public class PLAudioPlayerActivity extends BaseActivity implements View.OnClickListener {


    private com.pili.pldroid.player.widget.PLVideoTextureView plText;

    @Override
    public void initView() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_audio_player);
        this.plText = (PLVideoTextureView) findViewById(R.id.pl_Text);
        plText.setMediaController(new MediaController(this));
        String mVideoPath = "rtmp://live.hkstv.hk.lxdns.com/live/hks";
        plText.setVideoPath(mVideoPath);

    }

    @Override
    public void onClick(View v) {

    }
    @Override
    protected void onResume() {
        super.onResume();

        plText.start();
    }

    @Override
    protected void onPause() {
        super.onPause();

        plText.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        plText.stopPlayback();
    }
}
