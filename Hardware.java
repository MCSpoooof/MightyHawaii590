package org.firstinspires.ftc.teamcode.MightyHawaii590;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

//Establishing your class
public class Hardware {

    //Define motors, servos, gyro, sensors
    public DcMotor Left;

    public DcMotor Right;

    public DcMotor Lift;

    public Servo Lock;

    public Servo LeftClaw;

    public Servo RightClaw;

    public BNO055IMU Gyro;

    public RevColorSensorV3 Color;

    //Control speed of robot easily by changing this variable
    public static double maxSpeed = 1;

    //Create an instance of your hardware class
    private static Hardware myInstance = null;
    public static Hardware getInstance() {
        if(myInstance == null) {
            myInstance = new Hardware();
        }
        return myInstance;
    }

    //Initialize all hardware
    public void init(HardwareMap hwMap) {

        //Initialize motor
        try {
            Left = hwMap.get(DcMotor.class, "Left");
            Left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            Left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            Left.setPower(0);
        }
        catch(Exception p_exception) {
            Left = null;
        }

        try {
            Right = hwMap.get(DcMotor.class, "Right");
            Right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            Right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            Right.setDirection(DcMotorSimple.Direction.REVERSE);
            Right.setPower(0);
        }
        catch(Exception p_exception) {
            Right = null;
        }

        try {
            Lift = hwMap.get(DcMotor.class, "Lift");
            Lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            Lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            Lift.setPower(0);
        }
        catch(Exception p_exception) {
            Left = null;
        }

        //Initialize gyro
        try {
            Gyro = hwMap.get(BNO055IMU.class, "Gyro");
            BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
            parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
            parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
            parameters.loggingEnabled = true;
            parameters.loggingTag = "Gyro";
            parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
            Gyro.initialize(parameters);
        }
        catch (Exception p_exception) {
            Gyro = null;
        }

        //Initialize servo
        try {
            Lock = hwMap.get(Servo.class, "Lock");
        } catch(Exception p_exception) {
            Lock = null;
        }

        try {
            LeftClaw = hwMap.get(Servo.class, "LeftClaw");
        } catch(Exception p_exception) {
            LeftClaw = null;
        }

        try {
            RightClaw = hwMap.get(Servo.class, "RightClaw");
        } catch(Exception p_exception) {
            RightClaw = null;
        }

        try {
            Color = hwMap.get(RevColorSensorV3.class, "Color");
        } catch (Exception p_exception) {
        }
    }


    //Method to setPower to all wheels at once
    public void setPower(double left, double right) {
        if (Left != null) {
            Left.setPower(Range.clip(left, -maxSpeed, maxSpeed));
        }
        if (Right != null) {
            Right.setPower(Range.clip(right, -maxSpeed, maxSpeed));
        }
    }

    //Method to easily set position to servo
    public void setPosition(double servo) {
        if (Lock != null) {
            Lock.setPosition(servo);
        }
    }

}
