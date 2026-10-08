
package org.firstinspires.ftc.teamcode.teleops;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;

import org.firstinspires.ftc.teamcode.processors.YellowProcessor;

@TeleOp(name = "Yellow Vision Test", group = "Vision")
public class VisionTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        // Create our custom processor
        YellowProcessor processor = new YellowProcessor();

        // Connect it to our USB webcam
        VisionPortal portal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .addProcessor(processor)
                .build();

        try {
            telemetry.addLine("Camera ready!");
            telemetry.update();

            waitForStart();

            while (opModeIsActive()) {

                telemetry.addData("Yellow percentage",
                        "%.2f%%", processor.getYellowPercentage());

                telemetry.addData("Yellow detected",
                        processor.isYellowDetected());

                telemetry.update();
                sleep(50);
            }

        } finally {
            portal.close();
        }
    }
}
