package org.firstinspires.ftc.teamcode.subsystems;




import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class UnfoldSub {
    private ElapsedTime unfoldtimer = new ElapsedTime();
    DcMotorEx unfold;
    int d = 0;

    public UnfoldSub(HardwareMap hardwareMap) {
        unfold = hardwareMap.get(DcMotorEx.class, "unfold");
        unfold.setDirection(DcMotorSimple.Direction.REVERSE);
        unfold.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        unfold.setTargetPosition(ValuesSub.UnfoldTicks);
        unfold.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

    public void unfold() {
        if (d == 0) {
            unfold.setPower(ValuesSub.UnfoldPower);
            d = 1;
        }
        if(unfoldtimer.seconds() > 4.0){
            unfold.setPower(0.0);
            d = 2;
        }
    }
}

