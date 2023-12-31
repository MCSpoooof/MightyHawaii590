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
@TeleOp(name = "TeleOp")
public class Tele extends LinearOpMode {


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

        robot.Lift.setPower(0.15);
        robot.Lift.setTargetPosition(0);
        robot.Lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);



        waitForStart();

        //Booleans to ensure that buttons are only counted once when pressed
        boolean pressingA = false;
        boolean gamepadA = false;

        boolean pressinglb = false;
        boolean gamepadlb = false;

        boolean pressinglt = false;
        boolean gamepadlt = false;

        while (opModeIsActive()) {

            //Robot directionals
            double leftPower;
            double rightPower;

            double drive = gamepad1.left_stick_y;
            double turn = -gamepad1.right_stick_x;
            leftPower = Range.clip(drive + turn, -1.0, 1.0);
            rightPower = Range.clip(drive - turn, -1.0, 1.0);

            robot.Left.setPower(leftPower);
            robot.Right.setPower(rightPower);


            //Gamepad buttons

            if ((gamepad1.a && !pressingA && gamepadA == false)) {
                robot.Lock.setPosition(0.78);
                gamepadA = true;
                pressingA = true;
            } else if ((gamepad1.a && !pressingA && gamepadA == true)) {
                robot.Lock.setPosition(0.40);
                gamepadA = false;
                pressingA = true;
            } else if (!gamepad1.a) {
                pressingA = false;
            }


            if ((gamepad1.right_bumper || gamepad1.left_bumper && !pressinglb && gamepadlb == false)) {
                robot.LeftClaw.setPosition(0.5);
                robot.RightClaw.setPosition(0.3);
                gamepadlb = true;
                pressinglb = true;
            } else if ((gamepad1.right_bumper && !pressinglb && gamepadlb == true)) {
                robot.LeftClaw.setPosition(0.65);
                robot.RightClaw.setPosition(0.17);
                gamepadlb = false;
                pressinglb = true;
            } else if (!gamepad1.right_bumper) {
                pressinglb = false;
            }

            if (((gamepad1.right_trigger > 0.1) && !pressinglt && gamepadlt == false)) {
                robot.Lift.setTargetPosition(-270);
                gamepadlt = true;
                pressinglt = true;
            } else if (((gamepad1.right_trigger > 0.1) && !pressinglt && gamepadlt == true)) {
                robot.Lift.setTargetPosition(0);
                gamepadlt = false;
                pressinglt = true;
            } else if (!(gamepad1.right_trigger > 0.1)) {
                pressinglt = false;
            }

        }
    }
}

