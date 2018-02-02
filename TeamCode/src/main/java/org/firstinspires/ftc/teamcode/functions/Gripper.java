package org.firstinspires.ftc.teamcode.functions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Richik SC on 10/3/2017.
 */

public class Gripper {

  private double closedPosition     =  0.5;
  private double openPosition       =  0.15;
  private double midPosition        =  0.3;

  private OpMode ctx;
  public Servo bigServo;
  public Servo bottomServo;
  public Servo topServo;
  private boolean gripping;

  public Gripper(String bigServoId, String bottomServoId, String topServoId, OpMode ctx) {
    this.bigServo = ctx.hardwareMap.servo.get(bigServoId);
    this.bottomServo = ctx.hardwareMap.servo.get(bottomServoId);
    this.topServo = ctx.hardwareMap.servo.get(topServoId);
    this.ctx = ctx;

    bigServo.setDirection(Servo.Direction.REVERSE);
    bottomServo.setDirection(Servo.Direction.REVERSE);
  }

  public void setPosition(double position) {
    bigServo.setPosition(position - 0.2);
    bottomServo.setPosition(position);
    topServo.setPosition(position + 0.08);
  }

  public double getPosition() {
    return bigServo.getPosition();
  }

  public void updateTelemetry() {
//    if (isGripping()) ctx.telemetry.addData("Gripper", "Closed");
//    else ctx.telemetry.addData("Gripper", "Open");
//    ctx.telemetry.update();
  }

  public void bottomGrip() {
    bigServo.setPosition(closedPosition);
    bottomServo.setPosition(closedPosition);
  }

  public void grip() {
    setPosition(closedPosition);
    gripping = true;
    updateTelemetry();
  }

  public void wideRelease() {
    setPosition(openPosition);
    gripping = false;
    updateTelemetry();
  }

  public void midPosition() {
    bigServo.setPosition(midPosition - 0.27);
    bottomServo.setPosition(midPosition);
    topServo.setPosition(midPosition + 0.08);
  }
}
