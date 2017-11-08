package org.firstinspires.ftc.teamcode.util;

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
    telemetry.addData("Step",
        String.format(Locale.US, "%d Done: %s", currentStep, steps[currentStep-1]));
  }

  public void notifyStep() {
    currentStep++;
    telemetry.addData("Step", String.format(Locale.US, "%d Done: %s", currentStep, steps[currentStep-1]));
  }



}
