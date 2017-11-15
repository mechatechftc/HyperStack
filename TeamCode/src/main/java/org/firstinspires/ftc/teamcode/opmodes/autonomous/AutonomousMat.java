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
  private HSRobot robot;
  private Movement movement;
  private Gripper gripper;
  private Tollbooth tollbooth;
  private Elevator elevator;

  private StepNotifier notifier;

  protected abstract double getYDist();
  protected abstract double getSecondRotationAngle();
  protected abstract Tollbooth.JewelColor getAllianceColor();
  protected abstract Tollbooth.JewelColor getOppositionColor();

  @Override
  public void runOpMode() throws InterruptedException {
    try {
      // Initialize robot and variables
      customInit();
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
      bumpJewel();                  // Read jewel color and knock appropriately.
      moveToGlyphBox();             // Move to cryptoboxes to deposit glyph.
//      releaseGlyph();               // Deposit glyph.

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

  private void bumpJewel() throws Exception {
    tollbooth.lower(); // Lower tollbooth arm
    notifier.notifyStep();
    sleep(3000);
    Tollbooth.JewelColor color = tollbooth.checkColor();
    notifier.notifyStep();
    if (color == getOppositionColor()) {
      movement.rotate(15, 0.2f);
      notifier.notifyStep();
      sleep(3000);
      tollbooth.raise();
      sleep(3000);
      movement.rotate(-15, 0.2f);
    } else if (color == getAllianceColor()) {
      movement.rotate(-15, 0.2f);
      notifier.notifyStep();
      sleep(3000);
      tollbooth.raise();
      sleep(3000);
      movement.rotate(15, 0.2f);
    } else {
      telemetry.addLine("Error with color sensor readings");
      telemetry.update();
    }
  }

  private void moveToGlyphBox() throws Exception {
    sleep(250);
    movement.yDrive(getYDist(), 0.5f);
    sleep(1000);
    movement.directDrive(0f,0f,0f);
    /*sleep(500);
    movement.rotate(getSecondRotationDistance(), 0.2f);
    sleep(1000);
    movement.yDrive(12.0, 0.2f);*/
  }

  private void releaseGlyph() {
    gripper.wideRelease();
  }

}
