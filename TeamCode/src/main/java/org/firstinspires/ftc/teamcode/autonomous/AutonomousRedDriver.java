package org.firstinspires.ftc.teamcode.autonomous;

import com.edinaftc.ninevolt.core.hw.drivetrain.Movement;
import com.edinaftc.ninevolt.util.ExceptionHandling;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.HSRobot;
import org.firstinspires.ftc.teamcode.functions.Elevator;
import org.firstinspires.ftc.teamcode.functions.Gripper;
import org.firstinspires.ftc.teamcode.functions.Tollbooth;
import org.firstinspires.ftc.teamcode.util.StepNotifier;

@Autonomous(name = "Red Autonomous Driver Box", group = "real")

public class AutonomousRedDriver extends LinearOpMode {

  private HSRobot robot;
  private Movement movement;
  private Gripper gripper;
  private Tollbooth tollbooth;
  private Elevator elevator;
  private StepNotifier notifier;

  @Override
  public void runOpMode() throws InterruptedException {
    try {
      customInit();
      bumpJewel();
      moveBack();
    } catch (Exception e) {
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

  private void bumpJewel() throws Exception {
    tollbooth.lower(); // Lower tollbooth arm
    notifier.notifyStep();
    sleep(3000);
    Tollbooth.JewelColor color = tollbooth.checkColor();
    notifier.notifyStep();
    if (color == Tollbooth.JewelColor.BLUE) {
      movement.rotate(15, 0.2f);
      notifier.notifyStep();
      sleep(3000);
      tollbooth.raise();
      sleep(3000);
    } else if (color == Tollbooth.JewelColor.RED) {
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
  private void moveBack() {
    try {
      movement.yDrive(-28, 0.2f);
    } catch (Exception e) {
      ExceptionHandling.standardExceptionHandling(e, this);
    }
  }
}
