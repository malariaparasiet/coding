import logUtil.LogUtil;
import logUtil.LogUtil.LogTypes;

public class Main {
  
  // The constructor takes the desired log file name
  private static final LogUtil myLogger = new LogUtil("application_output.log");

  public static void main(String[] args) {
    System.out.println("Klaas");
    myLogger.log(LogTypes.SUCCESS, "Printed Klaas!");
    myLogger.log(LogTypes.ERROR, "Error");
    myLogger.log(LogTypes.INFO, "Info!");
  }

}