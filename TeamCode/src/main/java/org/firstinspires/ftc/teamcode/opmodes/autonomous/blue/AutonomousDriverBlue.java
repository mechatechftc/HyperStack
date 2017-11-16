package org.firstinspires.ftc.teamcode.opmodes.autonomous.blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.functions.Tollbooth;
import org.firstinspires.ftc.teamcode.opmodes.autonomous.AutonomousDriver;

@Autonomous (name = "Blue Autonomous Driver Side", group = "real")
public class AutonomousDriverBlue extends AutonomousDriver{

  @Override
  public Tollbooth.JewelColor getAllianceColor() {
    return Tollbooth.JewelColor.BLUE;
  }

  @Override
  public Tollbooth.JewelColor getOpponentColor() {
    return Tollbooth.JewelColor.RED;
  }
  @Override
  public int getMoveBackDistance() {return 28;}

}
