package org.firstinspires.ftc.teamcode.opmodes.autonomous.blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.opmodes.autonomous.base.AutonomousMat;
import org.firstinspires.ftc.teamcode.functions.Tollbooth;

@Autonomous(name = "Blue Autonomous Mat Side", group = "real.blue")
public class AutonomousMatBlue extends AutonomousMat {

  protected Tollbooth.JewelColor getAllianceColor() {
    return Tollbooth.JewelColor.BLUE;
  }

  protected double getYDistLeft() {
    return 34.5 + getPictographDist();
  }

  protected double getYDistRight() {
    return 28.5 + getPictographDist();
  }

  protected double getYDistCenter() {
    return 40.5 + getPictographDist();
  }

  protected double getPictographDist() {
    return 12;
  }

  protected double getRotationAngle() {
    return -85;
  }
}
