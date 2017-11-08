package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.edinaftc.ninevolt.core.hw.drivetrain.mecanum.Movement;
import com.edinaftc.ninevolt.util.ExceptionHandling;
import org.firstinspires.ftc.teamcode.HSRobot;
import org.firstinspires.ftc.teamcode.functions.Elevator;
import org.firstinspires.ftc.teamcode.functions.Gripper;
import org.firstinspires.ftc.teamcode.functions.Tollbooth;
import org.firstinspires.ftc.teamcode.util.StepNotifier;

@Autonomous(name = "Main Autonomous", group = "real")
public class AutonomousMain extends LinearOpMode {
  private HSRobot robot;
  private Movement movement;
  private Gripper gripper;
  private Elevator elevator;
  private Tollbooth tollbooth;

  private StepNotifier notifier;

  @Override
  public void runOpMode() throws InterruptedException {
    try {
      // Initialize robot and variables
      customInit();
      // Alert user that initialization was successful
      telemetry.addData("Initialization", "Done!");
      telemetry.update();

      // Wait until play button is pressed
      waitForStart();
      resetStartTime();

      // Perform autonomous
      initGrip(9.5);                // Grip block without knocking it off.
      bumpJewel();                  // Read jewel color and knock appropriately.
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
    elevator = robot.getElevator();
    tollbooth = robot.getTollbooth();

    notifier = new StepNotifier(new String[] {
        "Elevator up",
        "Tollbooth lowered",
        "Gripper released",
        "Color checked",
        "Elevator down",
        "Gripper grip",
        "Bump jewel",
        "Raise tollbooth",
        "Move off platform"
    }, this);
  }

  private void initGrip(double delta) throws Exception {
    elevator.elevate(delta);
    notifier.notifyStep();
    gripper.wideRelease();
    notifier.notify();
    elevator.elevate(-delta);
    notifier.notifyStep();
    gripper.grip();
    notifier.notifyStep();
  }

  private void bumpJewel() {
    tollbooth.lower(); // Lower tollbooth arm
    if (tollbooth.checkColor() == Tollbooth.JewelColor.BLUE) {
      double startTime = getRuntime();
      while(getRuntime() < startTime + 1) {
          movement.directDrive(0, 0, 0.2f);
      }
    } else if (tollbooth.checkColor() == Tollbooth.JewelColor.RED) {
      double startTime = getRuntime();
      while(getRuntime() < startTime + 1) {
          movement.directDrive(0, 0, -0.2f);
      }
    } else {
      telemetry.addLine("Error with color sensor readings");
      telemetry.update();
    }
    tollbooth.raise();
  }

  private void moveToGlyphBox() throws Exception {
    movement.xDrive(12);
    movement.yDrive(28);
  }

  private void releaseGlyph() {
    gripper.wideRelease();
  }

}