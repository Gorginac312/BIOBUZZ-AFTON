package org.firstinspires.ftc.teamcode.teleops;
import org.firstinspires.ftc.teamcode.processors.CoordinateProcessor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;

import org.firstinspires.ftc.teamcode.processors.YellowProcessor;
import org.opencv.core.Point;

@TeleOp(name = "Coordinate Test")
public class CoordinateTest extends LinearOpMode{
    @Override
    public void runOpMode() throws InterruptedException {
        CoordinateProcessor processor = new CoordinateProcessor();
        VisionPortal portal = VisionPortal.easyCreateWithDefaults(
                hardwareMap.get(WebcamName.class, "Webcam 1"),
                processor
        );
        telemetry.update();
        waitForStart();

        try {
            while(opModeIsActive()) {
                Point yellow = processor.getYellowCenter();
                if(yellow != null) {
                    telemetry.addData("Yellow X", yellow.x);
                    telemetry.addData("Yellow Y", yellow.y);
                }
                else{
                    telemetry.addLine("Yellow not detected");
                }
                telemetry.update();
                sleep(50);
            }
        }   finally {
            portal.close();
        }
    }
}
