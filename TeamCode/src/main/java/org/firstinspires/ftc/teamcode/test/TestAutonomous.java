package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.edinaftc.ninevolt.core.hw.Hardware;
import com.edinaftc.ninevolt.core.hw.HardwareBuilder;
import com.edinaftc.ninevolt.core.hw.drivetrain.holonomic.Movement;
import com.edinaftc.ninevolt.util.ExceptionHandling;

/**
 * Created by Richik SC on 9/23/2017.
 */

@Autonomous(name = "Test the Autonomous", group = "test")
public class TestAutonomous extends LinearOpMode {
  private static final int PULSES_PER_MOTOR_REV = 28;
  private static final double DRIVE_GEAR_REDUCTION = 40.0;
  private static final double WHEEL_DIAMETER_INCHES = 4;
  private static final double     PULSES_PER_INCH         = (PULSES_PER_MOTOR_REV *
      DRIVE_GEAR_REDUCTION) /
      (WHEEL_DIAMETER_INCHES * 3.1415);

  private Hardware hardware;
  private Movement movement;

  private void custom_init() throws Exception {

    // Create Bosch IMU parameters
    BNO055IMU.Parameters imuParameters = new BNO055IMU.Parameters();
    imuParameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
    imuParameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
    imuParameters.loggingEnabled = true;
    imuParameters.loggingTag = "IMU";
    imuParameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

    // Create Ninevolt hardware using HardwareBuilder
    HardwareBuilder hb = new HardwareBuilder(hardwareMap);
    hb.setMotorConfig(Hardware.MotorMode.HOLONOMIC, DcMotor.Direction.FORWARD)
        .addMotorFL("motor_fl").addMotorFR("motor_fr")
        .addMotorBL("motor_bl").addMotorBR("motor_br")
        .addBoschIMU("imu", imuParameters);
    this.hardware = hb.build();
    hb = null;
    hardware.init();

    movement = new Movement(hardware, this, PULSES_PER_INCH);
    movement.setVerbose(true);
  }

  @Override
  public void runOpMode() throws InterruptedException {
    try {
      custom_init();
      waitForStart();
      if(opModeIsActive()) {
        movement.yDrive(48);
        movement.xDrive(-48);
        movement.yDrive(-48);
        movement.xDrive(48);
        movement.driveUsingGyro(5000, 0.5f);
        movement.driveUsingGyro(10000, 0, 90);
      }
    } catch (Exception e) {
      ExceptionHandling.standardExceptionHandling(e, this);
      if (e instanceof InterruptedException) {
        throw (InterruptedException)e;
      }
    }

  }
}