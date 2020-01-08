package utilities.reportRelated;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;



public class Log4jManager {
    // make sure use static and LogManager when using log4j 2
    public static Logger log = LogManager.getLogger(Log4jManager.class);
    
    
    /**
     * Logs info text messages to the Log file & Extent report
     *
     * @param message
     */
    public static void info(String message) {
   	 log.info(message);
   	 
    }

    /**
     * Logs debug text messages to the Log file
     *
     * @param message
     */
    public static void debug(String message) {
   	 log.debug(message);
    }
    
    /**
     * Logs error text messages to the Log file
     *
     * @param message
     */
    public static void error(String message) {
   	 log.error(message);
    }
}




