package org.firstinspires.ftc.teamcode.functions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.edinaftc.ninevolt.core.hw.drivetrain.holonomic.Movement;
import org.firstinspires.ftc.teamcode.HSRobot;

public class Tollbooth {

  private HSRobot robot;
  private Movement movement;
  private OpMode ctx;
  private LinearOpMode ctxl;
  private Servo servo;
  private ColorSensor colorSensor;

  private double raisedPosition = 0.25;
  private double loweredPosition = 0.82;

  public enum JewelColor {
    RED, BLUE, INDETERMINATE
  }

  public Tollbooth(String servoId, String colorId, OpMode ctx) {
    this.ctx = ctx;
    colorSensor = ctx.hardwareMap.get(ColorSensor.class, colorId);
    this.servo = ctx.hardwareMap.servo.get(servoId);
    this.servo.setDirection(Servo.Direction.REVERSE);
  }

  public void lower() { servo.setPosition(loweredPosition); }

  public void raise() { servo.setPosition(raisedPosition); }

  public int getRed() {
    return colorSensor.red();
  }

  public int getGreen() {
    return colorSensor.green();
  }

  public int getBlue() {
    return colorSensor.blue();
  }

  public boolean isRaised() {
    return (servo.getPosition() < ((raisedPosition + loweredPosition) / 2));
  }

  public JewelColor checkColor() {
    int blue = getBlue();
    int red = getRed();
    if (blue > red) {
      return JewelColor.BLUE;
    } else if (red > blue) {
      return JewelColor.RED;
    } else {
      return JewelColor.INDETERMINATE;
    }
  }

  public void lowerBlocking() throws Exception {
    if (ctxl == null) throw new Exception("Tollbooth: No linear context provided!");
    lower();
    while (servo.getPosition() != loweredPosition) {
      ctxl.sleep(100);
    }
  }

  public void raiseBlocking() throws Exception {
    if (ctxl == null) throw new Exception("Tollbooth: No linear context provided!");
    raise();
    while (servo.getPosition() != raisedPosition) {
      ctxl.sleep(100);
    }
  }

  public void setCtxl(LinearOpMode ctxl) {
    this.ctxl = ctxl;
  }
}
