package com.example.abymani.image_comm;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class CaptureRecognizeDecode extends Activity {
    private static final String TAG = "FrontCamera";
    Camera camera;
    Preview preview;
    ImageView img;
    TextView text;
    int i=0;
    Recognize recog;
    DecodeSaveprogress decode;
    int index;

    Bitmap orig;

    ArrayList<Integer> colors;
    ArrayList<Bitmap> pictures;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_recognize_decode);
        IntaializeALL();


    }

    private void IntaializeALL() {
//        preview = new Preview(this);
//        ((FrameLayout) findViewById(R.id.preview)).addView(preview);
        img = (ImageView) findViewById(R.id.img);
//        text= (TextView)findViewById(R.id.t2);
//
        colors=new ArrayList<Integer>();
        pictures=new ArrayList<Bitmap>();
        recog=new Recognize();
       // decode=new DecodeSaveprogress();
        index=0;

        ///pics intialize

        pictures.add(BitmapFactory.decodeResource(getResources(),R.drawable.pic1));

        pictures.add(BitmapFactory.decodeResource(getResources(),R.drawable.testsubject1));
        pictures.add(BitmapFactory.decodeResource(getResources(),R.drawable.testsubject2));
        pictures.add(BitmapFactory.decodeResource(getResources(),R.drawable.testsubject3));
        pictures.add(BitmapFactory.decodeResource(getResources(),R.drawable.testsubject6));


        pictures.add(BitmapFactory.decodeResource(getResources(),R.drawable.pic4));
        pictures.add(BitmapFactory.decodeResource(getResources(),R.drawable.pic5));

        img.setImageBitmap(pictures.get(3));
        //recognize();

    }

    public void recognize(View view) {



        while (!recog.checkforStart(pictures.get(index++))){//pictures.remove(index-1);
        }

        toast("Start pic on index "+ String.valueOf(index-1));

        while (!recog.checkforStart(pictures.get(index++))){//pictures.remove(index-1);
        }

        toast("Start pic on index "+ String.valueOf(index-1));
//
//        while (!recog.checkforStart(pictures.get(index))&&index>6)
//        //check for start
//        {
//
//            //  colors.addAll(recog.imageprocessing(pictures.get(index)));
//
////            for (int i=0;i<colors.size();i++) {
////                toast(String.valueOf(colors.get(i)));
//            //  img.setImageBitmap(pictures.get(index));
////            }
////            colors.clear();
////            decode.decode();
////            pictures.remove(index);
//            toast(String.valueOf(index));
//            index++;
//        }
//
//        finish();

    }

    private void recognize() {


    }

    private void toast(String s) {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }


    public static Bitmap rotateBitmap (Bitmap source, float angle){
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return  Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),matrix,true);
    }


    public void image_capture(final View view) {
        final Handler handler = new Handler();

        Runnable run = new Runnable() {
            @Override
            public void run() {
                i++;
                preview.camera.takePicture(null, null,
                        jpegCallback);
                view.setEnabled(false);

                if (i < 5)
                    handler.postDelayed(this, 400);
                text.setText(String.valueOf(i));


            }
        };

        handler.postDelayed(run, 300);


    }

    PictureCallback jpegCallback = new PictureCallback() {
        public void onPictureTaken(byte[] data, Camera camera) {


            FileOutputStream outStream = null;

            Bitmap bitmap= BitmapFactory.decodeByteArray(data, 0, data.length);
            Bitmap bmap = rotateBitmap(bitmap , 90);
            img.setImageBitmap(bmap);

           picttaken(bmap);
//
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            bmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//            byte[] byteArray = stream.toByteArray();

//
//
//            try {
//
//                outStream = new FileOutputStream(String.format(
//                        "/storage/emulated/0/Gujjar_g/l%d.jpg",
//                        System.currentTimeMillis()));
//                outStream.write(byteArray);
//                outStream.close();
//                Log.d(TAG, "onPictureTaken - wrote bytes: " + data.length);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//            }

            camera.startPreview();

        }
    };

    private void picttaken(Bitmap bitmap) {

        pictures.add(bitmap);
    }


}

