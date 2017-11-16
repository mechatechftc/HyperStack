package org.firstinspires.ftc.teamcode.opmodes.autonomous.blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import org.firstinspires.ftc.teamcode.opmodes.autonomous.AutonomousMat;
import org.firstinspires.ftc.teamcode.functions.Tollbooth;

@Autonomous(name = "Autonomous Blue Mat Side OOP", group = "real")
@Disabled
public class AutonomousMatBlue extends AutonomousMat {
  @Override
  protected double getYDist() {
    return -41;
  }

  @Override
  protected double getSecondRotationAngle() {
    return -90;
  }

  @Override
  protected Tollbooth.JewelColor getAllianceColor() {
    return Tollbooth.JewelColor.RED;
  }

  @Override
  protected void realign() {

  }
}
