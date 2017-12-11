package org.firstinspires.ftc.teamcode.opmodes.autonomous.blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.opmodes.autonomous.base.AutonomousMat;
import org.firstinspires.ftc.teamcode.functions.Tollbooth;

@Autonomous(name = "Autonomous Blue Mat Side OOP", group = "real")
public class AutonomousMatBlue extends AutonomousMat {

  protected Tollbooth.JewelColor getAllianceColor() {
    return Tollbooth.JewelColor.BLUE;
  }

  protected double getPictographDist() { return -1; }

  protected double getYDist() {
    return 34.5 + getPictographDist();
  }

  protected double getRotationAngle() {
    return -85;
  }
}
