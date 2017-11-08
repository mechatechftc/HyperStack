package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.edinaftc.ninevolt.core.hw.Hardware;
import com.edinaftc.ninevolt.core.hw.HardwareBuilder;
import com.edinaftc.ninevolt.core.hw.drivetrain.mecanum.Movement;
import com.edinaftc.ninevolt.util.ExceptionHandling;

@Autonomous(name = "Test the Encoders", group = "test")

public class TestEncoders extends LinearOpMode {
  private static final int    PULSES_PER_MOTOR_REV  = 28;
  private static final double DRIVE_GEAR_REDUCTION  = 40.0;
  private static final double WHEEL_DIAMETER_INCHES = 4;
  private static final double PULSES_PER_INCH = (PULSES_PER_MOTOR_REV *
      DRIVE_GEAR_REDUCTION) /
      (WHEEL_DIAMETER_INCHES * 3.1415);

  Hardware hardware;
  Movement movement;

  @Override
  public void runOpMode() {
    try {
      customInit();
      waitForStart();
      movement.yDrive(20);
      movement.yDrive(-20);
    }
    catch (Exception e) {
      ExceptionHandling.standardExceptionHandling(e, this);
    }
  }

  private void customInit() throws Exception {
      HardwareBuilder hb = new HardwareBuilder(hardwareMap);
      hb.setMotorConfig(Hardware.MotorMode.MECANUM, DcMotor.Direction.FORWARD)
          .addMotorFL("motorFL").addMotorFR("motorFR")
          .addMotorBL("motorBL").addMotorBR("motorBR");
      this.hardware = hb.build();
      hb = null;
      hardware.init();
      movement = new Movement(hardware, this, PULSES_PER_INCH);
  }
}
