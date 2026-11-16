package org.firstinspires.ftc.teamcode.subsystems;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeSub {
    DcMotor IntakeOut;
    DcMotor IntakeIn;
    Servo Ramp;

    public IntakeSub(HardwareMap hardwareMap){
        IntakeOut = hardwareMap.get(DcMotor.class ,"IntakeOut");
        IntakeIn = hardwareMap.get(DcMotor.class , "IntakeIn");
        Ramp = hardwareMap.get(Servo.class , "Ramp");


    }
    public void intake(boolean intake){
        if(intake){
            IntakeOut.setPower(1.0);
            IntakeIn.setPower(1.0);
        }
        else{
            IntakeOut.setPower(0);
            IntakeIn.setPower(0);
        }

    }
    public void ramp(boolean ramp){
        if(ramp){
            Ramp.setPosition(0.5);
        }
        else{
            Ramp.setPosition(0);
        }
    }

}
