package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.edinaftc.ninevolt.core.hw.drivetrain.mecanum.Movement;
import com.edinaftc.ninevolt.util.ExceptionHandling;
import org.firstinspires.ftc.teamcode.HSRobot;
import org.firstinspires.ftc.teamcode.functions.Gripper;

/**
 * Created by Richik SC on 9/26/2017.
 */

@Autonomous(name = "Blue Autonomous", group = "real")
@Disabled
public class AutonomousBlue extends LinearOpMode {

  private HSRobot robot;
  private Movement movement;
  private Gripper gripper;

  @Override
  public void runOpMode() throws InterruptedException {
    try {
      // Initialize robot
      robot = new HSRobot(this);

      // Declare variables for utility
      movement = robot.getMovement();
      gripper = robot.getGripper();

    } catch (Exception e) {
      ExceptionHandling.standardExceptionHandling(e, this);
    }
  }

}
