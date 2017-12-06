package org.firstinspires.ftc.teamcode.opmodes.autonomous.blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import org.firstinspires.ftc.teamcode.functions.Tollbooth;
import org.firstinspires.ftc.teamcode.opmodes.autonomous.base.AutonomousDriver;

@Autonomous (name = "Blue Autonomous Driver Side", group = "real")
@Disabled
public class AutonomousDriverBlue extends AutonomousDriver{

  @Override
  public Tollbooth.JewelColor getAllianceColor() {
    return Tollbooth.JewelColor.BLUE;
  }

  @Override
  public int getMoveBackDistance() {return 28;}

}
