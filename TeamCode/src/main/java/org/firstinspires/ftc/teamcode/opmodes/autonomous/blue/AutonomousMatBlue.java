package org.firstinspires.ftc.teamcode.opmodes.autonomous.blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.opmodes.autonomous.base.AutonomousMat;
import org.firstinspires.ftc.teamcode.functions.Tollbooth;

@Autonomous(name = "Blue Autonomous Mat Side", group = "real")
public class AutonomousMatBlue extends AutonomousMat {

  protected Tollbooth.JewelColor getAllianceColor() {
    return Tollbooth.JewelColor.BLUE;
  }

  protected double getYDistLeft() {
    return 40.5 + x;
  }

  protected double getYDistRight() {
    return 28.5 + x;
  }

  protected double getYDistCenter() {
    return 34.5 + x;
  }

  protected double getRotationAngle() {
    return -85;
  }
}
