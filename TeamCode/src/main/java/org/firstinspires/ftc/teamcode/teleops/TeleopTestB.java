package org.firstinspires.ftc.teamcode.teleops;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.subsystems.DriveSub;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSub;
import org.firstinspires.ftc.teamcode.subsystems.OuttakeSub;

public class TeleopTestB extends OpMode {
    DriveSub drive;
    IntakeSub intake;
    OuttakeSub outtake;
    @Override
    public void init() {
    drive = new DriveSub(hardwareMap);
    intake = new IntakeSub(hardwareMap);
    outtake = new OuttakeSub(hardwareMap);
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
    }
}
