package org.firstinspires.ftc.teamcode.opmodes.autonomous.red;

import com.edinaftc.ninevolt.util.ExceptionHandling;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.functions.Tollbooth;
import org.firstinspires.ftc.teamcode.opmodes.autonomous.base.AutonomousDriver;

@Autonomous(name = "Red Autonomous Driver Side OOP", group  = "real")
public class AutonomousDriverRed extends AutonomousDriver {

  public Tollbooth.JewelColor getAllianceColor() {
    return Tollbooth.JewelColor.RED;
  }

  public void moveToGlyphBox() {
    try {
      movement.yDrive(-24, power);
      sleep(500);
      movement.rotate(180, power);
      sleep(500);
      movement.xDrive(-8, power);
      sleep(500);
      movement.yDrive(6, power);
      sleep(500);
    }
    catch (Exception e) {
      ExceptionHandling.standardExceptionHandling(e, this);
    }
  }
}
