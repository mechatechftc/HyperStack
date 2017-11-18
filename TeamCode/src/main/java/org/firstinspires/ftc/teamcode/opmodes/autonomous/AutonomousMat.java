package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.edinaftc.ninevolt.Config;
import com.edinaftc.ninevolt.Ninevolt;
import com.edinaftc.ninevolt.core.hw.drivetrain.Movement;
import com.edinaftc.ninevolt.util.ExceptionHandling;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.HSRobot;
import org.firstinspires.ftc.teamcode.functions.Elevator;
import org.firstinspires.ftc.teamcode.functions.Gripper;
import org.firstinspires.ftc.teamcode.functions.Tollbooth;
import org.firstinspires.ftc.teamcode.util.StepNotifier;

public abstract class AutonomousMat extends LinearOpMode {
  protected HSRobot robot;
  protected Movement movement;
  protected Gripper gripper;
  protected Tollbooth tollbooth;
  protected Elevator elevator;

  private StepNotifier notifier;

  protected abstract void bumpJewel();
  protected abstract double getYDist();

  @Override
  public void runOpMode() throws InterruptedException {
    try {
      // Initialize robot and variables
      customInit();
      bumpJewel();
      moveToGlyphBox();
      releaseGlyph();
      // Alert user that initialization was successful

      if (Ninevolt.getConfig().minLoggingLevel(Config.LoggingLevel.RECOMMENDED)) {
        telemetry.addData("Initialization", "Done!");
        telemetry.update();
      }

      // Wait until play button is pressed
      waitForStart();
      resetStartTime();

      // Perform autonomous
      gripper.grip();
      elevator.elevate(4);
      notifier.notifyStep();
      sleep(1000);
      moveToGlyphBox();             // Move to cryptoboxes to deposit glyph.
      releaseGlyph();               // Deposit glyph.

    } catch (Exception e) {
      // Stops OpMode and prints exception in case of exception
      ExceptionHandling.standardExceptionHandling(e, this);
    }
  }

  private void customInit() throws Exception {
    robot = new HSRobot(this); // Initializes robot
    // Retrieve variables
    movement = robot.getMovement();
    gripper = robot.getGripper();
    tollbooth = robot.getTollbooth();
    elevator = robot.getElevator();

    notifier = new StepNotifier(new String[] {
        "Elevator up",
        "Tollbooth lowered",
        "Gripper released",
        "Color checked",
        "Bump jewel",
        "Re-align",
        "Raise tollbooth",
        "Move off platform"
    }, this);

    while (!robot.getHardware().imu.isGyroCalibrated() && opModeIsActive()) {
      telemetry.addData("Gyro", "Calibrating");
      telemetry.update();
      idle();
    }
  }

  protected void moveToGlyphBox() {
    try {
      sleep(250);
      movement.yDrive(getYDist(), 0.5f);
      sleep(1000);
      movement.setPowerZero();
    /*sleep(500);
    movement.rotate(-90, 0.2f);
    sleep(1000);
    movement.yDrive(12.0, 0.2f);*/
    }
    catch (Exception e) {
      ExceptionHandling.standardExceptionHandling(e, this);
    }
  }
  private void releaseGlyph() {
    try {
      elevator.elevate(-3);
      gripper.wideRelease();
    }
    catch (Exception e) {
      ExceptionHandling.standardExceptionHandling(e, this);
    }
  }
}
