package org.firstinspires.ftc.teamcode.opmodes.autonomous.red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.opmodes.autonomous.base.AutonomousMat;
import org.firstinspires.ftc.teamcode.functions.Tollbooth;

@Autonomous(name = "Red Autonomous Mat Side", group = "real.red")
public class AutonomousMatRed extends AutonomousMat {

  protected Tollbooth.JewelColor getAllianceColor() {
    return Tollbooth.JewelColor.RED;
  }

<<<<<<< HEAD
  protected double getYDistLeft() {
    return -44 -x;
  }

  protected double getYDistRight() {
    return -32 -x;
  }

  protected double getYDistCenter() {
    return -38 - x;
=======
  protected double getPictographDist() { return 1; }

  protected double getYDist() {
    return -38 - getPictographDist();
>>>>>>> c3b5e3a3a885bc627d7acf8d764a9a1b10b8732b
  }

  protected double getRotationAngle() {
    return -80;
  }
}
