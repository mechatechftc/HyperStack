package org.firstinspires.ftc.teamcode.opmodes.autonomous.blue;

import com.edinaftc.ninevolt.util.ExceptionHandling;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.functions.Tollbooth;
import org.firstinspires.ftc.teamcode.opmodes.autonomous.base.AutonomousDriver;

@Autonomous (name = "Blue Autonomous Driver Side", group = "real.blue")
public class AutonomousDriverBlue extends AutonomousDriver {

  private double centerDist = 7.5;

  public Tollbooth.JewelColor getAllianceColor() {
    return Tollbooth.JewelColor.BLUE;
  }

  public void moveToGlyphBox(RelicRecoveryVuMark vuMark) {
    try {
      sleep(500);
      movement.yDrive(30, power);
      sleep(500);
      movement.xDrive(centerDist, power);
      sleep(500);

      switch (vuMark) {
        case LEFT: {
          movement.xDrive(-offset, power);
        }
        case RIGHT: {
          movement.xDrive(offset, power);
        }
      }
    }
    catch(Exception e) {
      ExceptionHandling.standardExceptionHandling(e, this);
    }
  }
}
