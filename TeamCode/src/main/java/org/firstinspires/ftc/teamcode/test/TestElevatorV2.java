package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.edinaftc.ninevolt.util.ExceptionHandling;
import org.firstinspires.ftc.teamcode.functions.Elevator;

@Autonomous(name = "Test Elevator Encoders", group = "test")
public class TestElevatorV2 extends LinearOpMode {
  private Elevator elevator;

  @Override
  public void runOpMode() throws InterruptedException {
    try {
      elevator = new Elevator(hardwareMap.dcMotor.get("elevatorMotor"), 8.5, this);

      waitForStart();

      // Normal operation
      telemetry.addData("Mode", "Normal Operation");
      telemetry.update();
      elevator.elevate(6);
      sleep(3000);
      elevator.elevate(-6);
      sleep(5000);
      // Trying to break the program
      telemetry.addData("Mode", "Forced overflow");
      telemetry.update();
      elevator.elevate(20);
      sleep(1000);
      elevator.elevate(-20);
    } catch (InterruptedException ie) {
      throw ie;
    } catch (Exception e) {
      ExceptionHandling.standardExceptionHandling(e, this);
    }
  }

}
