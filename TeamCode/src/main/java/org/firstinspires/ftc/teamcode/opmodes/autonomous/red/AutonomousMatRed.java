package org.firstinspires.ftc.teamcode.opmodes.autonomous.red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.opmodes.autonomous.base.AutonomousMat;
import org.firstinspires.ftc.teamcode.functions.Tollbooth;

@Autonomous(name = "Red Autonomous Mat Side", group = "real")
public class AutonomousMatRed extends AutonomousMat {

  protected Tollbooth.JewelColor getAllianceColor() {
    return Tollbooth.JewelColor.RED;
  }

  protected double getPictographDist() { return 1; }

  protected double getYDist() {
    return -38 - getPictographDist();
  }

  protected double getRotationAngle() {
    return -80;
  }
}
