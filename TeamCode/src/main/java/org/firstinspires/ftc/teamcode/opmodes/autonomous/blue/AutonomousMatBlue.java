package org.firstinspires.ftc.teamcode.opmodes.autonomous.blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.opmodes.autonomous.base.AutonomousMat;
import org.firstinspires.ftc.teamcode.functions.Tollbooth;

@Autonomous(name = "Blue Autonomous Mat Side", group = "real.blue")
public class AutonomousMatBlue extends AutonomousMat {

  protected Tollbooth.JewelColor getAllianceColor() {
    return Tollbooth.JewelColor.BLUE;
  }

<<<<<<< HEAD
  protected double getYDistLeft() {
    return 40.5 + x;
  }

  protected double getYDistRight() {
    return 28.5 + x;
  }

  protected double getYDistCenter() {
    return 34.5 + x;
=======
  protected double getPictographDist() { return -1; }

  protected double getYDist() {
    return 34.5 + getPictographDist();
>>>>>>> c3b5e3a3a885bc627d7acf8d764a9a1b10b8732b
  }

  protected double getRotationAngle() {
    return -85;
  }
}
