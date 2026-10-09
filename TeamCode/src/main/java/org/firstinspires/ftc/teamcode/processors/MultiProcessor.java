
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

public class MultiProcessor implements VisionProcessor {

    // Mat objects store images
    private Mat kernel;
    private final Mat hsvImage = new Mat();
    private final Mat yellowMask = new Mat();
    private final Mat blueMask = new Mat();
    private final Mat redMask = new Mat();


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

        List<MatOfPoint> yellowContours = new ArrayList<>();
        List<MatOfPoint> blueContours = new ArrayList<>();
        List<MatOfPoint> redContours = new ArrayList<>();

        Mat hierarchy = new Mat();

        // Convert the camera image from RGB to HSV
        Imgproc.cvtColor(frame, hsvImage, Imgproc.COLOR_RGB2HSV);

        // Define the HSV range for yellow
        Scalar lowerYellow = new Scalar(15, 100, 80);
        Scalar upperYellow = new Scalar(35, 255, 255);

        Scalar lowerRed = new Scalar(150, 100, 40);
        Scalar upperRed = new Scalar(200, 255, 255);

        Scalar lowerBlue = new Scalar(100, 100, 80);
        Scalar upperBlue = new Scalar(130, 255, 255);

        // Create a mask of yellow pixels
        Core.inRange(hsvImage, lowerYellow, upperYellow, yellowMask);
        Core.inRange(hsvImage, lowerBlue, upperBlue, blueMask);
        Core.inRange(hsvImage, lowerRed, upperRed, redMask);

        Imgproc.morphologyEx(yellowMask, yellowMask, Imgproc.MORPH_CLOSE, kernel);
        Imgproc.morphologyEx(blueMask, blueMask, Imgproc.MORPH_CLOSE, kernel);
        Imgproc.morphologyEx(redMask, redMask, Imgproc.MORPH_CLOSE, kernel);

        Imgproc.findContours(
                yellowMask,
                yellowContours,
                hierarchy,
                Imgproc.RETR_EXTERNAL,
                Imgproc.CHAIN_APPROX_SIMPLE);
        Imgproc.findContours(
                blueMask,
                blueContours,
                hierarchy,
                Imgproc.RETR_EXTERNAL,
                Imgproc.CHAIN_APPROX_SIMPLE);
        Imgproc.findContours(
                redMask,
                redContours,
                hierarchy,
                Imgproc.RETR_EXTERNAL,
                Imgproc.CHAIN_APPROX_SIMPLE);

        Imgproc.drawContours(
                frame,
                yellowContours,
                -1,
                new Scalar(255, 0, 0),
                2);
        Imgproc.drawContours(
                frame,
                blueContours,
                -1,
                new Scalar(0, 255, 0),
                2);
        Imgproc.drawContours(
                frame,
                redContours,
                -1,
                new Scalar(0, 0, 255),
                2);

        for(MatOfPoint contour : yellowContours) {
            contour.release();
        }
        for(MatOfPoint contour : blueContours) {
            contour.release();
        }
        for(MatOfPoint contour : redContours) {
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
