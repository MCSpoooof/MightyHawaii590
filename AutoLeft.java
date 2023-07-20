package org.firstinspires.ftc.teamcode.MightyHawaii590;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

//Classify program as autonomous
@Autonomous(name = "AutoLeft")

//Establishing your class
public class AutoLeft extends LinearOpMode {

     boolean BlueRed = false;
     boolean foundColor = false;

     String setColor = "blue";

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

        move(-10,0.5);
        sleep(1000);
           if (robot.Color.red() > robot.Color.blue() && !foundColor) {
                foundColor = true;
                setColor = "red";

            } else if (robot.Color.blue() > robot.Color.red() && !foundColor){
                foundColor = true;
                setColor = "blue";
            }

        if (setColor.equals("blue")) {
            move(-147, 1);
            telemetry.addData("blue", "blue");
            runtime.reset();
            while (runtime.seconds() < 1.2) {
                robot.setPower(-0.5, 0.5);
            }
            runtime.reset();
            move(-60,1);
            while (runtime.seconds() < 1.5) {
                robot.Lift.setPower(0.5);
            }
            robot.Lift.setPower(0);
            sleep(5000);
        }

        else if (setColor.equals("red")) {
            telemetry.addData("red", "red");
            move(-147, 1);
            runtime.reset();
                while (runtime.seconds() < 1.5) {
                    robot.Lift.setPower(0.5);
                }
                    robot.Lift.setPower(0);
                    sleep(5000);

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


        //Time Based


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
