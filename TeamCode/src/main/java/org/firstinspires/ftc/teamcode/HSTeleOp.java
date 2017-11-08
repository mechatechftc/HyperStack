package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.edinaftc.ninevolt.core.hw.drivetrain.mecanum.Movement;
import com.edinaftc.ninevolt.util.ExceptionHandling;
import org.firstinspires.ftc.teamcode.functions.Elevator;
import org.firstinspires.ftc.teamcode.functions.Gripper;

/**
 * Created by Richik SC on 9/16/2017.
 */
@TeleOp(name = "HyperStack TeleOp", group = "real")
public class HSTeleOp extends OpMode {

  private HSRobot robot;
  private Movement movement;
  private Gripper gripper;
  private Elevator elevator;
  private boolean gripperLock;
  private int block;
  private double lastBlockUpdateTime;

  // Initialization block - run once
  @Override
  public void init() {
    try {
      // Actually initialize robot
      robot = new HSRobot(this);

      // Utility variables
      movement = robot.getMovement();
      gripper = robot.getGripper();
      elevator = robot.getElevator();

      movement.setRunUsingEncoders();
      movement.setVerbose(true);

      // Alert user that initialization was successful
      telemetry.addData("Initialization", "Done!");
      telemetry.update();

    } catch (Exception e) {
      // Stops OpMode and prints exception in case of exception
      ExceptionHandling.standardExceptionHandling(e, this);
    }
  }

  // Start block - run on play press - after init, before loop
  @Override
  public void start() {
    // Initalize block timer
    resetStartTime();
    block = 1;
    lastBlockUpdateTime = 0;
  }

  // Loop block - run repeatedly after init
  @Override
  public void loop() {

    drive(softGear());

    grip();

    gripLock();

    moveElevator();

    blockNotify(getRuntime());

  }

  public void drive(float gearRatio) {
    movement.directDrive(
            gamepad1.left_stick_x * gearRatio,
            (-gamepad1.left_stick_y) * gearRatio,
            gamepad1.right_stick_x * gearRatio
    );
  }

  public void grip() {
    if (gamepad2.right_trigger > 0.8) {
      gripper.grip();
    } else if (gamepad2.right_bumper) {
      gripper.lightRelease();
    }
    else if (gamepad2.left_trigger > 0.8) {
      gripper.bottomGrip();
    }
    else {
      if (!gripperLock) { gripper.wideRelease(); }
    }
  }

  public float softGear() {
    if (gamepad1.right_trigger > 0.8) {
      return 0.5f;
    } else {
      return 1f;
    }
  }

  public void gripLock() {
    if (gamepad2.right_trigger > 0.8 && gamepad2.a) {
      if (!gripperLock) { gripperLock = true; }
      else { gripperLock = false; }
    }
  }

  public void moveElevator() {
    if(gamepad2.dpad_up) {
      elevator.directDrive(0.75f);
    } else if (gamepad2.dpad_down) {
      elevator.directDrive(-0.2f);
    } else {
      elevator.stop();
    }
  }

  public void blockNotify(double rt) {
    telemetry.addData("Block", block);
    gripper.updateTelemetry();
    if (rt - lastBlockUpdateTime > 10 && block < 12) {
      block++;
      lastBlockUpdateTime =  rt;
    }
    if(rt > 90) {
      telemetry.addLine("End Game");
    }
    telemetry.update();
  }
}