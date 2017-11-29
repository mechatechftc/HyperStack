package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.HSRobot;

public class TestRotate extends LinearOpMode {

  private HSRobot robot;
  private Hardware hardware;
  private Movement movement;

  private float power = 0.5f;

  @Override
  public void runOpMode() throws InterruptedException {

    this.robot = new HSRobot(this);
    this.hardware = robot.getHardware();
    this.movement = robot.getMovement();
    waitForStart();
    movement.rotate(90, power);
    movement.yDrive(20, power);
    rotateLoop();
  }

  private void rotateLoop() {
    for (int i = 1; i < 45; i++) {
      if (!opModeIsActive()) {
        exit();
      }
      movement.rotate(i, power);
      movement.rotate(-i-1, power);
      idle();
    }
    exit();
  }
}
