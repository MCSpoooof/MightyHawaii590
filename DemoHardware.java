package org.firstinspires.ftc.teamcode.GearsOfFire516;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

//Establishing your class
public class DemoHardware {

    //Define motors, servos, gyro, sensors
    public DcMotor demoWheel1;

    public DcMotor demoWheel2;

    public Servo demoServo;

    public BNO055IMU gyro;

    //Control speed of robot easily by changing this variable
    public static double maxSpeed = 1;

    //Create an instance of your hardware class
    private static DemoHardware myInstance = null;
    public static DemoHardware getInstance() {
        if(myInstance == null) {
            myInstance = new DemoHardware();
        }
        return myInstance;
    }

    //Initialize all hardware
    public void init(HardwareMap hwMap) {

        //Initialize motor
        try {
            demoWheel1 = hwMap.get(DcMotor.class, "demoWheel1");
            demoWheel1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            demoWheel1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            demoWheel1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            demoWheel1.setPower(0);
        }
        catch(Exception p_exception) {
            demoWheel1 = null;
        }

        try {
            demoWheel2 = hwMap.get(DcMotor.class, "demoWheel2");
            demoWheel2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            demoWheel2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            demoWheel2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            demoWheel2.setPower(0);
        }
        catch(Exception p_exception) {
            demoWheel2 = null;
        }

        //Initialize gyro
        try {
            gyro = hwMap.get(BNO055IMU.class, "gyro");
            BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
            parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
            parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
            parameters.loggingEnabled = true;
            parameters.loggingTag = "gyro";
            parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
            gyro.initialize(parameters);
        }
        catch (Exception p_exception) {
            gyro = null;
        }

        //Initialize servo
        try {
            demoServo = hwMap.get(Servo.class, "demoServo");
        } catch(Exception p_exception) {
            demoServo = null;
        }

    }

    //Method to setPower to all wheels at once
    public void setPower(double wheelDemo1, double wheelDemo2) {
        if (demoWheel1 != null) {
            demoWheel1.setPower(Range.clip(wheelDemo1, -maxSpeed, maxSpeed));
        }
        if (demoWheel2 != null) {
            demoWheel2.setPower(Range.clip(wheelDemo2, -maxSpeed, maxSpeed));
        }
    }

    //Method to easily set power to servo
    public void demoServo(double servoDemo) {
        if (demoServo != null) {
            demoServo.setPosition(servoDemo);
        }
    }

}
