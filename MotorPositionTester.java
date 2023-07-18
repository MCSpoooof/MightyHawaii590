package org.firstinspires.ftc.teamcode.MightyHawaii590;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.robot.Robot;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import java.util.concurrent.locks.Lock;

//See Autonomous program for more in depth comments
@TeleOp(name = "MotorPositionTester")
public class MotorPositionTester extends LinearOpMode {


    private ElapsedTime runtime = new ElapsedTime();
    Hardware robot = Hardware.getInstance();

    public void runOpMode() {
        robot.init(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //Turn off encoders in case something gets unplugged
        if (robot.Left != null) {
            robot.Left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
        if (robot.Right != null) {
            robot.Right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }

        Hardware.maxSpeed = 1;

        robot.Lift.setPower(0.1);
        robot.Lift.setTargetPosition(0);
        robot.Lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        waitForStart();

        while (opModeIsActive()) {

            telemetry.addData("Left Position", robot.Left.getCurrentPosition());
            telemetry.addData("Right Position", robot.Right.getCurrentPosition());
            telemetry.update();

        }
    }
}