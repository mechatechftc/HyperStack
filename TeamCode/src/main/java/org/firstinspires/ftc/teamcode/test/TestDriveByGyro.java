package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.edinaftc.ninevolt.core.hw.Hardware;
import com.edinaftc.ninevolt.core.hw.HardwareBuilder;
import com.edinaftc.ninevolt.core.hw.drivetrain.mecanum.Movement;
import com.edinaftc.ninevolt.util.ExceptionHandling;

@Autonomous(name = "Test the Gryo Drive", group = "test")

public class TestDriveByGyro extends LinearOpMode {

  Hardware hardware;
  Movement movement;


  @Override
  public void runOpMode() {
    try {
      customInit();
      movement.driveUsingGyro(3, 0.5f);
      movement.driveUsingGyro(3, -0.5f);
    }
    catch (Exception e) {
      ExceptionHandling.standardExceptionHandling(e, this);
    }
  }

  private void customInit() throws Exception{
    HardwareBuilder hb = new HardwareBuilder(hardwareMap);
    hb.setMotorConfig(Hardware.MotorMode.HOLONOMIC, DcMotor.Direction.FORWARD)
        .addMotorFL("motor_fl").addMotorFR("motor_fr")
        .addMotorBL("motor_bl").addMotorBR("motor_br");
    this.hardware = hb.build();
    hb = null;
    hardware.init();
  }
}