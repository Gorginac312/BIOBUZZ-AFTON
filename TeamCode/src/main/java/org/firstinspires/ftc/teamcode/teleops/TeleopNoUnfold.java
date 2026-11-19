package org.firstinspires.ftc.teamcode.teleops;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.DriveSub;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSub;
import org.firstinspires.ftc.teamcode.subsystems.OuttakeSub;
import org.firstinspires.ftc.teamcode.subsystems.UnfoldSub;
import org.firstinspires.ftc.teamcode.subsystems.ValuesSub;

@TeleOp(name = "TeleopNoUnfold", group = "Vision")

public class TeleopNoUnfold extends OpMode {
    DriveSub drive;
    IntakeSub intake;
    OuttakeSub outtake;
    UnfoldSub unfold;
    double ticksPerSecondOuttake;
    double currentOuttake;
    double currentIntakeOut;
    double ticksPerSecondIntakeOut;
    double currentIntakeIn;
    double ticksPerSecondIntakeIn;
    @Override
    public void init() {
        drive = new DriveSub(hardwareMap);
        intake = new IntakeSub(hardwareMap);
        outtake = new OuttakeSub(hardwareMap);
        unfold = new UnfoldSub(hardwareMap);
    }

    @Override
    public void loop() {
        drive.drive(-gamepad1.left_stick_y,
                gamepad1.left_stick_x,
                gamepad1.right_stick_x,
                gamepad1.left_bumper);
        intake.intake(gamepad1.left_trigger > 0.5);
        intake.ramp(gamepad1.left_bumper);
        outtake.outtake(gamepad1.right_trigger > 0.5);
        outtake.block(gamepad1.right_bumper);

        ticksPerSecondOuttake = outtake.getMotor().getVelocity();
        ticksPerSecondIntakeIn = intake.getMotorIn().getVelocity();
        ticksPerSecondIntakeOut = intake.getMotorOut().getVelocity();
        currentOuttake = (ticksPerSecondOuttake * 60) / ValuesSub.TicksPerRev;
        currentIntakeIn = (ticksPerSecondIntakeIn * 60) / ValuesSub.TicksPerRev;
        currentIntakeOut = (ticksPerSecondIntakeOut * 60) / ValuesSub.TicksPerRev;
        telemetry.addData("outtake rpm", currentOuttake);
        telemetry.addData("IntakeIn rpm" , currentIntakeIn);
        telemetry.addData("IntakeOut rpm" , currentIntakeOut);

    }
}
