package org.firstinspires.ftc.teamcode.GearsOfFire516;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

//See Autonomous program for more in depth comments
@TeleOp(name = "TeleOp")
public class DemoT extends LinearOpMode {


    private ElapsedTime runtime = new ElapsedTime();
    DemoHardware robot = DemoHardware.getInstance();

    public void runOpMode() {
        robot.init(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //Turn off encoders in case something gets unplugged
        if (robot.demoWheel1 != null) {
            robot.demoWheel1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
        if (robot.demoWheel2 != null) {
            robot.demoWheel2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }

        DemoHardware.maxSpeed = 1;

        waitForStart();

        //Booleans to ensure that buttons are only counted once when pressed
        boolean pressinga = false;

        while (opModeIsActive()) {

            //Robot directionals
            double movement = gamepad1.left_stick_y;
            double turning = -gamepad1.right_stick_x;

            double left = movement + turning;
            double right = movement - turning;

            double max = Math.max(Math.abs(left), Math.abs(right));
            if (max > 1.0) {
                left /= max;
                right /= max;
            }

            robot.demoWheel1.setPower(left);
            robot.demoWheel2.setPower(right);

            //Gamepad button example
            if ((gamepad1.a && !pressinga)){
                //Enter whatever function you want here
                pressinga = true;
            } else if (!gamepad1.a) {
                pressinga = false;
            }

        }

    }

}
