package org.firstinspires.ftc.teamcode.MightyHawaii590;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.robot.Robot;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import java.util.concurrent.locks.Lock;

@TeleOp(name = "MotorTester")
public class MotorTester extends LinearOpMode {


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

        waitForStart();

        int position = 0;

        boolean pressingA = false;
        boolean pressingB = false;

        while (opModeIsActive()) {

            if (gamepad1.a && !pressingA){
                position += 10;
                pressingA = true;
            }
            else if (!gamepad1.a) {
                pressingA = false;
            }

            if (gamepad1.b && !pressingB){
                position -= 10;
                pressingB = true;
            }
            else if (!gamepad1.b) {
                pressingB  = false;
            }

            robot.Lift.setPower(0.5);
            robot.Lift.setTargetPosition(position);
            robot.Lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            telemetry.addData("position", position);
            telemetry.update();

        }
    }
}

