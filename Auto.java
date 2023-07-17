package org.firstinspires.ftc.teamcode.MightyHawaii590;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

//Classify program as autonomous
@Autonomous(name = "Auto")

//Establishing your class
public class Auto extends LinearOpMode {

    //Needed for time based auto
    private ElapsedTime runtime = new ElapsedTime();

    //Call on hardware class
    Hardware robot = Hardware.getInstance();

    public void runOpMode() {
        //Initialize Hardware
        robot.init(hardwareMap);

        //Example telemetry (prints on driver station)
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        //Calling on Encoder Based
        move(65, 1);

        //Time Based
        runtime.reset();
        while (runtime.seconds() < 3) {
            robot.setPower(1,1);
        }
        runtime.reset();

    }

    //Encoder Based
    public void move(double distanceMoving, double speedMoving) {

        //Math needs to be adjusted based on wheels
        double wheelCircumfrance = 4 * Math.PI;
        double wheelMotor = 560;
        double ticks = (distanceMoving * (wheelMotor / wheelCircumfrance));

        robot.setPower(0,0);

        //Converting ticks to units (inches)
        robot.Left.setTargetPosition((int) Math.round(ticks));
        robot.Right.setTargetPosition((int) Math.round(ticks));

        //Rest encoders to prevent old values from being used
        robot.Left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.Right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set speed to robot
        robot.setPower(speedMoving, speedMoving);

        //While loop keeps robot moving until it is "near" the target
        while (opModeIsActive() && (robot.Left.getCurrentPosition() + 10 < ticks || robot.Right.getCurrentPosition() - 10 > ticks)) {

        }

        //Stops robot after while loop otherwise robot will keep going
        robot.setPower(0,0);

        //Wheels set to use encoders
        robot.Left.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        robot.Right.setMode((DcMotor.RunMode.RUN_USING_ENCODER));

    }

}
