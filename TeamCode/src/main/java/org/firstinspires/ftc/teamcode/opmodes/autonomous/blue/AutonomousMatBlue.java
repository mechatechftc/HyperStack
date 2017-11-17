package org.firstinspires.ftc.teamcode.opmodes.autonomous.blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.opmodes.autonomous.AutonomousMat;
import org.firstinspires.ftc.teamcode.functions.Tollbooth;

@Autonomous(name = "Autonomous Blue Mat Side OOP", group = "real")
public class AutonomousMatBlue extends AutonomousMat {

  protected void bumpJewel() {
    tollbooth.lower(); // Lower tollbooth arm
    // notifier.notifyStep();
    sleep(3000);
    Tollbooth.JewelColor color = tollbooth.checkColor();
    // notifier.notifyStep();
    if (color == Tollbooth.JewelColor.RED) {
      movement.rotate(15, 0.2f);
      // notifier.notifyStep();
      sleep(3000);
      tollbooth.raise();
      sleep(3000);
      movement.rotate(-45, 0.2f);
    } else if (color == Tollbooth.JewelColor.BLUE) {
      movement.rotate(-30, 0.2f);
      // notifier.notifyStep();
      sleep(3000);
      tollbooth.raise();
      sleep(3000);
    } else {
      telemetry.addData("ColorSensor", "Error with color sensor readings");
      telemetry.update();
    }
  }
}
