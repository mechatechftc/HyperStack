package org.firstinspires.ftc.teamcode.opmodes.autonomous;

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
  private Movement movement;
  private Gripper gripper;
  private Tollbooth tollbooth;
  private Elevator elevator;
  private StepNotifier notifier;

  public abstract Tollbooth.JewelColor getAllianceColor();
  public abstract Tollbooth.JewelColor getOpponentColor();
  public abstract int getMoveBackDistance();

  @Override
  public void runOpMode() throws InterruptedException {
    try {
      customInit();
      bumpJewel(getAllianceColor(), getOpponentColor());
      moveBack(getMoveBackDistance());
      dropGlyph();
    }
    catch(InterruptedException ie) {
      throw ie;
    }
    catch(Exception e) {
      ExceptionHandling.standardExceptionHandling(e, this);
    }
  }

  private void customInit() throws Exception{
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

  private void bumpJewel (Tollbooth.JewelColor getAllianceColor,
                          Tollbooth.JewelColor getOpponentColor) throws Exception{

    gripper.grip();
    elevator.elevate(5);
    tollbooth.lower(); // Lower tollbooth arm
    notifier.notifyStep();
    sleep(3000);

    Tollbooth.JewelColor color = tollbooth.checkColor();
    notifier.notifyStep();
    if (color == getOpponentColor) {
      movement.rotate(15, 0.2f);
      notifier.notifyStep();
      sleep(3000);
      tollbooth.raise();
      sleep(3000);

    } else if (color == getAllianceColor) {
      movement.rotate(-15, 0.2f);
      notifier.notifyStep();
      sleep(3000);
      tollbooth.raise();
      sleep(3000);

    } else {
      telemetry.addLine("Error with color sensor readings");
      telemetry.update();
    }
  }

  private void moveBack(int getMoveBackDistance) throws Exception{
    movement.yDrive(getMoveBackDistance, 0.2f);
  }

  private void dropGlyph() throws Exception{
    elevator.elevate(-5);
    gripper.wideRelease();
  }

}
