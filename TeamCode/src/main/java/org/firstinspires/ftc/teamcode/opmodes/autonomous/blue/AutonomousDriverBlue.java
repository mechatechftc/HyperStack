package org.firstinspires.ftc.teamcode.opmodes.autonomous.blue;

import com.edinaftc.ninevolt.util.ExceptionHandling;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.functions.Tollbooth;
import org.firstinspires.ftc.teamcode.opmodes.autonomous.base.AutonomousDriver;

@Autonomous (name = "Blue Autonomous Driver Side", group = "real.blue")
public class AutonomousDriverBlue extends AutonomousDriver {

  private double centerDist = 22;

  public Tollbooth.JewelColor getAllianceColor() {
    return Tollbooth.JewelColor.BLUE;
  }

  public void moveToGlyphBox(RelicRecoveryVuMark vuMark) {
    telemetry.addData("moveToGlyphBox", "entered");
    telemetry.update();
    try {
      movement.yDrive(31, power);
      sleep(500);
      movement.xDrive(centerDist, power);
      sleep(500);

      if(vuMark == RelicRecoveryVuMark.LEFT) {
        movement.xDrive(-offset, power);
        sleep(500);
      }
      else if(vuMark == RelicRecoveryVuMark.RIGHT) {
        movement.xDrive(offset, power);
        sleep(500);
      }
    }
    catch(Exception e) {
      ExceptionHandling.standardExceptionHandling(e, this);
    }
  }
}
