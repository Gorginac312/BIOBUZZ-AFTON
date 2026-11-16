package org.firstinspires.ftc.teamcode.subsystems;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
public class OuttakeSub {
    DcMotor outtake;
    Servo block;

    public OuttakeSub(HardwareMap hardwareMap){
        outtake = hardwareMap.get(DcMotor.class ,"outtake");
        block = hardwareMap.get(Servo.class , "block");

    }
    public void outtake(boolean out){
        if(out){
            outtake.setPower(0.67);
        }
        else{
            outtake.setPower(0.0);
        }
    }
    public void block(boolean Block){
        if(Block){
            block.setPosition(0.5);
        }
        else{
            block.setPosition(0);
        }
    }

}
