package org.firstinspires.ftc.teamcode.opmodes.autonomous.red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.opmodes.autonomous.base.AutonomousMat;
import org.firstinspires.ftc.teamcode.functions.Tollbooth;

@Autonomous(name = "Autonomous Red Mat Side OOP", group = "real")
public class AutonomousMatRed extends AutonomousMat {
  @Override
  protected Tollbooth.JewelColor getAllianceColor() {
    return Tollbooth.JewelColor.RED;
  }

  @Override
  protected double getYDist() {
    return -38;
  }

  @Override
  protected double getRotationAngle() {
    return -80;
  }
}
