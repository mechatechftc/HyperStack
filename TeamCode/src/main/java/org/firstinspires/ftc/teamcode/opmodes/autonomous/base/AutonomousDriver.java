package org.firstinspires.ftc.teamcode.opmodes.autonomous.base;

import com.edinaftc.ninevolt.core.hw.drivetrain.Movement;
import com.edinaftc.ninevolt.util.ExceptionHandling;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.HSRobot;
import org.firstinspires.ftc.teamcode.functions.Elevator;
import org.firstinspires.ftc.teamcode.functions.Gripper;
import org.firstinspires.ftc.teamcode.functions.Tollbooth;
import org.firstinspires.ftc.teamcode.util.StepNotifier;

abstract public class AutonomousDriver extends LinearOpMode{

  private HSRobot robot;
  private Gripper gripper;
  private Tollbooth tollbooth;
  private Elevator elevator;
  private StepNotifier notifier;

  public Movement movement;
  public float power = 0.5f;

  public abstract Tollbooth.JewelColor getAllianceColor();
  public abstract void moveToGlyphBox();

  @Override
  public void runOpMode() throws InterruptedException {
    try {
      customInit();
      waitForStart();
      bumpJewel(getAllianceColor());
      moveToGlyphBox();
      releaseGlyph();
    }
    catch(InterruptedException ie) {
      throw ie;
    }
    catch(Exception e) {
      ExceptionHandling.standardExceptionHandling(e, this);
    }
  }

  private void customInit() throws Exception {
    robot = new HSRobot(this);
    this.movement = robot.getMovement();
    this.gripper = robot.getGripper();
    this.tollbooth = robot.getTollbooth();
    this.elevator = robot.getElevator();
    this.notifier = new StepNotifier(new String[]{
        "Elevator up",
        "Tollbooth lowered",
        "Gripper released",
        "Color checked",
        "Bump jewel",
        "Re-align",
        "Raise tollbooth",
        "Move off platform"
    }, this);
  }

  private Tollbooth.JewelColor oppositionColor(Tollbooth.JewelColor allianceColor) {
    if (allianceColor == Tollbooth.JewelColor.RED) return Tollbooth.JewelColor.BLUE;
    else if (allianceColor == Tollbooth.JewelColor.BLUE) return Tollbooth.JewelColor.RED;
    else return null;
  }

  private void bumpJewel (Tollbooth.JewelColor allianceColor) throws Exception{

    gripper.grip();
    elevator.elevate(5);
    tollbooth.lower(); // Lower tollbooth arm
    notifier.notifyStep();
    sleep(3000);

    Tollbooth.JewelColor color = tollbooth.checkColor();
    notifier.notifyStep();
    if (color == allianceColor) {
      movement.rotate(-15, 0.2f);
      notifier.notifyStep();
      sleep(1000);
      tollbooth.raise();
      sleep(1000);
      movement.rotate(15, 0.2f);
      sleep(1000);
    } else if (color == oppositionColor(allianceColor)) {
      movement.rotate(15, 0.2f);
      notifier.notifyStep();
      sleep(1000);
      tollbooth.raise();
      sleep(1000);
      movement.rotate(15, 0.2f);
      sleep(1000);
    } else {
      telemetry.addLine("Error with color sensor readings");
      telemetry.update();
    }
  }

  private void releaseGlyph() {
    try {
      elevator.elevate(-1);
      sleep(1000);
      gripper.wideRelease();
      sleep(500);
      elevator.elevate(3);
      sleep(500);
      movement.yDrive(-4, 0.5f);
      sleep(1000);
    }
    catch (Exception e) {
      ExceptionHandling.standardExceptionHandling(e, this);
    }
  }
}