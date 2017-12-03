package org.firstinspires.ftc.teamcode.test;
// Testing the rotation
import com.edinaftc.ninevolt.core.hw.Hardware;
import com.edinaftc.ninevolt.core.hw.drivetrain.Movement;
import com.edinaftc.ninevolt.util.ExceptionHandling;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.HSRobot;

@Autonomous(name = "Test the Rotation", group = "test")
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

      movement.rotate(-15, power);
      sleep(500);
      movement.rotate(15, power);
      sleep(500);
      movement.yDrive(20, power);
      sleep(1000);
      movement.rotate(90, power);
    } catch (InterruptedException ie) {
      throw ie;
    } catch (Exception e) {
      ExceptionHandling.standardExceptionHandling(e, this);
    }

  }
}
