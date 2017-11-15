package org.firstinspires.ftc.teamcode.util;

import com.edinaftc.ninevolt.Config;
import com.edinaftc.ninevolt.Ninevolt;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.Locale;

public class StepNotifier {
  private String[] steps;
  private int currentStep;
  private Telemetry telemetry;

  public StepNotifier(String[] steps, OpMode ctx) {
    this.steps = steps;
    this.currentStep = 1;
    this.telemetry = ctx.telemetry;
  }

  public int getCurrentStep() {
    return currentStep;
  }

  public String[] getSteps() {
    return steps;
  }

  public void notifyStep(int step) {
    this.currentStep = step;
    if (Ninevolt.getConfig().minLoggingLevel(Config.LoggingLevel.VERBOSE)) {
      telemetry.addData("Step",
          String.format(Locale.US, "%d Done: %s", currentStep, steps[currentStep - 1]));
      telemetry.update();
    }
  }

  public void notifyStep() {
    currentStep++;
    if (Ninevolt.getConfig().minLoggingLevel(Config.LoggingLevel.VERBOSE)) {
      telemetry.addData("Step", String.format(Locale.US, "%d Done: %s", currentStep, steps[currentStep - 1]));
      telemetry.update();
    }
  }



}
