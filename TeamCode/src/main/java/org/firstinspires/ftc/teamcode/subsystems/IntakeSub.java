package org.firstinspires.ftc.teamcode.subsystems;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeSub {
    DcMotorEx intakeout;
    DcMotorEx intakein;
    Servo ramp;

    public IntakeSub(HardwareMap hardwareMap){
        intakeout = hardwareMap.get(DcMotorEx.class ,"intakeout");
        intakein = hardwareMap.get(DcMotorEx.class , "intakein");
        ramp = hardwareMap.get(Servo.class , "ramp");

        intakeout.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        intakeout.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        intakein.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        intakein.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }
    public void intake(boolean Intake){
        double IntakeRPM = (ValuesSub.TargetIntake * ValuesSub.TicksPerRev)/60;
        if(Intake){
            intakeout.setVelocity(IntakeRPM);
            intakein.setVelocity(IntakeRPM);
        }
        else{
            intakeout.setPower(0);
            intakein.setPower(0);
        }

    }
    public void ramp(boolean Ramp){
        if(Ramp){
            ramp.setPosition(ValuesSub.RampOut);
        }
        else{
            ramp.setPosition(ValuesSub.RampIn);
        }
    }
    public DcMotorEx getMotorIn() {
        return intakein;
    }
    public DcMotorEx getMotorOut() {
        return intakeout;
    }

}
