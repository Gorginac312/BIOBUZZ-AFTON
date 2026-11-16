package org.firstinspires.ftc.teamcode.subsystems;



import static android.os.SystemClock.sleep;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class UnfoldSub {
    DcMotor unfold;
    public UnfoldSub(HardwareMap hardwareMap){
        unfold = hardwareMap.get(DcMotor.class , "unfold");
    }
    public void unfold(boolean Unfold){
        if(Unfold){
            unfold.setPower(0.1);
            sleep(60);
            unfold.setPower(0);
        }
    }
}
