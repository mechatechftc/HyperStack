package org.firstinspires.ftc.teamcode.opmodes.autonomous.blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.opmodes.autonomous.base.AutonomousMat;
import org.firstinspires.ftc.teamcode.functions.Tollbooth;

@Autonomous(name = "Blue Autonomous Mat Side", group = "real.blue")
public class AutonomousMatBlue extends AutonomousMat {

  private double centerDist = 34.5 + getPictographDist();

  protected Tollbooth.JewelColor getAllianceColor() {
    return Tollbooth.JewelColor.BLUE;
  }

  protected double getYDistLeft() {
    return centerDist - offset;
  }

  protected double getYDistRight() {
    return centerDist + offset;
  }

  protected double getYDistCenter() {
    return centerDist;
  }

  protected double getPictographDist() {
    return 12;
  }

  protected double getRotationAngle() {
    return -85;
  }
}
