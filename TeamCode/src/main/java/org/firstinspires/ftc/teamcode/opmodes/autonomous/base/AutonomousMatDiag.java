package org.firstinspires.ftc.teamcode.opmodes.autonomous.base;

import com.edinaftc.ninevolt.Config;
import com.edinaftc.ninevolt.Ninevolt;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

public abstract class AutonomousMatDiag extends AutonomousMat {
  @Override
  protected void moveToGlyphBox(RelicRecoveryVuMark vuMark) {
    if (Ninevolt.getConfig().minLoggingLevel(Config.LoggingLevel.VERBOSE)) {
      telemetry.addData("VuMark", vuMark.toString());
      telemetry.update();
    }
    try {
      switch (vuMark) {
        case LEFT: {
          sleep(250);
          movement.yDrive(getYDistLeft(), 0.5f);
          sleep(1000);
          break;
        }
        case RIGHT: {
          sleep(250);
          movement.yDrive(getYDistLeft(), 0.5f);
          sleep(1000);
          break;
        }
        default: {
          sleep(250);
          movement.yDrive(getYDistLeft(), 0.5f);
          sleep(1000);
          break;
        }
      }
      movement.rotate(30, 0.5f);
      sleep(500);
    } catch (Exception e) {

    }
  }
}
