
package org.firstinspires.ftc.teamcode.processors;

import android.graphics.Canvas;

import org.firstinspires.ftc.vision.VisionProcessor;
import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class YellowProcessor implements VisionProcessor {

    // Mat objects store images
    private final Mat hsvImage = new Mat();
    private final Mat yellowMask = new Mat();

    // Latest result, readable from the OpMode
    private volatile double yellowPercentage = 0;

    @Override
    public void init(int width, int height,
                     CameraCalibration calibration) {
        // Called when the camera processor initializes
    }

    @Override
    public Object processFrame(Mat frame, long captureTimeNanos) {

        // Convert the camera image from RGB to HSV
        Imgproc.cvtColor(frame, hsvImage, Imgproc.COLOR_RGB2HSV);

        // Define the HSV range for yellow
        Scalar lowerYellow = new Scalar(20, 100, 100);
        Scalar upperYellow = new Scalar(35, 255, 255);

        // Create a mask of yellow pixels
        Core.inRange(hsvImage, lowerYellow, upperYellow, yellowMask);

        // Count how many pixels are yellow
        double yellowPixels = Core.countNonZero(yellowMask);
        double totalPixels = frame.rows() * frame.cols();

        // Calculate the percentage
        yellowPercentage = (yellowPixels / totalPixels) * 100.0;

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
