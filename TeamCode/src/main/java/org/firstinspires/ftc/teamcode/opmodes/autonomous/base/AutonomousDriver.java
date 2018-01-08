package org.firstinspires.ftc.teamcode.opmodes.autonomous.base;

import com.edinaftc.ninevolt.core.hw.drivetrain.Movement;
import com.edinaftc.ninevolt.util.ExceptionHandling;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.HSConfig;
import org.firstinspires.ftc.teamcode.HSRobot;
import org.firstinspires.ftc.teamcode.functions.Elevator;
import org.firstinspires.ftc.teamcode.functions.Gripper;
import org.firstinspires.ftc.teamcode.functions.Tollbooth;
import org.firstinspires.ftc.teamcode.util.StepNotifier;

abstract public class AutonomousDriver extends LinearOpMode{

  private HSRobot robot;
  private Gripper gripper;
  private Tollbooth tollbooth;
  private Elevator elevator;
  private StepNotifier notifier;

  private VuforiaTrackable relicTemplate;
  private VuforiaTrackables relicTrackables;

  public Movement movement;

  protected float power = 0.5f;
  protected double offset = 12;

  public abstract Tollbooth.JewelColor getAllianceColor();
  public abstract void moveToGlyphBox(RelicRecoveryVuMark vuMark);

  @Override
  public void runOpMode() throws InterruptedException {
    try {
      customInit();
      waitForStart();
      resetStartTime();

      relicTrackables.activate();

      gripAndElevate();
      bumpJewel(getAllianceColor());
      moveToPictograph();
      moveToGlyphBox(readPictograph());
      releaseGlyph();
    }
    catch(InterruptedException ie) {
      throw ie;
    }
    catch(Exception e) {
      ExceptionHandling.standardExceptionHandling(e, this);
    }
  }

  private void customInit() throws Exception {
    // Initialize Vuforia
    int cameraMonitorViewId = hardwareMap.appContext.getResources()
        .getIdentifier(
            "cameraMonitorViewId",
            "id",
            hardwareMap.appContext.getPackageName()
        );
    VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
    parameters.vuforiaLicenseKey = HSConfig.getInstance().getVuKey();
    parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

    VuforiaLocalizer vuforia = ClassFactory.createVuforiaLocalizer(parameters);
    relicTrackables = vuforia.loadTrackablesFromAsset("RelicVuMark");
    relicTemplate = relicTrackables.get(0);
    relicTemplate.setName("relicVuMarkTemplate");
    idle();

    robot = new HSRobot(this);
    this.movement = robot.getMovement();
    this.gripper = robot.getGripper();
    this.tollbooth = robot.getTollbooth();
    this.elevator = robot.getElevator();
    this.notifier = new StepNotifier(new String[]{
        "Elevator up",
        "Tollbooth lowered",
        "Gripper released",
        "Color checked",
        "Bump jewel",
        "Re-align",
        "Raise tollbooth",
        "Move off platform"
    }, this);
    telemetry.addData("Initialization Done!", "Press play when ready");
    telemetry.update();
  }

  private RelicRecoveryVuMark readPictograph() {
    sleep(250);
    RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
    idle();
    telemetry.addData("VuMark", vuMark.toString());
    telemetry.update();
    return vuMark;
  }

  private void moveToPictograph() throws Exception {
    movement.yDrive(-6, power);
    sleep(1000);
  }

  private Tollbooth.JewelColor oppositionColor(Tollbooth.JewelColor allianceColor) {
    if (allianceColor == Tollbooth.JewelColor.RED) return Tollbooth.JewelColor.BLUE;
    else if (allianceColor == Tollbooth.JewelColor.BLUE) return Tollbooth.JewelColor.RED;
    else return null;
  }

  private void gripAndElevate() throws Exception {
    gripper.grip();
    sleep(500);
    elevator.elevate(5);
    tollbooth.lower(); // Lower tollbooth arm
    notifier.notifyStep();
    sleep(3000);
  }

  private void bumpJewel(Tollbooth.JewelColor allianceColor) {

    Tollbooth.JewelColor color = tollbooth.checkColor();
    notifier.notifyStep();
    if (color == allianceColor) {
      movement.rotate(-15, 0.2f);
      notifier.notifyStep();
      sleep(1000);
      tollbooth.raise();
      sleep(1000);
      movement.rotate(15, 0.2f);
      sleep(1000);
    } else if (color == oppositionColor(allianceColor)) {
      movement.rotate(15, 0.2f);
      notifier.notifyStep();
      sleep(1000);
      tollbooth.raise();
      sleep(1000);
      movement.rotate(-15, 0.2f);
      sleep(1000);
    } else {
      telemetry.addLine("Error with color sensor readings");
      telemetry.update();
    }
  }

  private void releaseGlyph() {
    try {
      elevator.elevate(-1);
      sleep(1000);
      gripper.wideRelease();
      sleep(500);
      elevator.elevate(3);
      sleep(500);
      movement.yDrive(-4, 0.5f);
      sleep(1000);
    }
    catch (Exception e) {
      ExceptionHandling.standardExceptionHandling(e, this);
    }
  }
}
