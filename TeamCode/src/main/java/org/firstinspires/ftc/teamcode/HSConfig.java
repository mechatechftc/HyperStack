package org.firstinspires.ftc.teamcode;

import com.edinaftc.ninevolt.Config;

public class HSConfig extends Config {
  private static HSConfig INSTANCE = new HSConfig();
  public static HSConfig getInstance() {
    return INSTANCE;
  }
  private HSConfig() {
    setLoggingLevel(LoggingLevel.RECOMMENDED);
  }
}
