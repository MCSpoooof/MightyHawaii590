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

        waitForStart();

        //Booleans to ensure that buttons are only counted once when pressed
        boolean pressinga = false;

        while (opModeIsActive()) {

            //Robot directionals
            double leftPower;
            double rightPower;

            double turn = -gamepad1.left_stick_y;
            double drive = gamepad1.right_stick_x;
            leftPower = Range.clip(drive + turn, -1.0, 1.0);
            rightPower = Range.clip(drive - turn, -1.0, 1.0);

            robot.Left.setPower(leftPower);
            robot.Right.setPower(rightPower);


            //Gamepad buttons

            double gamepadA = 1;

            if ((gamepad1.a && !pressinga && gamepadA = 1)) {
                robot.Lock.setPosition(-0.3);
                //Enter whatever function you want here
                pressinga = true;
            } else if (!gamepad1.a) {
                pressinga = false;
            }

        }

    }
}

