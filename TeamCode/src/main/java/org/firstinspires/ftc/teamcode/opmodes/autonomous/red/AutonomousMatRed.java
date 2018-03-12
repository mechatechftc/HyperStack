package org.firstinspires.ftc.teamcode.opmodes.autonomous.red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.opmodes.autonomous.base.AutonomousMat;
import org.firstinspires.ftc.teamcode.functions.Tollbooth;

@Autonomous(name = "Red Autonomous Mat Side", group = "real.red")
public class AutonomousMatRed extends AutonomousMat {

  private double centerDist = -38 - getPictographDist();

  protected Tollbooth.JewelColor getAllianceColor() {
    return Tollbooth.JewelColor.RED;
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
    return -6;
  }

  protected double getRotationAngle() {
    return -82;
  }
}
