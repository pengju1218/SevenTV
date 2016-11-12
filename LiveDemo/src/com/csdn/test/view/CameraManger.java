package com.csdn.test.view;


import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Sensor;

import java.io.EOFException;

/**
 * Created by Administrator on 2016/11/12.
 */
public class CameraManger {

    public static CameraManger cameraManger;

    private Camera camera = Camera.open();

    public static CameraManger getInstance() {
        if (cameraManger == null) {
            cameraManger = new CameraManger();
        }

        return cameraManger;
    }

    public void doOpenCamera(SurfaceTexture surface) {
        try {
            camera = Camera.open();
            camera.setPreviewTexture(surface);
            //camera.startPreview();
            camera.setPreviewCallback(previewCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean pre = false;
    private Camera.PreviewCallback previewCallback = new Camera.PreviewCallback() {

        @Override
        public void onPreviewFrame(byte[] data, Camera camera) {

            if (data != null) {
                pre = true;
            } else {
                pre = false;
            }
        }
    };

    public boolean isPreviewing() {

        return pre;
    }

    public void doStartPreview(SurfaceTexture surface) {
        try {
            camera.setPreviewTexture(surface);
            camera.startPreview();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void doStopCamera() {
        camera.stopPreview();
        camera.release();
    }

}
