package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;

import org.firstinspires.ftc.teamcode.pedro.Constants;

@Autonomous(name = "Pedro Tune", group = "Tuning")
public class PedroTuneAuto extends LinearOpMode {

    private Follower follower;

    @Override
    public void runOpMode() {

        follower = Constants.create(hardwareMap);

        Pose startPose = new Pose(
                72,
                72,
                Math.toRadians(0)
        );

        follower.setPose(startPose);

        telemetry.addLine("Pedro v3 initialized");
        telemetry.addLine("Ready for tuning");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            follower.update();

            Pose currentPose = follower.pose();

            telemetry.addData("X", currentPose.x());
            telemetry.addData("Y", currentPose.y());
            telemetry.addData(
                    "Heading",
                    Math.toDegrees(currentPose.heading())
            );

            telemetry.update();
        }
    }
}