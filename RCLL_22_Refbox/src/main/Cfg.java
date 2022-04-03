package main;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Properties;
import org.robocup_logistics.llsf_msgs.OrderInfoProtos;
import org.robocup_logistics.llsf_msgs.TeamProtos;
import org.robocup_logistics.llsf_msgs.TeamProtos.Team;

/**
 * alle Konfigurationsdaten, welche sich während eines Programmablaufs nie ändern wird in Main
 * Klasse geladen. Wird von Inhalt des XML-Files überschrieben.
 *
 * Werden mit Standard-Werten initalisiert um später dessen Änderung zu prüfen und allenfalls eine
 * Exception zu werfen
 *
 * @author alain
 * @author Adrian Hayoz
 * @author Colin Grossen
 */
public class Cfg
{
    public static int roboNr = 99;
    public static int maxRobiOnField = 3;
    public static String teamName="Solidus";
    public static String fieldWidth;
    public static String fieldHeight;
    public static String teamsOnField;
    public static String mqttBrokerIp;
    public static String robotIp;
    public static String refBoxIp;
    public static String encKey;
    public static String ipMaster;
    public static String ipRobi1;
    public static String ipRobi2;
    public static String ipRobi3;
    public static String robiNrForNavChal;
    public static List<Integer> robisB2S;
    public static EnumMap<OrderInfoProtos.Order.Complexity, Integer> numOfRobotsEachComplexity;

    public static boolean manualStartPosOn;
    public static String manualStartPos;

    public static int[] exploTargetArray;
    public static List<String> Stations = new ArrayList();

    public static Team team;

    static
    {
        File f = new File("gamePlay.xml");
        if (f.exists() && !f.isDirectory())
        {
            loadFromFile("gamePlay.xml"); //load when valid File (from robotino)
        }
    }

    private static void loadFromFile(String fileName)
    {
        try
        {
            Properties prop = new Properties();
            prop.loadFromXML(new FileInputStream(fileName));

            String teamColor = prop.getProperty("teamColor");

            if (teamColor == null || teamColor.isEmpty())
            {
                throw new NullPointerException("Team Color is invalid in GamePlay Settings");
            }
            Cfg.team = TeamProtos.Team.valueOf(teamColor.toUpperCase());

            Cfg.roboNr = Integer.parseInt(prop.getProperty("roboNr"));
            if (Cfg.roboNr < 0 || Cfg.roboNr > maxRobiOnField)
            {
                throw new Exception("Robot Number is invalid in GamePlay Settings");
            }

            if (Cfg.team == null)
            {
                throw new NullPointerException("Team Color is invalid in GamePlay Settings");
            }

            Cfg.teamName = prop.getProperty("teamName");
            if (Cfg.teamName == null || Cfg.teamName.isEmpty())
            {
                throw new NullPointerException("Team Name is invalid in GamePlay Settings");
            }

            if (Cfg.roboNr == 0)
            {
                Cfg.fieldWidth = prop.getProperty("fieldWidth");
                if (Cfg.fieldWidth == null || Cfg.fieldWidth.isEmpty())
                {
                    throw new NullPointerException("Field Width is invalid in GamePlay Settings");
                }

                Cfg.fieldHeight = prop.getProperty("fieldHeight");
                if (Cfg.fieldHeight == null || Cfg.fieldHeight.isEmpty())
                {
                    throw new NullPointerException("Field Height is invalid in GamePlay Settings");
                }

                Cfg.teamsOnField = prop.getProperty("teamsOnField");
                if (Cfg.teamsOnField == null || Cfg.teamsOnField.isEmpty())
                {
                    throw new NullPointerException("teamsOnField is invalid in GamePlay Settings");
                }
            }

            Cfg.manualStartPosOn = Boolean.parseBoolean(prop.getProperty("manualStartPosOn"));
            if (Cfg.manualStartPosOn)
            {
                System.out.println("manual StartPos is Active!");
            }

            Cfg.manualStartPos = prop.getProperty("manualStartPos");
            if ((Cfg.manualStartPosOn) && (Cfg.manualStartPos == null))
            {
                throw new Exception("manual SetupMode is Active, but no StartPos found. StartPos es 2,5; 1,5; 0");
            }

            Cfg.encKey = prop.getProperty("encKey");
            if (Cfg.encKey == null || Cfg.encKey.isEmpty())
            {
                throw new NullPointerException("Encryption key is invalid in GamePlay Settings");
            }

            Cfg.refBoxIp = prop.getProperty("refBoxIp");
            if (Cfg.refBoxIp == null || Cfg.refBoxIp.isEmpty())
            {
                throw new NullPointerException("Ref Box IP is invalid in GamePlay Settings");
            }

            Cfg.ipMaster = prop.getProperty("ipMaster");
            if (Cfg.ipMaster == null || Cfg.ipMaster.isEmpty())
            {
                throw new NullPointerException("Ref Box IP is invalid in GamePlay Settings");
            }

            Cfg.ipRobi1 = prop.getProperty("ipRobi1");
            Cfg.ipRobi2 = prop.getProperty("ipRobi2");
            Cfg.ipRobi3 = prop.getProperty("ipRobi3");
            if (Cfg.ipRobi1 == null || Cfg.ipRobi1.isEmpty()
              || Cfg.ipRobi2 == null || Cfg.ipRobi2.isEmpty()
              || Cfg.ipRobi3 == null || Cfg.ipRobi3.isEmpty())
            {
                throw new NullPointerException("Remote Robot IP is invalid in GamePlay Settings");
            }

            Cfg.mqttBrokerIp = prop.getProperty("mqttBrokerIp");
            if (Cfg.mqttBrokerIp == null || Cfg.mqttBrokerIp.isEmpty())
            {
                throw new NullPointerException("MQTT broker IP is invalid in GamePlay Settings");
            }

            Cfg.robotIp = prop.getProperty("robotIp");
            if (Cfg.robotIp == null || Cfg.robotIp.isEmpty())
            {
                throw new NullPointerException("Local robot IP is invalid in GamePlay Settings");
            }

            String exploTargets = prop.getProperty("exploTargets");
            if (Cfg.roboNr == 0 && (exploTargets == null || exploTargets.isEmpty()))
            {
                throw new NullPointerException("No explo-targets defined in settings-file");
            } else if (Cfg.roboNr == 0)
            {
                try
                {
                    String[] targets = exploTargets.split(";");
                    List<String> tempTargets = new ArrayList();
                    if (targets.length < 3)
                    {
                        throw new Exception("Not enough targets for exploration specified!");
                    }
                    for (String target : targets)
                    {
                        int x = Integer.parseInt(target.substring(0, 1));
                        int y = Integer.parseInt(target.substring(1, 2));
                        if (target.length() == 2 && x >= 1 && x <= 7 && y >= 1 && y <= 8)
                        {
                            tempTargets.add(target);
                        }
                    }
                    Cfg.exploTargetArray = new int[tempTargets.size()];

                    int i = 0;
                    for (String target : tempTargets)
                    {
                        Cfg.exploTargetArray[i] = Integer.parseInt(target);
                        i++;
                    }
                } catch (Exception e)
                {
                    throw new Exception("Error while parsing Explo-Targets... " + e.getMessage());
                }

                String mockupStations = prop.getProperty("mockupStations");
                if ((mockupStations == null || mockupStations.isEmpty()))
                {
                    throw new NullPointerException("no mockupStations defined");
                } else if (Cfg.roboNr == 0)
                {
                    try
                    {
                        String[] stationString = mockupStations.split(";");

                        for (String s : stationString)
                        {
                            Stations.add(s);
                        }

                    } catch (Exception e)
                    {
                        throw new Exception("Error while parsing mockupStations... " + e.getMessage());
                    }

                }

                try
                {
                    String robisB2Sstring = prop.getProperty("robisB2S");
                    String[] robis = robisB2Sstring.split(";");
                    Cfg.robisB2S = new ArrayList();
                    for (String robi : robis)
                    {

                        if (!robi.equalsIgnoreCase(""))
                        {
                            int robiInt = Integer.parseInt(robi);
                            if (robiInt > 0 && robiInt <= 3)
                            {
                                Cfg.robisB2S.add(robiInt);
                            }
                        }

                    }
                } catch (Exception e)
                {
                    throw new Exception("Error while parsing B2S-Robis... " + e.getMessage());
                }

                try
                {
                    String robiEachComp = prop.getProperty("numOfRobiEachComplexity");
                    String[] complexity = robiEachComp.split(";");
                    Cfg.numOfRobotsEachComplexity = new EnumMap<>(OrderInfoProtos.Order.Complexity.class);

                    if (complexity.length == 4)
                    {
                        Cfg.numOfRobotsEachComplexity.put(OrderInfoProtos.Order.Complexity.C0, Integer.parseInt(complexity[0]));
                        Cfg.numOfRobotsEachComplexity.put(OrderInfoProtos.Order.Complexity.C1, Integer.parseInt(complexity[1]));
                        Cfg.numOfRobotsEachComplexity.put(OrderInfoProtos.Order.Complexity.C2, Integer.parseInt(complexity[2]));
                        Cfg.numOfRobotsEachComplexity.put(OrderInfoProtos.Order.Complexity.C3, Integer.parseInt(complexity[3]));
                    } else
                    {
                        throw new Exception("Not for Each Complexity is a Robinumber defined!");
                    }
                } catch (Exception e)
                {
                    throw new Exception("Error while parsing Complexity-Robis... " + e.getMessage());
                }

            }

            printConfig();

        } catch (Exception ex)
        {
            System.exit(1); //Halt program
        }
    }

    private static void printConfig()
    {
        String config = "\n\n";
        config += "#########################################\n";
        config += "############ GAMEPLAY CONFIG ############\n";
        config += "#########################################\n";
        config += "# ROBO NR:\t\t" + Cfg.roboNr + "\t\t#\n";
        config += "# TEAM COLOR:\t\t" + Cfg.team + "\t\t#\n";
        config += "# TEAM NAME:\t\t" + Cfg.teamName + "\t\t#\n";
        config += "# ENC KEY:\t\t" + Cfg.encKey + "\t#\n";
        config += "# \t\t\t\t\t#\n";
        config += "# REF BOX IP:\t\t" + Cfg.refBoxIp + "\t#\n";

        if (Cfg.roboNr == 0) //Print only important IP's
        {
            config += "# FIELD WIDTH:\t\t" + Cfg.fieldWidth + "\t\t#\n";
            config += "# FIELD HEIGHT:\t\t" + Cfg.fieldHeight + "\t\t#\n";
            config += "# TEAMS ON FIELD:\t" + Cfg.teamsOnField + "\t\t#\n";
            config += "# ROBO 1 IP:\t\t" + Cfg.ipRobi1 + "\t#\n";
            config += "# ROBO 2 IP:\t\t" + Cfg.ipRobi2 + "\t#\n";
            config += "# ROBO 3 IP:\t\t" + Cfg.ipRobi3 + "\t#\n";
            config += "# \t\t\t\t\t#\n";
            config += "# B2S-Robis:\t\t" + Cfg.robisB2S.toString() + "\t\t#\n";
        } else
        {
            config += "# MASTER IP:\t\t" + Cfg.ipMaster + "\t#\n";
        }

        config += "############## GOOD LUCK ################\n";
        System.out.println(config);
    }
}
