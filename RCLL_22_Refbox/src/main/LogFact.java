package main;

import java.io.IOException;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

/**
 * Contains all Logger, so it is easy to get the right logger and set the logger level
 *
 * @author simon.buehlmann, Lukas Hofmann
 */
public class LogFact
{
    // Singleton
    private static LogFact instance;

    // Loggers
    public Logger mainLog;
    public Logger laserScannerLog;
    public Logger calcLaserLogg;
    public Logger machineLog;
    public Logger fieldCmdLog;
    public Logger prodCtrlMasterLog;
    public Logger masterLog;
    public Logger stateMachineLog;
    public Logger gripperLog;
    public Logger productionLog;
    public Logger startUpLog;
    public Logger driveLog;
    public Logger stationLog;
    public Logger mqttLog;
    public Logger ArVisuLog;

    /**
     * Zentraler Logger Factory. Diese Klasser erstellt alle benötigten Logger und setzt die
     * Loglevel
     */
    private LogFact()
    {
        // Layout
        PatternLayout detailedLayout = new PatternLayout("%d{ISO8601} %-5p [%.40t] %m %n");  //Zeitstempel, Umwandlung, Threadname, Nachricht, Zeilenumbruch

        // Appender
        ConsoleAppender consoleAppender = new ConsoleAppender(detailedLayout, "System.out");

        /**
         * Create Instance of Main Logger
         */
        //<editor-fold defaultstate="collapsed" desc="MAIN LOGGER">
        this.mainLog = org.apache.log4j.Logger.getLogger("MAIN_LOGGER");
        this.mainLog.addAppender(consoleAppender);
        this.mainLog.setLevel(org.apache.log4j.Level.ALL);

        try
        {
            this.mainLog.addAppender(new FileAppender(detailedLayout, "logs/main.log"));
        } catch (IOException ex)
        {
            this.mainLog.error("Not possible to add file appender to MAIN_LOGGER. Message: " + ex.getMessage());
        }
        //</editor-fold>

        /**
         * Create Instance of Laserscanner Logger
         */
        //<editor-fold defaultstate="collapsed" desc="LASER">
        this.laserScannerLog = org.apache.log4j.Logger.getLogger("LASERSCANNER_LOGGER");
        //this.LASERSCANNER_LOGGER.addAppender(consoleAppender);
        this.laserScannerLog.setLevel(org.apache.log4j.Level.ALL);
        try
        {
            this.laserScannerLog.addAppender(new FileAppender(detailedLayout, "logs/laserscanner.log"));
        } catch (IOException ex)
        {
            this.mainLog.error("Not possible to add fileappender to LASERSCANNER_LOGGER. Message: " + ex.getMessage());
        }
        //</editor-fold>

        /**
         * Create Instance of LaserCalc Logger
         */
        
        //<editor-fold defaultstate="collapsed" desc="CALCLASER">
        this.calcLaserLogg = org.apache.log4j.Logger.getLogger("CALCLASERLOG_LOGGER");
        //this.LASERSCANNER_LOGGER.addAppender(consoleAppender);
        this.calcLaserLogg.setLevel(org.apache.log4j.Level.ALL);
        try
        {
            this.calcLaserLogg.addAppender(new FileAppender(detailedLayout, "logs/calclaser.log"));
        } catch (IOException ex)
        {
            this.mainLog.error("Not possible to add fileappender to CALCLASER_LOGGER. Message: " + ex.getMessage());
        }
        //</editor-fold>
        /**
         * Create Instance of Machine Logger
         */
        //<editor-fold defaultstate="collapsed" desc="MACHINE LOGGER">
        this.machineLog = org.apache.log4j.Logger.getLogger("MACHINE_LOGGER");
        this.machineLog.addAppender(consoleAppender);
        this.machineLog.setLevel(org.apache.log4j.Level.ALL);
        try
        {
            this.machineLog.addAppender(new FileAppender(detailedLayout, "logs/machine.log"));
        } catch (IOException ex)
        {
            this.mainLog.error("Not possible to add fileappender to MACHINE_LOGGER. Message: " + ex.getMessage());
        }
        //</editor-fold>

        /**
         * Create Instance of Fieldcommander Logger
         */
        //<editor-fold defaultstate="collapsed" desc="FIELDCOMMANDER LOGGER">
        this.fieldCmdLog = org.apache.log4j.Logger.getLogger("FIELDCOMMANDER_LOGGER");
        this.fieldCmdLog.addAppender(consoleAppender);
        this.fieldCmdLog.setLevel(org.apache.log4j.Level.ALL);
        try
        {
            this.fieldCmdLog.addAppender(new FileAppender(detailedLayout, "logs/fieldcommander.log"));
        } catch (IOException ex)
        {
            this.mainLog.error("Not possible to add fileappender to FIELDCOMMANDER_LOGGER. Message: " + ex.getMessage());
        }
        //</editor-fold>

        /**
         * Create Instance of Master Logger
         */
        //<editor-fold defaultstate="collapsed" desc="Package Master LOGGER">
        this.masterLog = org.apache.log4j.Logger.getLogger("Master_LOGGER");
        this.masterLog.addAppender(consoleAppender);
        this.masterLog.setLevel(org.apache.log4j.Level.ALL);
        try
        {
            this.masterLog.addAppender(new FileAppender(detailedLayout, "logs/master.log"));
        } catch (IOException ex)
        {
            this.masterLog.error("Not possible to add fileappender to Master_LOGGER. Message: " + ex.getMessage());
        }
        //</editor-fold>

        /**
         * Create Instance of StateMachine Logger
         */
        //<editor-fold defaultstate="collapsed" desc="StateMachine">
        this.stateMachineLog = org.apache.log4j.Logger.getLogger("StateMachine_LOGGER");
        this.stateMachineLog.addAppender(consoleAppender);
        this.stateMachineLog.setLevel(org.apache.log4j.Level.ALL);
        try
        {
            this.stateMachineLog.addAppender(new FileAppender(detailedLayout, "logs/statemachine.log"));
        } catch (IOException ex)
        {
            this.stateMachineLog.error("Not possible to add fileappender to StateMachine_LOGGER. Message: " + ex.getMessage());
        }
        //</editor-fold>

        /**
         * Create Instance of Gripper Logger
         */
        //<editor-fold defaultstate="collapsed" desc="GRIPPER LOGGER">
        this.gripperLog = org.apache.log4j.Logger.getLogger("GRIPPER_LOGGER");
        this.gripperLog.addAppender(consoleAppender);
        this.gripperLog.setLevel(org.apache.log4j.Level.ALL);
        try
        {
            this.gripperLog.addAppender(new FileAppender(detailedLayout, "logs/gripper.log"));
        } catch (IOException ex)
        {
            this.mainLog.error("Not possible to add fileappender to GRIPPER_LOGGER. Message: " + ex.getMessage());
        }
        //</editor-fold>

        /**
         * Create Instance of Production Logger
         */
        //<editor-fold defaultstate="collapsed" desc="PRODUCTION LOGGER">
        this.productionLog = org.apache.log4j.Logger.getLogger("PRODUCTION_LOGGER");
        this.productionLog.addAppender(consoleAppender);
        this.productionLog.setLevel(org.apache.log4j.Level.ALL);
        try
        {
            this.productionLog.addAppender(new FileAppender(detailedLayout, "logs/production.log"));
        } catch (IOException ex)
        {
            this.mainLog.error("Not possible to add fileappender to PRODUCTION_LOGGER. Message: " + ex.getMessage());
        }

        //</editor-fold>
        /**
         * Create Instance of Drive Logger
         */
        //<editor-fold defaultstate="collapsed" desc="DRIVE LOGGER">
        this.driveLog = org.apache.log4j.Logger.getLogger("DRIVE_LOGGER");
        this.driveLog.addAppender(consoleAppender);
        this.driveLog.setLevel(org.apache.log4j.Level.ALL);
        try
        {
            this.driveLog.addAppender(new FileAppender(detailedLayout, "logs/drive.log"));
        } catch (IOException ex)
        {
            this.mainLog.error("Not possible to add fileappender to DRIVE_LOGGER. Message: " + ex.getMessage());
        }
        //</editor-fold>
        /**
         * Create Instance of StartUp Logger
         */
        //<editor-fold defaultstate="collapsed" desc="STARTUP LOGGER">
        this.startUpLog = org.apache.log4j.Logger.getLogger("STARTUP_LOGGER");
        this.startUpLog.addAppender(consoleAppender);
        this.startUpLog.setLevel(org.apache.log4j.Level.ALL);
        try
        {
            this.startUpLog.addAppender(new FileAppender(detailedLayout, "logs/startup.log"));
        } catch (IOException ex)
        {
            this.startUpLog.error("Not possible to add fileappender to STARTUP_LOGGER_LOGGER. Message: " + ex.getMessage());
        }
        //</editor-fold> 

        /**
         * Create Instance of STATION Logger
         */
        //<editor-fold defaultstate="collapsed" desc="STATION_LOGGER">
        this.stationLog = org.apache.log4j.Logger.getLogger("STATION");
        this.stationLog.addAppender(consoleAppender);
        this.stationLog.setLevel(org.apache.log4j.Level.ALL);
        try
        {
            this.stationLog.addAppender(new FileAppender(detailedLayout, "logs/Station.log"));
        } catch (IOException ex)
        {
            this.stationLog.error("Not possible to add fileappender to STATION_LOGGER. Message: " + ex.getMessage());
        }
        //</editor-fold> 

        /**
         * Create Instance of MQTT Logger
         */
        //<editor-fold defaultstate="collapsed" desc="MQTT_LOGGER">
        this.mqttLog = org.apache.log4j.Logger.getLogger("MQTT");
        this.mqttLog.addAppender(consoleAppender);
        this.mqttLog.setLevel(org.apache.log4j.Level.ALL);
        try
        {
            this.mqttLog.addAppender(new FileAppender(detailedLayout, "logs/Mqtt.log"));
        } catch (IOException ex)
        {
            this.mqttLog.error("Not possible to add fileappender to MQTT_LOGGER. Message: " + ex.getMessage());
        }
        //</editor-fold>   
        /**
         * Create Instance of ArVisu Logger
         */
        //<editor-fold defaultstate="collapsed" desc="Package Ar Visu LOGGER">
        this.ArVisuLog = org.apache.log4j.Logger.getLogger("ArVisu_LOGGER");
        this.ArVisuLog.addAppender(consoleAppender);
        this.ArVisuLog.setLevel(org.apache.log4j.Level.ALL);
        try
        {
            this.ArVisuLog.addAppender(new FileAppender(detailedLayout, "logs/ArVisu.log"));
        } catch (IOException ex)
        {
            this.ArVisuLog.error("Not possible to add fileappender to ArVisu_LOGGER. Message: " + ex.getMessage());
        }
        //</editor-fold>
    }

  
    /**
     * Station Logger holen
     *
     * @return Logger Instanz
     */
//    public static Logger getStation()
//    {
//        return get().stationLog;
//    }

    /**
     * Station Logger holen
     *
     * @return Logger Instanz
     */
    public static Logger getMqttLog()
    {
        return get().mqttLog;
    }

    /**
     * LogFact Instanz holen
     *
     * @return LogFact Instanz
     */
    public static LogFact get()
    {
        if (instance == null)
        {
            instance = new LogFact();
        }
        return instance;
    }

}
