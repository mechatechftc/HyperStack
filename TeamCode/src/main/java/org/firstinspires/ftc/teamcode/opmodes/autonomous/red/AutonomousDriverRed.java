package org.firstinspires.ftc.teamcode.opmodes.autonomous.red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.functions.Tollbooth;
import org.firstinspires.ftc.teamcode.opmodes.autonomous.AutonomousDriver;

@Autonomous(name = "Red Autonomous Driver Side OOP", group  = "real")
public class AutonomousDriverRed extends AutonomousDriver{

  @Override
  public Tollbooth.JewelColor getAllianceColor() {
    return Tollbooth.JewelColor.RED;
  }

  @Override
  public Tollbooth.JewelColor getOpponentColor() {
    return Tollbooth.JewelColor.BLUE;
  }
  @Override
  public int getMoveBackDistance() {
    return -28;
  }
}
