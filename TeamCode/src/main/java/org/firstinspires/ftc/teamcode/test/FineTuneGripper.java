package org.firstinspires.ftc.teamcode.test;

import com.edinaftc.ninevolt.util.ExceptionHandling;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.teamcode.HSRobot;
import org.firstinspires.ftc.teamcode.functions.Gripper;

@TeleOp(name = "Gripper Tuner", group = "test")
public class FineTuneGripper extends OpMode {

  HSRobot robot;
  Servo topServo;
  Servo bottomServo;
  Servo bigServo;

  @Override
  public void init() {
    try {
      robot = new HSRobot(this);

      Gripper gripper = robot.getGripper();
      topServo = gripper.topServo;
      bottomServo = gripper.bottomServo;
      bigServo = gripper.bigServo;
      telemetry.addData("Initialization", "Done");
      telemetry.update();
    } catch (Exception e) {
      ExceptionHandling.standardExceptionHandling(e, this);
    }
  }

  @Override
  public void loop() {
    if (gamepad1.dpad_left) {
      topServo.setPosition(Range.clip(topServo.getPosition() - 0.001,0,1));
    } else if(gamepad1.dpad_right) {
      topServo.setPosition(Range.clip(topServo.getPosition() + 0.001,0,1));
    }

    if (gamepad1.left_bumper) {
      bottomServo.setPosition(Range.clip(bottomServo.getPosition() - 0.01,0,1));
    } else if(gamepad1.right_bumper) {
      bottomServo.setPosition(Range.clip(bottomServo.getPosition() + 0.01, 0, 1));
    }

    if (gamepad1.x) {
      bigServo.setPosition(bigServo.getPosition() - 0.01);
    } else if(gamepad1.b) {
      bigServo.setPosition(bigServo.getPosition() + 0.01);
    }

    telemetry.addData("TopServo", topServo.getPosition());
    telemetry.addData("BottomServo", bottomServo.getPosition());
    telemetry.addData("BigServo", bigServo.getPosition());
    telemetry.update();
  }
}
