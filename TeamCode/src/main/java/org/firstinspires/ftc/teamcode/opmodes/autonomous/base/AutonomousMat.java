package org.firstinspires.ftc.teamcode.opmodes.autonomous.base;

import com.edinaftc.ninevolt.Config;
import com.edinaftc.ninevolt.Ninevolt;
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

public abstract class AutonomousMat extends LinearOpMode {

  private HSRobot robot;
  private Movement movement;
  private Gripper gripper;
  private Tollbooth tollbooth;
  private Elevator elevator;

  private VuforiaTrackable relicTemplate;
  private VuforiaTrackables relicTrackables;

  protected abstract Tollbooth.JewelColor getAllianceColor();
  protected abstract double getYDistCenter();
  protected abstract double getYDistLeft();
  protected abstract double getYDistRight();
  protected abstract double getPictographDist();
  protected abstract double getRotationAngle();

  protected double offset = 8;

  @Override
  public void runOpMode() throws InterruptedException {
    try {
      // Initialize robot and variables
      customInit();

      // Alert user that initialization was successful
      if (Ninevolt.getConfig().minLoggingLevel(Config.LoggingLevel.RECOMMENDED)) {
        telemetry.addData("Initialization", "Done!");
        telemetry.update();
      }

      // Wait until play button is pressed
      waitForStart();
      resetStartTime();
      relicTrackables.activate();

      // Perform autonomous
      gripAndElevate();
      bumpJewel(getAllianceColor());
      moveToPictograph();
      moveToGlyphBox(readPictograph());
      releaseGlyph();
      turnAndDrive();
    } catch (InterruptedException ie) {
      throw ie;
    } catch (Exception e) {
      // Stops OpMode and prints exception in case of exception
      ExceptionHandling.standardExceptionHandling(e, this);
    }
  }

  private void turnAndDrive() throws Exception {
    movement.rotate(180,0.5f);
    sleep(500);
    gripper.midPosition();
    sleep(500);
    movement.yDrive(20, 0.5f);
    sleep(500);
    gripper.grip();
    sleep(500);
    movement.yDrive(-10, 0.5f);
    sleep(500);
    movement.rotate(-180, 0.5f);
    sleep(500);
    movement.yDrive(10, 0.5f);
    sleep(500);
    elevator.elevate(6);
    sleep(500);
    movement.yDrive(2, 0.5f);
    sleep(500);
    gripper.lightRelease();
    sleep(500);
    movement.yDrive(-6, 0.5f);
    sleep(500);
  }

  private void gripAndElevate() throws Exception {
    gripper.grip();
    sleep(500);
    elevator.elevate(7);
    sleep(500);
  }

  private RelicRecoveryVuMark readPictograph() {
    sleep(250);
    RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
    idle();
    return vuMark;
  }

  private void moveToPictograph() throws Exception {
    sleep(250);
    movement.yDrive(getPictographDist(), 0.5f);
    sleep(500);
  }

  private void bumpJewel(Tollbooth.JewelColor allianceColor) {
    tollbooth.lower(); // Lower tollbooth arm
    // notifier.notifyStep();
    sleep(1000);
    Tollbooth.JewelColor color = tollbooth.checkColor();
    // notifier.notifyStep();
    if (color == allianceColor) {
      sleep(250);
      movement.rotate(-15, 0.2f);
      // notifier.notifyStep();
      sleep(500);
      tollbooth.raise();
      sleep(500);
      movement.rotate(15, 0.2f);
    } else if (color == getOppositionColor(allianceColor)) {
      sleep(250);
      movement.rotate(15, 0.2f);
      // notifier.notifyStep();
      sleep(500);
      tollbooth.raise();
      sleep(1000);
      movement.rotate(-15, 0.2f);
    } else {
      telemetry.addData("ColorSensor", "Error with color sensor readings");
      telemetry.update();
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

    robot = new HSRobot(this); // Initializes robot
    // Retrieve variables
    movement = robot.getMovement();
    gripper = robot.getGripper();
    tollbooth = robot.getTollbooth();
    elevator = robot.getElevator();

    while (!robot.getHardware().imu.isGyroCalibrated() && opModeIsActive()) {
      idle();
    }
  }

  private Tollbooth.JewelColor getOppositionColor(Tollbooth.JewelColor allianceColor) {
    if (allianceColor == Tollbooth.JewelColor.RED) {
      return Tollbooth.JewelColor.BLUE;
    } else if (allianceColor == Tollbooth.JewelColor.BLUE) {
      return Tollbooth.JewelColor.RED;
    }
    return Tollbooth.JewelColor.INDETERMINATE;
  }

  private void moveToGlyphBox(RelicRecoveryVuMark vuMark) {
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
          movement.yDrive(getYDistRight(), 0.5f);
          sleep(1000);
          break;
        }
        default: {
          sleep(250);
          movement.yDrive(getYDistCenter(), 0.5f);
          sleep(1000);
          break;
        }
      }
      movement.rotate(getRotationAngle(), 0.5f);
      sleep(500);
    }
    catch (Exception e) {
      ExceptionHandling.standardExceptionHandling(e, this);
    }
  }

  private void releaseGlyph() {
    try {
      elevator.elevate(-5);
      idle();
      gripper.wideRelease();
      sleep(250);
      movement.yDrive(10, 0.5f);
      sleep(500);
      movement.yDrive(-4, 0.5f);
      sleep(1000);
    }
    catch (Exception e) {
      ExceptionHandling.standardExceptionHandling(e, this);
    }
  }
}
