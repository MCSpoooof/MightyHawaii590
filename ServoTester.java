package org.firstinspires.ftc.teamcode.MightyHawaii590;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.robot.Robot;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import java.util.concurrent.locks.Lock;

@TeleOp(name = "ServoTester")
public class ServoTester extends LinearOpMode {


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

        double positionLeft = 0;
        double positionRight = 0;

        boolean pressingA = false;
        boolean pressingB = false;
        boolean pressingX = false;
        boolean pressingY = false;

        while (opModeIsActive()) {

            if (gamepad1.a && !pressingA){
                positionLeft += 0.05;
                pressingA = true;
            }
            else if (!gamepad1.a) {
                pressingA = false;
            }

            if (gamepad1.b && !pressingB){
                positionLeft -= 0.05;
                pressingB = true;
            }
            else if (!gamepad1.b) {
                pressingB  = false;
            }

            robot.LeftClaw.setPosition(positionLeft);
            telemetry.addData("Left Position", positionLeft);



            if (gamepad1.x && !pressingX){
                positionRight += 0.05;
                pressingX = true;
            }
            else if (!gamepad1.x) {
                pressingX = false;
            }

            if (gamepad1.y && !pressingY){
                positionRight -= 0.05;
                pressingY = true;
            }
            else if (!gamepad1.y) {
                pressingY  = false;
            }

            robot.RightClaw.setPosition(positionRight);
            telemetry.addData("Right Position", positionRight);
            telemetry.update();

        }
        }
    }

