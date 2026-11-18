package org.firstinspires.ftc.teamcode.subsystems;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.subsystems.ValuesSub;
public class OuttakeSub {
    DcMotorEx outtake;
    Servo block;

    public OuttakeSub(HardwareMap hardwareMap){
        outtake = hardwareMap.get(DcMotorEx.class ,"outtake");
        block = hardwareMap.get(Servo.class , "block");

        outtake.setDirection(DcMotorSimple.Direction.REVERSE);
        block.setDirection(Servo.Direction.FORWARD);

        outtake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        outtake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        outtake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }
    public void outtake(boolean out){
        double OuttakeRPM = (ValuesSub.TargetOuttake * ValuesSub.TicksPerRev)/60;

        if(out){
            outtake.setVelocity(OuttakeRPM);
        }
        else{
            outtake.setVelocity(0.0);
        }
    }
    public void block(boolean Block){
        if(Block){
            block.setPosition(ValuesSub.BlockOut);
        }
        else{
            block.setPosition(ValuesSub.BlockIn);
        }
    }
    public DcMotorEx getMotor() {
        return outtake;
    }

}
