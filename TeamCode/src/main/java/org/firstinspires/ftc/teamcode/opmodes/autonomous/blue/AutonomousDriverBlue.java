package org.firstinspires.ftc.teamcode.opmodes.autonomous.blue;

import com.edinaftc.ninevolt.util.ExceptionHandling;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.functions.Tollbooth;
import org.firstinspires.ftc.teamcode.opmodes.autonomous.base.AutonomousDriver;

@Autonomous (name = "Blue Autonomous Driver Side", group = "real")
public class AutonomousDriverBlue extends AutonomousDriver {

  public Tollbooth.JewelColor getAllianceColor() {
    return Tollbooth.JewelColor.BLUE;
  }

  public void moveToGlyphBox() {
    try {
      movement.yDrive(24, power);
      sleep(500);
      movement.xDrive(7.5, power);
      sleep(500);
      movement.yDrive(6, power);
      sleep(500);
    }
    catch(Exception e) {
      ExceptionHandling.standardExceptionHandling(e, this);
    }
  }
}
