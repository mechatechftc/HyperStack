package org.firstinspires.ftc.teamcode.opmodes.autonomous.red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.functions.Tollbooth;
import org.firstinspires.ftc.teamcode.opmodes.autonomous.AutonomousDriver;

@Autonomous(name = "Red Autonomous Driver Side OOP", group  = "real")
public class AutonomousDriverRed extends AutonomousDriver{

  public Tollbooth.JewelColor getAllianceColor() {
    return Tollbooth.JewelColor.RED;
  }

  public int getMoveBackDistance() {
    return -28;
  }
}
