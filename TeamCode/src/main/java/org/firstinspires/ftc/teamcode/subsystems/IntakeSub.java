package org.firstinspires.ftc.teamcode.subsystems;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeSub {
    DcMotor intakeout;
    DcMotor intakein;
    Servo ramp;

    public IntakeSub(HardwareMap hardwareMap){
        intakeout = hardwareMap.get(DcMotor.class ,"intakeout");
        intakein = hardwareMap.get(DcMotor.class , "intakein");
        ramp = hardwareMap.get(Servo.class , "ramp");


    }
    public void intake(boolean Intake){
        if(Intake){
            intakeout.setPower(0.8);
            intakein.setPower(0.8);
        }
        else{
            intakeout.setPower(0);
            intakein.setPower(0);
        }

    }
    public void ramp(boolean Ramp){
        if(Ramp){
            ramp.setPosition(0.1);
        }
        else{
            ramp.setPosition(0);
        }
    }

}
