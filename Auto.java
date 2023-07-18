package org.firstinspires.ftc.teamcode.MightyHawaii590;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

//Classify program as autonomous
@Autonomous(name = "Auto")

//Establishing your class
public class Auto extends LinearOpMode {

     boolean BlueRed = false;
     boolean foundColor = false;

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

        while (!isStarted() && !isStopRequested()) {
            telemetry.addData("Red", robot.Color.red());
            telemetry.addData("Blue", robot.Color.blue());
            telemetry.update();
        }




        waitForStart();

        move(-8,0.5);
        if foundColor = false {
           if (robot.Color.red() > ) {
                move(-150, 0.5);
                foundColor = true;
            } if else (robot.Color.blue() > ){
                move(-150, 0.5);
                foundColor = true;
            }
        }
        //Calling on Encoder Based


        /*robot.Left.setTargetPosition(-529);
        robot.Right.setTargetPosition(-549);
        robot.Left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.Right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.Left.setPower(-0.5);
        robot.Right.setPower(-0.5);

        robot.Left.setTargetPosition(-3982);
        robot.Right.setTargetPosition(-4036);
        robot.Left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.Right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.Left.setPower(-0.5);
        robot.Right.setPower(-0.5);

        robot.Left.setTargetPosition(-6446);
        robot.Right.setTargetPosition(-6556);
        robot.Left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.Right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.Left.setPower(-0.5);
        robot.Right.setPower(-0.5);

        robot.Left.setTargetPosition(-8106);
        robot.Right.setTargetPosition(-6556);
        robot.Left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.Right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.Left.setPower(-0.5);
        robot.Right.setPower(-0.5);

        robot.Left.setTargetPosition(-9879);
        robot.Right.setTargetPosition(-8280);
        robot.Left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.Right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.Left.setPower(-0.5);
        robot.Right.setPower(-0.5);*/


        //Reading the color

        if (robot.Color.blue() > robot.Color.red()) {
            BlueRed = false;
        }
        else {
            BlueRed = true;
        }

        //Time Based
        /*runtime.reset();
        while (runtime.seconds() < 3) {
            robot.setPower(1, 1);
        }
        runtime.reset();*/

    }

    //Encoder Based
    public void move(double distanceMoving, double speedMoving) {

        //Math needs to be adjusted based on wheels
        double wheelCircumfrance = 3.5 * Math.PI;
        double wheelMotor = 537.7;
        double ticks = (distanceMoving * (wheelMotor / wheelCircumfrance));

        robot.setPower(0, 0);

        //Converting ticks to units (inches)
        robot.Left.setTargetPosition((int) Math.round(ticks));
        robot.Right.setTargetPosition((int) Math.round(ticks));

        //Rest encoders to prevent old values from being used
        robot.Left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.Right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.Left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.Right.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //Set speed to robot
        robot.setPower(speedMoving, speedMoving);

        //While loop keeps robot moving until it is "near" the target
        while (opModeIsActive() && (robot.Left.getCurrentPosition() + 10 < ticks || robot.Right.getCurrentPosition() - 10 > ticks)) {

        }

        //Stops robot after while loop otherwise robot will keep going
        robot.setPower(0, 0);

        //Wheels set to use encoders
        robot.Left.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        robot.Right.setMode((DcMotor.RunMode.RUN_USING_ENCODER));


        ;


    }
}
