package org.firstinspires.ftc.teamcode.test;

import com.edinaftc.ninevolt.core.hw.drivetrain.Movement;
import com.edinaftc.ninevolt.util.ExceptionHandling;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.HSRobot;

@Autonomous(name = "Test the Gyro", group = "test")
@Disabled
public class TestGyro extends OpMode {
  HSRobot robot;
  Movement movement;
  BNO055IMU imu;
  @Override
  public void init() {
    try {
      robot = new HSRobot(this);
      movement = robot.getMovement();
      imu = robot.getHardware().imu;
      robot.getTollbooth().raise();
    } catch (Exception e) {
      ExceptionHandling.standardExceptionHandling(e, this);
    }
  }

  @Override
  public void loop() {
    movement.directDrive(0, 0, -0.2f);
    telemetry.addData("Angle", imu.getAngularOrientation().firstAngle);
    telemetry.update();
  }
}
