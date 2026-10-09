
package org.firstinspires.ftc.teamcode.processors;

import android.graphics.Canvas;

import java.util.List;
import java.util.ArrayList;
import org.opencv.core.MatOfPoint;

import org.firstinspires.ftc.vision.VisionProcessor;
import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.Size;

public class YellowProcessor implements VisionProcessor {

    // Mat objects store images
    private Mat kernel;
    private final Mat hsvImage = new Mat();
    private final Mat yellowMask = new Mat();

    // Latest result, readable from the OpMode
    private volatile double yellowPercentage = 0;

    @Override
    public void init(int width, int height,
                     CameraCalibration calibration) {
        // Called when the camera processor initializes
        kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3));
    }

    @Override
    public Object processFrame(Mat frame, long captureTimeNanos) {

        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();

        // Convert the camera image from RGB to HSV
        Imgproc.cvtColor(frame, hsvImage, Imgproc.COLOR_RGB2HSV);

        // Define the HSV range for yellow
        Scalar lowerYellow = new Scalar(20, 150, 100);
        Scalar upperYellow = new Scalar(35, 255, 255);

        // Create a mask of yellow pixels
        Core.inRange(hsvImage, lowerYellow, upperYellow, yellowMask);
        Imgproc.dilate(yellowMask, yellowMask, kernel);//Imgproc.morphologyEx(yellowMask, yellowMask, Imgproc.MORPH_Close, kernel);
        Imgproc.erode(yellowMask, yellowMask, kernel);
        Imgproc.findContours(
                            yellowMask,
                            contours,
                            hierarchy,
                            Imgproc.RETR_EXTERNAL,
                            Imgproc.CHAIN_APPROX_SIMPLE);
        Imgproc.drawContours(
                            frame,
                            contours,
                            -1,
                            new Scalar(255, 0, 0),
                            6);
        for(MatOfPoint contour : contours) {
                contour.release();
        }
        hierarchy.release();


        // Count how many pixels are yellow
        double yellowPixels = Core.countNonZero(yellowMask);
        double totalPixels = frame.rows() * frame.cols();

        // Calculate the percentage
        yellowPercentage = (yellowPixels / totalPixels) * 100.0;
        //Imgproc.cvtColor(yellowMask, frame, Imgproc.COLOR_GRAY2RGB);

        return null;
    }

    public double getYellowPercentage() {
        return yellowPercentage;
    }

    public boolean isYellowDetected() {
        return yellowPercentage > 2.0;
    }

    @Override
    public void onDrawFrame(Canvas canvas,
                            int onscreenWidth,
                            int onscreenHeight,
                            float scaleBmpPxToCanvasPx,
                            float scaleCanvasDensity,
                            Object userContext) {
        // We'll add camera overlays later
    }
}
