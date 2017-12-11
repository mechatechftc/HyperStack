package org.firstinspires.ftc.teamcode.opmodes.autonomous.red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.opmodes.autonomous.base.AutonomousMat;
import org.firstinspires.ftc.teamcode.functions.Tollbooth;

@Autonomous(name = "Red Autonomous Mat Side", group = "real.red")
public class AutonomousMatRed extends AutonomousMat {

  protected Tollbooth.JewelColor getAllianceColor() {
    return Tollbooth.JewelColor.RED;
  }

  protected double getYDistLeft() {
    return -44 - getPictographDist();
  }

  protected double getYDistRight() {
    return -32 - getPictographDist();
  }

  protected double getYDistCenter() {
    return -38 - getPictographDist();

    protected double getPictographDist() {
      return 1;
    }

    protected double getRotationAngle() {
      return -80;
    }
  }
}
