package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.edinaftc.ninevolt.util.ExceptionHandling;
import org.firstinspires.ftc.teamcode.functions.Tollbooth;

@Autonomous(name = "Test Tollbooth", group = "test")
public class TestTollbooth extends LinearOpMode {

  @Override
  public void runOpMode() throws InterruptedException {
    Tollbooth tollbooth = new Tollbooth("tollboothServo", "colorSensor", this);
    tollbooth.setCtxl(this);
    tollbooth.raise();
    waitForStart();
    if (opModeIsActive()) {
      try {
        tollbooth.lower();
        sleep(3000);
        telemetry.addData("Color", tollbooth.checkColor().name());
        telemetry.update();
        sleep(3000);
        tollbooth.raiseBlocking();
        telemetry.addData("Color", tollbooth.checkColor().name());
        telemetry.update();
        sleep(3000);
        tollbooth.lower();
        sleep(3000);
        tollbooth.raise();
      } catch (Exception e) {
        ExceptionHandling.standardExceptionHandling(e, this);
      }
    }
  }
}
