package org.firstinspires.ftc.teamcode;

import com.edinaftc.ninevolt.Ninevolt;
import com.edinaftc.ninevolt.core.hw.drivetrain.MecanumMovement;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.edinaftc.ninevolt.core.hw.Hardware;
import com.edinaftc.ninevolt.core.hw.HardwareBuilder;
import com.edinaftc.ninevolt.core.hw.drivetrain.Movement;
import org.firstinspires.ftc.teamcode.functions.Elevator;
import org.firstinspires.ftc.teamcode.functions.Gripper;
import org.firstinspires.ftc.teamcode.functions.Tollbooth;

public class HSRobot {
  private static final int    PULSES_PER_MOTOR_REV  = 28;
  private static final double DRIVE_GEAR_REDUCTION  = 40.0;
  private static final double WHEEL_DIAMETER_INCHES = 4;
  private static final double PULSES_PER_INCH = (PULSES_PER_MOTOR_REV *
      DRIVE_GEAR_REDUCTION) /
      (WHEEL_DIAMETER_INCHES * 3.1415);

  private Hardware hardware;
  private Gripper gripper;
  private Movement movement;
  private Elevator elevator;
  private Tollbooth tollbooth;
  private OpMode ctx;
  private LinearOpMode ctxl;

  public static BNO055IMU.Parameters getIMUParameters() {
    // Create Bosch IMU parameters
    BNO055IMU.Parameters imuParameters = new BNO055IMU.Parameters();
    imuParameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
    imuParameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
    imuParameters.loggingEnabled = true;
    imuParameters.loggingTag = "IMU";
    imuParameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
    imuParameters.mode = BNO055IMU.SensorMode.IMU;
    return imuParameters;
  }

  public HSRobot(OpMode _ctx) throws Exception {
    this.ctx = _ctx;
    Ninevolt.setConfig(HSConfig.getInstance());
    hardware = initializeHardware(ctx.hardwareMap);
    gripper = new Gripper("bigServo", "bottomServo", "topServo", ctx);
    elevator = new Elevator(ctx.hardwareMap.dcMotor.get("elevatorMotor"), ctx);
    tollbooth = initializeTollbooth(ctx);
    movement = new MecanumMovement(hardware, ctx);
  }

  public HSRobot(LinearOpMode _ctxl) throws Exception {
    this.ctxl = _ctxl;
    this.ctx = _ctxl;
    Ninevolt.setConfig(HSConfig.getInstance());
    hardware = initializeHardware(ctxl.hardwareMap);
    movement = new MecanumMovement(hardware, ctxl, PULSES_PER_INCH);
    elevator = new Elevator(ctx.hardwareMap.dcMotor.get("elevatorMotor"), 8.5, ctxl);
    tollbooth = initializeTollbooth(ctx);
    gripper = new Gripper("bigServo", "bottomServo", "topServo", ctxl);
  }

  private Tollbooth initializeTollbooth(OpMode ctx) {
    Tollbooth booth = new Tollbooth("tollboothServo", "colorSensor", ctx);
    booth.raise();
    return booth;
  }

  private Hardware initializeHardware(HardwareMap hardwareMap) throws Exception {
    HardwareBuilder hb = new HardwareBuilder(hardwareMap);
    hb.setMotorDirection(DcMotor.Direction.FORWARD);
    hb
        .addMotorFL("motorFL")
        .addMotorFR("motorFR")
        .addMotorBL("motorBL")
        .addMotorBR("motorBR")
        .addBoschIMU("imu", HSRobot.getIMUParameters());
    hardware = hb.build();
    hb = null;
    hardware.init();
    return hardware;
  }

  public Hardware getHardware() {
    return hardware;
  }

  public Gripper getGripper() {
    return gripper;
  }

  public Movement getMovement() {
    return movement;
  }

  public Tollbooth getTollbooth() { return tollbooth; }

  public Elevator getElevator() {
    return elevator;
  }
}
