package com.csdn.test.ac.push;

import android.hardware.Camera;
import android.view.MotionEvent;
import android.view.WindowManager;

import com.csdn.test.Base.BaseActivity;
import com.csdn.test.R;
import com.csdn.test.view.CameraGLSurfaceView;
import com.csdn.test.view.CameraPreviewFrameView;
import com.csdn.test.view.gl.CameraRenderer;
import com.qiniu.android.dns.DnsManager;
import com.qiniu.android.dns.IResolver;
import com.qiniu.android.dns.NetworkInfo;
import com.qiniu.android.dns.http.DnspodFree;
import com.qiniu.android.dns.local.AndroidDnsServer;
import com.qiniu.android.dns.local.Resolver;
import com.qiniu.pili.droid.streaming.AVCodecType;
import com.qiniu.pili.droid.streaming.CameraStreamingSetting;
import com.qiniu.pili.droid.streaming.MediaStreamingManager;
import com.qiniu.pili.droid.streaming.MicrophoneStreamingSetting;
import com.qiniu.pili.droid.streaming.StreamingProfile;
import com.qiniu.pili.droid.streaming.StreamingState;
import com.qiniu.pili.droid.streaming.StreamingStateChangedListener;
import com.qiniu.pili.droid.streaming.widget.AspectFrameLayout;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URISyntaxException;

public class HWCameraStreamingActivity extends BaseActivity implements StreamingStateChangedListener, CameraPreviewFrameView.Listener {
    private MediaStreamingManager streamingManager;
    private StreamingProfile streamingProfile;
    private MicrophoneStreamingSetting mMicrophoneStreamingSetting;
    private  CameraGLSurfaceView cameraPreviewFrameView ;

    @Override
    public void initView() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_hwcamera_streaming);

        AspectFrameLayout afl = (AspectFrameLayout) findViewById(R.id.cameraPreview_afl);
        afl.setShowMode(AspectFrameLayout.SHOW_MODE.REAL);

        cameraPreviewFrameView= (CameraGLSurfaceView) findViewById(R.id.cameraPreview_surfaceView);

        //cameraPreviewFrameView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        String publishurl = "这里换成你的推流地址";
        streamingProfile = new StreamingProfile();
        streamingManager = new MediaStreamingManager(this, afl, cameraPreviewFrameView,
                AVCodecType.HW_VIDEO_WITH_HW_AUDIO_CODEC); // hw codec  // soft codec

        try {
            streamingProfile.setVideoQuality(StreamingProfile.VIDEO_QUALITY_MEDIUM2)
                    .setAudioQuality(StreamingProfile.AUDIO_QUALITY_MEDIUM2)
//                .setPreferredVideoEncodingSize(960, 544)
                    .setEncodingSizeLevel(StreamingProfile.VIDEO_ENCODING_HEIGHT_480)
                    .setEncoderRCMode(StreamingProfile.EncoderRCModes.BITRATE_PRIORITY)
//                .setAVProfile(avProfile)
                    .setDnsManager(getMyDnsManager())
                    .setAdaptiveBitrateEnable(true)
                    .setFpsControllerEnable(true)
                    .setStreamStatusConfig(new StreamingProfile.StreamStatusConfig(3))
                    .setPublishUrl(publishurl)
//                .setEncodingOrientation(StreamingProfile.ENCODING_ORIENTATION.PORT)
                    .setSendingBufferProfile(new StreamingProfile.SendingBufferProfile(0.2f, 0.8f, 3.0f, 20 * 1000));
            CameraStreamingSetting setting = new CameraStreamingSetting();
            setting.setCameraId(Camera.CameraInfo.CAMERA_FACING_BACK)
                    .setContinuousFocusModeEnabled(true)
                    .setCameraPrvSizeLevel(CameraStreamingSetting.PREVIEW_SIZE_LEVEL.MEDIUM)
                    .setCameraPrvSizeRatio(CameraStreamingSetting.PREVIEW_SIZE_RATIO.RATIO_16_9);


            mMicrophoneStreamingSetting = new MicrophoneStreamingSetting();
            mMicrophoneStreamingSetting.setBluetoothSCOEnabled(false);
            streamingManager.prepare(setting, mMicrophoneStreamingSetting, null, streamingProfile);
            streamingManager.setStreamingStateListener(this);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        cameraPreviewFrameView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // You must invoke pause here.
        cameraPreviewFrameView.onPause();
    }

    @Override
    public void onStateChanged(StreamingState streamingState, Object o) {
        switch (streamingState) {
            case PREPARING:
                break;
            case READY:
                // start streaming when READY
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (streamingManager != null) {
                            streamingManager.startStreaming();
                        }
                    }
                }).start();
                break;
            case CONNECTING:
                break;
            case STREAMING:
                // The av packet had been sent.
                break;
            case SHUTDOWN:
                // The streaming had been finished.
                break;
            case IOERROR:
                // Network connect error.
                break;
            case SENDING_BUFFER_EMPTY:
                break;
            case SENDING_BUFFER_FULL:
                break;
            case AUDIO_RECORDING_FAIL:
                // Failed to record audio.
                break;
            case OPEN_CAMERA_FAIL:
                // Failed to open camera.
                break;
            case DISCONNECTED:
                // The socket is broken while streaming
                break;
        }
    }

    private static DnsManager getMyDnsManager() {
        IResolver r0 = new DnspodFree();
        IResolver r1 = AndroidDnsServer.defaultResolver();
        IResolver r2 = null;
        try {
            r2 = new Resolver(InetAddress.getByName("119.29.29.29"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return new DnsManager(NetworkInfo.normal, new IResolver[]{r0, r1, r2});
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onZoomValueChanged(float factor) {
        return false;
    }

}