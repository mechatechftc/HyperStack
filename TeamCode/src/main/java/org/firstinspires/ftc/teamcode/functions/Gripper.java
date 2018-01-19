package org.firstinspires.ftc.teamcode.functions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Richik SC on 10/3/2017.
 */

public class Gripper {

  private double closedPosition     =  0.75;
  private double lightOpenPosition  =  0.38;
  private double openPosition       =  0.15;
  private double midPosition        =  0.3;

  private OpMode ctx;
  private Servo bigServo;
  private Servo bottomServo;
  private Servo topServo;
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
    bigServo.setPosition(position);
    bottomServo.setPosition(position);
    topServo.setPosition(position);
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

  public void lightRelease() {
    bigServo.setPosition(lightOpenPosition - 0.07);
    bottomServo.setPosition(lightOpenPosition);
    topServo.setPosition(lightOpenPosition + 0.08);
    gripping = false;
    updateTelemetry();
  }

  public void wideRelease() {
    bottomServo.setPosition(openPosition);
    topServo.setPosition(openPosition);
    bigServo.setPosition(openPosition + 0.1);
    gripping = false;
    updateTelemetry();
  }

  public void midPosition() {
    setPosition(midPosition);
  }

  public boolean isGripping() {
    return gripping;
  }

}
