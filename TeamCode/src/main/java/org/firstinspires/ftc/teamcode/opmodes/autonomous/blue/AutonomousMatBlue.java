package org.firstinspires.ftc.teamcode.opmodes.autonomous.blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.opmodes.autonomous.base.AutonomousMat;
import org.firstinspires.ftc.teamcode.functions.Tollbooth;

@Autonomous(name = "Autonomous Blue Mat Side OOP", group = "real")
public class AutonomousMatBlue extends AutonomousMat {
  @Override
  protected Tollbooth.JewelColor getAllianceColor() {
    return Tollbooth.JewelColor.BLUE;
  }

  @Override
  protected double getYDist() {
    return 34;
  }

  @Override
  protected double getRotationAngle() {
    return -90;
  }
}
