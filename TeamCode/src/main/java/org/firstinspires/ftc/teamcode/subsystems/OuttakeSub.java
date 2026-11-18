package org.firstinspires.ftc.teamcode.subsystems;


import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
public class OuttakeSub {
    DcMotor outtake;
    Servo block;

    public OuttakeSub(HardwareMap hardwareMap){
        outtake = hardwareMap.get(DcMotor.class ,"outtake");
        block = hardwareMap.get(Servo.class , "block");

        outtake.setDirection(DcMotorSimple.Direction.REVERSE);
        block.setDirection(Servo.Direction.FORWARD);

    }
    public void outtake(boolean out){
        if(out){
            outtake.setPower(1.0);
        }
        else{
            outtake.setPower(0.0);
        }
    }
    public void block(boolean Block){
        if(Block){
            block.setPosition(1.0);
        }
        else{
            block.setPosition(0.0);
        }
    }

}
