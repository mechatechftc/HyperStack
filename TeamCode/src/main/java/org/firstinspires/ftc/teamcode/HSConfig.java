package org.firstinspires.ftc.teamcode;

import com.edinaftc.ninevolt.Config;

public class HSConfig extends Config {
  private static HSConfig INSTANCE = new HSConfig();
  public static HSConfig getInstance() {
    return INSTANCE;
  }
  private HSConfig() {
    setLoggingLevel(LoggingLevel.VERBOSE);
  }

  public String getVuKey() {
    return "AZgpFln/////AAAAGRdVHDnE6k0Wlm/7+0VaHCgLGSbWoGaT48qgOqkPSbfpXssHNhaduYnt17p4DHt+le2FMfVykSh1cC3OA/pAHxx6XCFY" +
        "xR7xW3YrPvEiPraSe3/26RWitqBWTSOP1ho2H7AXLsMDA7mg+2ij7nzK1HRCXhlauEQbAosl+/cedPlpkEw93CeGSGaolhOtsyNOZxhGlh+005r" +
        "OFVrXJxQdztw3KsUPzN8KHOEZ/cCucocPvgsqLE+T7eWjAMqnbveXB3X6XBFswYUC7InbaWXZrXC04pLyKjM9wpG+GJMemOUtFpF9CXt1vKBx8r" +
        "grn929sAOx1YAAX6o+CDTbaFdQq1EpJJ4s+FizjRVX+QMWLbr2";
  }
}
