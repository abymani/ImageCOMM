package com.example.abymani.image_comm;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class CannyEdge extends Activity implements SurfaceHolder.Callback, View.OnClickListener {
    private static final String TAG = "CameraTest";
    Camera mCamera;
    SurfaceView surfaceView;
    ImageView img;
    ImageView img2;

    //Camera camera;
    boolean mPreviewRunning = false;
    int j = 1;
    int i = 1;
    int p = 1;
    int a = 1;
    int id = 0;


    @SuppressWarnings("deprecation")


    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        Log.e(TAG, "onCreate");
        System.loadLibrary("opencv_java3");
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_canny_edge);
        img = (ImageView) findViewById(R.id.imgView);
        img2 = (ImageView) findViewById(R.id.imgView2);

//        AudioManager mgr = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//        int streamType = AudioManager.STREAM_SYSTEM;
//        mgr.setStreamSolo(streamType, true);
//        mgr.setRingerMode(AudioManager.RINGER_MODE_SILENT);
//        mgr.setStreamMute(streamType, true);
//

        mSurfaceView = (SurfaceView) findViewById(R.id.surface_camera);
        mSurfaceView.setOnClickListener(this);
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(this);
        mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

    }


    Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {

        public void onPictureTaken(byte[] data, Camera camera) {
            // TODO Auto-generated method stub
            if (data != null) {


                mCamera.stopPreview();
                mPreviewRunning = false;
                mCamera.release();

                try {
                    Bitmap bm;
                    BitmapFactory.Options opts = new BitmapFactory.Options();
                    Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, opts);
                    img.setImageBitmap(bitmap);


                    Mat source = new Mat();
                    Mat destination = new Mat();
                    Utils.bitmapToMat(bitmap, source);
                    Imgproc.Canny(source, destination, 50, 155);
                    Bitmap bmm;
                    Bitmap bmn = Bitmap.createBitmap(destination.width(), destination.height(), Bitmap.Config.ARGB_8888);

                    Utils.matToBitmap(destination, bmn);

                    Size sz = new Size(100, 100);


                    img2.setImageBitmap(bmn);




                    j++;


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }

    };


    protected void onResume() {
        Log.e(TAG, "onResume");
        super.onResume();
    }

    protected void onStop() {
        Log.e(TAG, "onStop");
        super.onStop();
    }

    @TargetApi(9)
    public void surfaceCreated(SurfaceHolder holder) {
        Log.e(TAG, "surfaceCreated");

        take_pic(holder);

    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        Log.e(TAG, "surfaceChanged");


    }


    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.e(TAG, "surfaceDestroyed");

    }

    public SurfaceView mSurfaceView;
    public SurfaceHolder mSurfaceHolder;

    public void onClick(View v) {
        // TODO Auto-generated method stub


    }

    public void take_pic(final SurfaceHolder holder) {


        final Thread thread = new Thread() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                super.run();
                while (a < 4) {
                    try {
                        mCamera = Camera.open();

                        if (mPreviewRunning) {
                            mCamera.stopPreview();
                        }

                        Camera.Parameters p = mCamera.getParameters();
                        p.setPreviewSize(300, 300);


                        mCamera.setParameters(p);
                        try {
                            mCamera.setPreviewDisplay(holder);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        mCamera.startPreview();
                        mPreviewRunning = true;
                        // take_pic(holder);

                        mCamera.takePicture(null, null, mPictureCallback);
                        //  AudioManager mgrs = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
                        //mgrs.setStreamMute(AudioManager.STREAM_SYSTEM, false);

                        a++;
                        sleep(900);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }
}