package com.example.abymani.image_comm;

/**
 * Created by Ali on 10/12/2016.
 */

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;


class Preview extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = "Preview";

    SurfaceHolder mHolder;


    public Camera camera;

    Preview(Context context) {
        super(context);

        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        // camera = openFrontFacingCamera();
        camera = Camera.open();
        camera.setDisplayOrientation(90);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        // The Surface has been created, acquire the camera and tell it where
        // to draw.
        // camera = Camera.open();



        try {
            camera.setPreviewDisplay(holder);


            camera.setPreviewCallback(new Camera.PreviewCallback() {

                public void onPreviewFrame(byte[] data, Camera arg1) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // Surface will be destroyed when we return, so stop the preview.
        // Because the CameraDevice object is not a shared resource, it's very
        // important to release it when the activity is paused.
        //   camera.stopPreview();
        // camera = null;
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        // Now that the size is known, set up the camera parameters and begin
        // the preview.
//        Camera.Parameters parameters = camera.getParameters();
//        parameters.setPreviewSize(w, h);
//        camera.setParameters(parameters);
        camera.startPreview();

    }


    public  void takepic(){

    }
}