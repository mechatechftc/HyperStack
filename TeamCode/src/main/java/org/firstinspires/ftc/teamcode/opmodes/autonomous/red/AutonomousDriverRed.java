package org.firstinspires.ftc.teamcode.opmodes.autonomous.red;

import com.edinaftc.ninevolt.util.ExceptionHandling;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.functions.Tollbooth;
import org.firstinspires.ftc.teamcode.opmodes.autonomous.base.AutonomousDriver;

@Autonomous(name = "Red Autonomous Driver Side", group  = "real.red")
public class AutonomousDriverRed extends AutonomousDriver {

  public Tollbooth.JewelColor getAllianceColor() {
    return Tollbooth.JewelColor.RED;
  }

  public void moveToGlyphBox(RelicRecoveryVuMark vuMark) {
    try {

      movement.yDrive(-31.5, power);
      sleep(500);
      movement.rotate(180, power);
      sleep(500);

      if(vuMark == RelicRecoveryVuMark.LEFT) {
        movement.xDrive(offset, power);
        sleep(500);
      }
      else if(vuMark == RelicRecoveryVuMark.RIGHT) {
        movement.xDrive(-offset, power);
        sleep(500);
      }
      movement.yDrive(6, power);
      sleep(500);
    }
    catch(Exception e){
      ExceptionHandling.standardExceptionHandling(e, this);
    }
  }
}