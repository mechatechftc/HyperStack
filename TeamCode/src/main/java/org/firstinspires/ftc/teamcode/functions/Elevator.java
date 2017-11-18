package org.firstinspires.ftc.teamcode.functions;

import com.edinaftc.ninevolt.Config;
import com.edinaftc.ninevolt.Ninevolt;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Richik SC on 10/3/2017.
 */

public class Elevator {
  private static final int PULSES_PER_MOTOR_REV = 28;
  private static final double DRIVE_GEAR_REDUCTION = 20.0;
  private static final double WHEEL_DIAMETER_INCHES = 1.389;
  private static final double     PULSES_PER_INCH         = (PULSES_PER_MOTOR_REV *
      DRIVE_GEAR_REDUCTION) /
      (WHEEL_DIAMETER_INCHES * 3.1415);

  private OpMode ctx;
  private LinearOpMode ctxl;
  private DcMotor motor;
  private boolean isVerbose;
  private float standardPower = 0.8f;
  private double ppi;
  private int maxHeight;

  public Elevator(DcMotor mainMotor, OpMode ctx) {
    this.motor = mainMotor;
    motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    this.ctx = ctx;
  }
  public Elevator(DcMotor mainMotor, double maxHeight, LinearOpMode ctxl) throws Exception {
    this.motor = mainMotor;
    this.motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    this.ctxl = ctxl;
    this.ctx = ctxl;
    this.ppi = PULSES_PER_INCH;
    this.maxHeight = calculateTargetTicks(maxHeight);
    resetEncoders();
  }

  public void directDrive(double power) {
    if(isVerbose()) {
      ctx.telemetry.addData("elevatorMotorPower", power);
    }
    motor.setPower(Range.clip(power, -1, 1));
  }

  private void elevateLinear(double delta) throws Exception {
    if (ctxl.opModeIsActive()) {
      int ticks = Range.clip(motor.getCurrentPosition() + calculateTargetTicks(delta),
          0, maxHeight);
      motor.setTargetPosition(ticks);

      if(delta > 0)
        directDrive(standardPower);
      else
        directDrive(-1 * standardPower);
      // keep looping while we are still active, and there is time left, and both motors are running.
      while (ctxl.opModeIsActive() && motor.isBusy()) {

        // Display it for the driver.
        if (isVerbose()) {
          ctx.telemetry.addData("Elevator1", "Running to %7d", ticks);
          ctx.telemetry.addData("Path2", "Running at %7d", motor.getCurrentPosition());
          ctx.telemetry.update();
        }


      }
      setPowerZero();
    }
  }

  private void setPowerZero() {
    motor.setPower(0);
  }

  public void elevate(double delta) throws Exception {
    if (ctxl != null) {
      elevateLinear(delta);
    } else {
      throw new Exception("Encoders not supported in non-linear OpModes");
    }
  }

  public boolean isVerbose() {
    return Ninevolt.getConfig().minLoggingLevel(Config.LoggingLevel.VERBOSE);
  }

  public void resetEncoders() throws Exception {
    if(ctxl == null) {
      throw new Exception("Cannot use linear method in NL opmode");
    }
    motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    ctxl.idle();

    while (motor.getCurrentPosition() != 0 && ctxl.opModeIsActive()) {
      ctxl.sleep(500);
    }

    motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    ctxl.idle();

  }

  private int calculateTargetTicks(double targetInches) {
    return (int)Math.round(targetInches * ppi);
  }

  public void stop() {
    setPowerZero();
  }
}
