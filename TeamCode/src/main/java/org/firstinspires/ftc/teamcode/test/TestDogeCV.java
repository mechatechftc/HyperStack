package org.firstinspires.ftc.teamcode.test;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.detectors.CryptoboxDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "Test dogecv", group = "test")
public class TestDogeCV extends LinearOpMode{

  private CryptoboxDetector cryptoboxDetector;

  public void runOpMode() {
    customInit();
    waitForStart();
    detectCryptobox();
    cryptoboxDetector.disable();
  }

  private void customInit() {
    cryptoboxDetector = new CryptoboxDetector();
    cryptoboxDetector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());

    cryptoboxDetector.rotateMat = true;
    cryptoboxDetector.enable();

    telemetry.addData("Init", "Done");
    telemetry.update();
  }

  private void detectCryptobox() {
    while(opModeIsActive()) {
      if(cryptoboxDetector.isCryptoBoxDetected() && cryptoboxDetector.isColumnDetected())
      telemetry.addData("Column Left ", cryptoboxDetector.getCryptoBoxLeftPosition());
      telemetry.addData("Column Center ", cryptoboxDetector.getCryptoBoxCenterPosition());
      telemetry.addData("Column Right ", cryptoboxDetector.getCryptoBoxRightPosition());
      telemetry.update();
    }
  }
}
