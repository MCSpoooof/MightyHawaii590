package org.firstinspires.ftc.teamcode.GearsOfFire516;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

//Classify program as autonomous
@Autonomous(name = "DemoAuto")

//Establishing your class
public class DemoA extends LinearOpMode {

    //Needed for time based auto
    private ElapsedTime runtime = new ElapsedTime();

    //Call on hardware class
    DemoHardware robot = DemoHardware.getInstance();

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
        robot.demoWheel1.setTargetPosition((int) Math.round(ticks));
        robot.demoWheel2.setTargetPosition((int) Math.round(ticks));

        //Rest encoders to prevent old values from being used
        robot.demoWheel1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.demoWheel2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set speed to robot
        robot.setPower(speedMoving, speedMoving);

        //While loop keeps robot moving until it is "near" the target
        while (opModeIsActive() && (robot.demoWheel1.getCurrentPosition() + 10 < ticks || robot.demoWheel2.getCurrentPosition() - 10 > ticks)) {

        }

        //Stops robot after while loop otherwise robot will keep going
        robot.setPower(0,0);

        //Wheels set to use encoders
        robot.demoWheel1.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        robot.demoWheel2.setMode((DcMotor.RunMode.RUN_USING_ENCODER));

    }

}
