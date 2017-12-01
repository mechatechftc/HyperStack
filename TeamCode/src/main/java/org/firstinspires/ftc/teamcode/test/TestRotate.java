package org.firstinspires.ftc.teamcode.test;

import com.edinaftc.ninevolt.core.hw.Hardware;
import com.edinaftc.ninevolt.core.hw.drivetrain.Movement;
import com.edinaftc.ninevolt.util.ExceptionHandling;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.HSRobot;

@Autonomous(name = "Test the Rotation", group = "test")
@Disabled
public class TestRotate extends LinearOpMode {

  private HSRobot robot;
  private Hardware hardware;
  private Movement movement;

  private float power = 0.5f;

  @Override
  public void runOpMode() throws InterruptedException {

    try {
      this.robot = new HSRobot(this);
      this.hardware = robot.getHardware();
      this.movement = robot.getMovement();
      waitForStart();
      movement.rotate(90, power);
      sleep(1000);
      movement.yDrive(20, power);
      sleep(1000);
      rotateLoop();
    } catch (Exception e) {
      ExceptionHandling.standardExceptionHandling(e, this);
    }

  }

  private void rotateLoop() {
    for (int i = 1; i < 45; i++) {
      if (!opModeIsActive()) {
        break;
      }
      movement.rotate(i, power);
      sleep(100);
      movement.rotate(-i-1, power);
      sleep(100);
      idle();
    }
    requestOpModeStop();
  }
}
