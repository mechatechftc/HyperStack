package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.edinaftc.ninevolt.core.hw.Hardware;
import com.edinaftc.ninevolt.core.hw.HardwareBuilder;
import com.edinaftc.ninevolt.core.hw.drivetrain.holonomic.Movement;
import com.edinaftc.ninevolt.util.ExceptionHandling;

/**
 * Created by Richik SC on 9/16/2017.
 */
@TeleOp(name = "Test the Chassis", group = "test")
public class TestChassis extends OpMode {
  private Hardware hw;
  private Movement movement;

  @Override
  public void init() {
    try {
      HardwareBuilder hb = new HardwareBuilder(hardwareMap);
      hb.setMotorConfig(Hardware.MotorMode.HOLONOMIC, DcMotor.Direction.FORWARD);
      hb
          .addMotorFL("motorFL")
          .addMotorFR("motorFR")
          .addMotorBL("motorBL")
          .addMotorBR("motorBR");
      hw = hb.build();
      hb = null;
      hw.init();
      movement = new Movement(hw, this);
    } catch (Exception e) {
      ExceptionHandling.standardExceptionHandling(e, this);
    }
  }

  @Override
  public void loop() {
    movement.directDrive(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x);
  }
}
