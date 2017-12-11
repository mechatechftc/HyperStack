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
import org.firstinspires.ftc.teamcode.util.StepNotifier;

public abstract class AutonomousMat extends LinearOpMode {

  protected HSRobot robot;
  protected Movement movement;
  protected Gripper gripper;
  protected Tollbooth tollbooth;
  protected Elevator elevator;

  private StepNotifier notifier;
  private VuforiaLocalizer vuforia;
  private VuforiaTrackable relicTemplate;

  protected abstract Tollbooth.JewelColor getAllianceColor();
<<<<<<< HEAD
  protected abstract double getYDistCenter();
  protected abstract double getYDistLeft();
  protected abstract double getYDistRight();
=======
  protected abstract double getPictographDist();
  protected abstract double getYDist();
>>>>>>> c3b5e3a3a885bc627d7acf8d764a9a1b10b8732b
  protected abstract double getRotationAngle();

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

      // Perform autonomous
      gripper.grip();
      sleep(500);
      elevator.elevate(4);
      notifier.notifyStep();
      sleep(1000);
      bumpJewel(getAllianceColor());
      moveToPictograph();
      moveToGlyphBox(readPictograph());
      releaseGlyph();
    } catch (Exception e) {
      // Stops OpMode and prints exception in case of exception
      ExceptionHandling.standardExceptionHandling(e, this);
    }
  }

  private RelicRecoveryVuMark readPictograph() {
    RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
    return vuMark;
  }

  private void moveToPictograph() throws Exception {
    movement.yDrive(getPictographDist(), 0.5f);
  }

  private void bumpJewel(Tollbooth.JewelColor allianceColor) {
    tollbooth.lower(); // Lower tollbooth arm
    // notifier.notifyStep();
    sleep(1000);
    Tollbooth.JewelColor color = tollbooth.checkColor();
    // notifier.notifyStep();
    if (color == allianceColor) {
      movement.rotate(-15, 0.2f);
      // notifier.notifyStep();
      sleep(1000);
      tollbooth.raise();
      sleep(1000);
      movement.rotate(15, 0.2f);
    } else if (color == getOppositionColor(allianceColor)) {
      movement.rotate(15, 0.2f);
      // notifier.notifyStep();
      sleep(1000);
      tollbooth.raise();
      sleep(1000);
      movement.rotate(-15, 0.2f);
    } else {
      telemetry.addData("ColorSensor", "Error with color sensor readings");
      telemetry.update();
    }
  }

  private void customInit() throws Exception {
    robot = new HSRobot(this); // Initializes robot
    // Retrieve variables
    movement = robot.getMovement();
    gripper = robot.getGripper();
    tollbooth = robot.getTollbooth();
    elevator = robot.getElevator();

    // vuforia
    int cameraMonitorViewId = hardwareMap.appContext.getResources()
        .getIdentifier(
            "cameraMonitorViewId",
            "id",
            hardwareMap.appContext.getPackageName()
        );
    VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
    parameters.vuforiaLicenseKey = HSConfig.getInstance().getVuKey();
    parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

    vuforia = ClassFactory.createVuforiaLocalizer(parameters);
    VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
    relicTemplate = relicTrackables.get(0);

    notifier = new StepNotifier(new String[] {
        "Elevator up",
        "Tollbooth lowered",
        "Gripper released",
        "Color checked",
        "Bump jewel",
        "Re-align",
        "Raise tollbooth",
        "Move off platform"
    }, this);

    while (!robot.getHardware().imu.isGyroCalibrated() && opModeIsActive()) {
      telemetry.addData("Gyro", "Calibrating");
      telemetry.update();
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

  protected void moveToGlyphBox(RelicRecoveryVuMark vuMark) {
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
      sleep(1000);
      elevator.elevate(-2);
      sleep(500);
    }
    catch (Exception e) {
      ExceptionHandling.standardExceptionHandling(e, this);
    }
  }

  private void releaseGlyph() {
    try {
      movement.yDrive(10, 0.5f);
      sleep(500);
      elevator.elevate(-1);
      sleep(500);
      gripper.wideRelease();
      sleep(250);
      movement.yDrive(-4, 0.5f);
      sleep(1000);
    }
    catch (Exception e) {
      ExceptionHandling.standardExceptionHandling(e, this);
    }
  }
}
