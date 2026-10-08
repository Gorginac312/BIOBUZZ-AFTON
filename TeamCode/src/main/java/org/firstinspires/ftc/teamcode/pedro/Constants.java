package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.algorithm.Foresight;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.controllers.Controller;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.pedropathing.revhub.localizers.PinpointLocalizer;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {

    // ============================================================
    // DRIVETRAIN
    // ============================================================
    public static MecanumConfig drivetrainConfig = new MecanumConfig(c -> {
        c.frontLeftName.set("LF");
        c.frontRightName.set("RF");
        c.backLeftName.set("LB");
        c.backRightName.set("RB");

        c.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.frontRightDirection.set(DcMotorSimple.Direction.FORWARD);
        c.backLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.backRightDirection.set(DcMotorSimple.Direction.FORWARD);

        c.manualBrakeMode.set(true);
    });




    // ============================================================
    // PINPOINT LOCALIZER
    // ============================================================

    public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
        c.name.set("pinpoint");
        c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_SWINGARM_POD);
        c.xPodOffset.set(-7.610430379552166);
        c.yPodOffset.set(-9.355023001122662);
        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.globalDistanceUnit.set(DistanceUnit.INCH);
        c.offsetUnits.set(DistanceUnit.INCH);
    });


    // ============================================================
    // FORESIGHT
    // ============================================================

    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.4061517491690154);
                Controller secondaryTranslationalForward = Controller.proportional(0.15006221874333867);
                Controller primaryTranslationalLateral = Controller.proportional(1.1269074375341808);
                Controller secondaryTranslationalLateral = Controller.proportional(0.416362186647577);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.01520275737204015));
                c.brake.set(Controller.proportionalFeedforward(0.012922343766234128));

                c.headingFeedback.set(Controller.proportional(2.0));
                c.headingDriveRatio.set(0.5);
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.0552859003281973, 0.007904482042306782));

                c.linearBrakeCoefficients.set(Matrix.diag(0.11449211521263518, 0.06493901295889037));
                c.quadraticBrakeCoefficients.set(Matrix.diag(0.0013589203324663964, 0.0019582339924373864));

                c.maxAchievableForwardVelocity.set(67.38394749099497);
                c.maxAchievableStrafeVelocity.set(44.90387775824046);
                c.naturalForwardDeceleration.set(30.040420709568213);
                c.naturalStrafeDeceleration.set(71.49423506026613);
            }
    );


    // ============================================================
    // FOLLOWER CREATION
    // ============================================================

    public static Follower create(HardwareMap h) {

        return new Follower(

                new PinpointLocalizer(
                        h,
                        localizerConfig
                ),

                new Mecanum(
                        h,
                        drivetrainConfig
                ),

                new Foresight(
                        foresightConfig
                )
        );
    }
}