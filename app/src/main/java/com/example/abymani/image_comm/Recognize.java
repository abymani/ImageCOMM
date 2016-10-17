package com.example.abymani.image_comm;

import android.graphics.Bitmap;
import android.os.Handler;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;

/**
 * Created by abymani on 13/10/2016.
 */
public class Recognize {
    Bitmap orig;
    Bitmap bit;
    ArrayList<Point> centerPoints;

    ArrayList<Integer> colors;

    int index;
    boolean startflag;
    boolean endflag;



    public Recognize() {
        System.loadLibrary("opencv_java3");
        index=0;
        centerPoints=new ArrayList<Point>();
        colors=new ArrayList<Integer>();
        startflag=false;
        endflag=false;
    }

    public void colorRecognition() {

        //   byte[][] colors= new byte[centerPoints.length][3];
        final int[] col = new int[5];


        for (int i = 0; i < centerPoints.size(); i++) {
            colors.add(orig.getPixel((int) centerPoints.get(i).x, (int) centerPoints.get(i).y));
            //   Toast.makeText(getApplicationContext(),String.valueOf(col[i]),Toast.LENGTH_SHORT).show();
            //   Toast.makeText(getApplicationContext(),String.valueOf(centerPoints.size()),Toast.LENGTH_SHORT).show();
        }

        final Handler hand = new Handler();
        Runnable run = new Runnable() {
            @Override
            public void run() {
                index++;
                if (index < 4)
                    hand.postDelayed(this, 2000);
            }
        };
        hand.postDelayed(run, 10);
        //  Toast.makeText(getApplicationContext(),String.valueOf(col),Toast.LENGTH_SHORT).show();
        //  rl.setBackgroundColor(col);


    }

    public void circledetect() {
        // Load input image
        Mat bgr_image = new Mat();
        Utils.bitmapToMat(orig,bgr_image);

        Mat orig_image = bgr_image.clone();

        Imgproc.medianBlur(bgr_image, bgr_image, 3);

        // Convert input image to HSV
        Mat hsv_image = new Mat();
        Imgproc.cvtColor(bgr_image, hsv_image, Imgproc.COLOR_BGR2HSV);




        // Threshold the HSV image, keep only the red pixels
        Mat lower_red_hue_range = new Mat();
        Mat upper_red_hue_range = new Mat();
        Core.inRange(hsv_image, new Scalar(110,50,50), new Scalar(130,255,255), lower_red_hue_range);
        Core.inRange(hsv_image, new Scalar(160, 100, 100), new Scalar(179, 255, 255), upper_red_hue_range);

        // Combine the above two images
        Mat red_hue_image = new Mat();
        Core.addWeighted(lower_red_hue_range, 1.0, upper_red_hue_range, 1.0, 0.0, red_hue_image);

        Imgproc.GaussianBlur(red_hue_image, red_hue_image, new Size(9, 9), 2, 2);

        //       Use the Hough transform to detect circles in the combined threshold image




        Mat circles = new Mat();
        Imgproc.HoughCircles(red_hue_image, circles, Imgproc.CV_HOUGH_GRADIENT, 1, red_hue_image.rows()/6, 100, 30, 200,0);

        // Loop over all detected circles and outline them on the original image

        for(int current_circle = 0; current_circle < circles.cols(); ++current_circle) {
            double[] vCircle = circles.get(0,current_circle);
            double x = vCircle[0];
            double y = vCircle[1];
            double radius = vCircle[2];
            Point point=new Point(x,y);
            centerPoints.add(point);



            Imgproc.circle(orig_image, point, (int) radius, new Scalar(0, 255, 0), 30);



        }
        //Ending condition
        if(centerPoints.size()==1)
            endflag=true;


    }

    public void pointcomparison(){

        Point PA=new Point();
        Point PB=new Point();
        Point PC=new Point();
        Point PD=new Point();
        Point first=new Point();
        Point sec=new Point();
        Point third=new Point();
        Point fourth=new Point();
        PA=centerPoints.get(0);
        PB=centerPoints.get(0);
        PC=centerPoints.get(0);
        PD=centerPoints.get(0);

        if(PA.x==PB.x)
        {
            if(PA.x<PC.x)
                if(PA.y<PB.y)
                {
                    first=PA;
                    sec=PB;
                    if(PC.y<PD.y)
                    {
                        third=PC;
                        fourth=PD;
                    }
                    else {
                        third=PD;
                        fourth=PC;
                    }
                }
            else{
                    third=PA;
                    fourth=PB;
                    if(PC.y<PD.y)
                    {
                        first=PC;
                        sec=PD;
                    }
                    else {
                        first=PD;
                        sec=PC;
                    }
                }
        }
        else  if(PA.x==PC.x)
        {

            if(PA.x<PB.x)
                if(PA.y<PC.y)
                {
                    first=PA;
                    sec=PC;
                    if(PC.y<PD.y)
                    {
                        third=PC;
                        fourth=PD;
                    }
                    else {
                        third=PD;
                        fourth=PC;
                    }
                }
                else{
                    third=PA;
                    fourth=PB;
                    if(PC.y<PD.y)
                    {
                        first=PC;
                        sec=PD;
                    }
                    else {
                        first=PD;
                        sec=PC;
                    }
                }
        }
        else if (PA.x==PD.x)
        {
            if(PA.x<PC.x)
                if(PA.y<PB.y)
                {
                    first=PA;
                    sec=PB;
                    if(PC.y<PD.y)
                    {
                        third=PC;
                        fourth=PD;
                    }
                    else {
                        third=PD;
                        fourth=PC;
                    }
                }
                else{
                    third=PA;
                    fourth=PB;
                    if(PC.y<PD.y)
                    {
                        first=PC;
                        sec=PD;
                    }
                    else {
                        first=PD;
                        sec=PC;
                    }
                }
        }
    }


    public ArrayList imageprocessing(Bitmap bitmap){

        orig=bitmap;

            circledetect();
            colorRecognition();
            return colors;

    }



    public boolean checkforStart(Bitmap orig) {
        // Load input image
        Mat bgr_image = new Mat();
        Utils.bitmapToMat(orig, bgr_image);

        Mat orig_image = bgr_image.clone();

        Imgproc.medianBlur(bgr_image, bgr_image, 3);

        // Convert input image to HSV
        Mat hsv_image = new Mat();
        Imgproc.cvtColor(bgr_image, hsv_image, Imgproc.COLOR_BGR2HSV);


        // Threshold the HSV image, keep only the red pixels
        Mat lower_red_hue_range = new Mat();
        Mat upper_red_hue_range = new Mat();
        Core.inRange(hsv_image, new Scalar(110, 50, 50), new Scalar(130, 255, 255), lower_red_hue_range);
        Core.inRange(hsv_image, new Scalar(160, 100, 100), new Scalar(179, 255, 255), upper_red_hue_range);

        // Combine the above two images
        Mat red_hue_image = new Mat();
        Core.addWeighted(lower_red_hue_range, 1.0, upper_red_hue_range, 1.0, 0.0, red_hue_image);

        Imgproc.GaussianBlur(red_hue_image, red_hue_image, new Size(9, 9), 2, 2);

        //       Use the Hough transform to detect circles in the combined threshold image


        Mat circles = new Mat();
        Imgproc.HoughCircles(red_hue_image, circles, Imgproc.CV_HOUGH_GRADIENT, 1, red_hue_image.rows() / 8, 100, 20, 200, 0);

        // Loop over all detected circles and outline them on the original image

        for (int current_circle = 0; current_circle < circles.cols(); ++current_circle) {
            double[] vCircle = circles.get(0, current_circle);
            double x = vCircle[0];
            double y = vCircle[1];
            double radius = vCircle[2];
            Point point = new Point(x, y);
            centerPoints.add(point);


            Imgproc.circle(orig_image, point, (int) radius, new Scalar(0, 255, 0), 30);


        }
        if(centerPoints.size()==1)
            return true;
        else return false;
    }

    public boolean checkforEnd(){
        return endflag;
    }

}
