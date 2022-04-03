package comm;

/**
 * MQTT TPC Constants
 *
 * @author roa2
 */
public interface TPC
{
    /*
     * Kommentare sind folgendermassen aufgebaut:
     * Topicverwendung / Sender, Empfänger / Actions / Rückgabewert
     * - bedeutet, dass keine Action erlaubt ist, oder kein Rückgabewert zu erwarten ist
     * Übergabewerte sind IMMER Strings (Ausnahme Station, Stationmap)
     * Arrays und oder mehrere Infos werden immer folgendermassen geschickt: "data;data;..."
     */
    //Cameras
    String TAGCAM_DATA = "robo/tagCam/data"; //Array mit Tag Infos / Sender: TagCam Empfänger: * / - / String[int, double]
    String TAGCAM_ACTIONS = "robo/tagCam/actions"; //Actions für Tagcam / Sender: * Empfänger: TagCam / "bisch_du_da", "get" / "ja_i_bi_da"

    //SignalLampe
    String LIGHT_SIG = "robo/light"; //Signallampen ansteuerung 

    //PowerManagement
    String PWR_MANAGEMENT = "robo/pwr";
    
    String GRASPING_CHALLENGE = "robo/graspingChallenge/active";

    //Refbox
    String PHASE = "robo/refbox/phase"; //Aktuell laufende Phase von Refbox / Sender: Refbox Empfänger: Statemachine / - / - "PRE_GAME", "SETUP", "EXPLORATION", "PRODUCTION"
    String STATE = "robo/refbox/state"; //Aktueller Status der Refbox / Sender: Refbox Empfänger: Statemachine / - / - "RUNNING", "PAUSE"
    String REFMACHINE = "robo/refbox/machines"; //entdeckte Maschinen / Sender: ExploCtrl Empfänger: ComRefBox / Station / -
    String REFSETUP = "robo/refbox/setup"; //Setup-Messages / Sender: Element Empfänger: Machine / Msg / -
    String GAME = "robo/refbox/game"; //Sendet alle GameInfos, die von der Refbox empfangen werden    

    //Statemachine
    String ONFIELD = "field/stateMachine/onfield";  //Sendet "true" sobal der Robi auf dem Feld ist
    String MACHINEPHASE = "robo/statemachine/phase"; //Phase der Statemachine /Sender: Statemachine Receiver: * / - / "SETUP", "EXPLORATION", "PRODUCTION"

    //Exploration
    String STARTSIGNAL_GO_ON_FIELD = "robo/job/startsignalGoOnField"; //Startsignal das die Robis nacheinanser aufs Feld fahren/ "goRobi1", "goRobi2", "goRobi3",
    String ROBI_ON_FIELD = "robo/job/robiOnField"; //Signal sobal der Robi auf dem Feld ist und der nächste herausfahren kann / "onFieldRobi1", "onFieldRobi2", "onFieldRobi2",
    String GO_TO_START = "robo/drive/goToStart"; //Sendet "go" wennder Robi aufs Feld soll
    //   String AVOIDDETECTION = "robo/drive/avoidDetection";  //Sendet true wenn avoidingDetector() gibt true zurück
    String NEW_EXPLOTARGET = "robo/explo/newTarget";  //Auf diesem Topic sendet der Explo Master den Robis die Targets (anzufahrende Positionen)
    String SEND_EXPLOTARGET = "master/explo/sendTarget"; //Auf diesem Topic erhält der ExploControllMaster die Anfragen für neue Explo-Ziele --> Format: Befehl,RobiNr also z.B. "send,2"
    String FOUND_TAGS_EXPLO = "master/explo/foundTags"; // Auf dieses Topic werden alle gefundenen Tags gesendet und von dort in einer Liste verwaltet --> ID, Winkel, Full_Delaration, StationName, RobiX,RobiY
    String ROBI_IN_EXPLO = "robo/job/robiInExplo";
    
    //Produktion
    String PROD_STEP = "robo/prod/step"; //Produktionsschritt wird an Robo gesendet / Sender: ProductControllMaster Empfänger: ProductionControl / - / -
    String PROD_READY = "field/prod/ready"; //Robo braucht neuen Produktionsschritt / Sender und Empfänger: ProductControllMaster, ProductionControl / - / -
    String RB_ORDERS = "field/refbox/orders";//Produktbestellungen von der RefBox / Sender: ProtoMsgHandler Empfänger: ProductControllMain / - / Msg
    String RB_MACHINES = "field/refbox/machines"; //Maschinen von Refbox / Sender: ProtoMsgHandler Empfänger: ProductControllmain / - / Msg
    String RB_RING_INFO = "field/refbox/ringInfo"; //Informationen zu den Additional Bases nach RingColor

    //Production neu MASTER
    String IN_POS_ROBI1 = "master/prod/robi1/inpos"; //Rückmeldung von Robi1 zu Master / Sender: ProductionControl Empfänger: OrderProcessing / - / "ready", "messageSended", "gegriffen", "plaziert"
    String IN_POS_ROBI2 = "master/prod/robi2/inpos"; //Rückmeldung von Robi2 zu Master / Sender: ProductionControl Empfänger: OrderProcessing / - / "ready", "messageSended", "gegriffen", "placed"
    String IN_POS_ROBI3 = "master/prod/robi3/inpos"; //Rückmeldung von Robi3 zu Master / Sender: ProductionControl Empfänger: OrderProcessing / - / "ready", "messageSended", "gegriffen", "plaziert"
    String BLOCK_ZONE_ROBI1 = "master/prod/robi1/blockedzone"; //Eine Zone blockieren von Robi 1/ Sender: ProductionControl Receiver: ProductionControllMaster / "true", "false" / "1;M35;true"
    String BLOCK_ZONE_ROBI2 = "master/prod/robi2/blockedzone"; //Eine Zone blockieren von Robi 2/ Sender: ProductionControl Receiver: ProductionControllMaster / "true", "false" / "2;M35;true"
    String BLOCK_ZONE_ROBI3 = "master/prod/robi3/blockedzone"; //Eine Zone blockieren von Robi 3/ Sender: ProductionControl Receiver: ProductionControllMaster / "true", "false" / "3;M35;true"
    String REBLOCK_BLOCK = "master/prod/reblockZone"; //Zonen entblockieren an alle Robis / "M35;false"
    String REQUEST_TARGET = "robo/prod/request"; //Target anforderung

    //Production neu ROBOTINOS
    String REFBOX_MESSAGE = "robo/prod/refboxMessage"; //Meldung die an die RefBox gesendet wird, um Produkt zu bestellen /Sender: OrderProcessing Empfänger: ProductionControl / - / -
    String PICK_COORD = "robo/prod/pickCoord"; //Coord der ersten Maschine /Sender: OrderProcessing Empfänger: ProductionControl / - / "#.#;#.#;#" (Standardformat Coord)
    String PLACE_COORD = "robo/prod/placeCoord"; //Coord der zweiten Maschine /Sender: OrderProcessing Empfänger: ProductionControl / - / #.#;#.#;#" (Standardformat Coord)
    String MACHINE_POS = "robo/prod/machinePos"; //Anzufahrende Position MPS /Sender: OrderProcessing Empfänger: ProductionControl / "0;pick" "0;place" / "0", "1", "2", "3", "4", "5"
    String NEW_COORD = "robo/prod/neueCoord"; //Abfrage ob es eine zweite Station gibt (capStation KEINE) /Sender: OrderProcessing Empfänger: ProductionControl / - / "JA"
    String SPECIAL_TASK = "robo/prod/specialTask"; //Senden des Speziellen Arbeitsschrittes /Sender: OrderProcessing Empfänger: ProductionControl / - / "sameMachine"
    String PICKED_PLACED = "robo/prod/pickandrelease"; //Wenn der greif/patzier Vorgang abgeschlossen ist /Sender: Drive Empfänger: ProductionControl / - / "ready"

    //Production Splittet Jobs
    String APPROACH_MASCHINE_PICK = "robo/prod/approach/pickandrelease";//Meldung das der Robi an eine Station fahren soll und dort nehmen oder loslassen soll/Sender: ProductionControl Empfänger:Drive /"0", "1", "2", "3", "4", "5" ; "false"oder"true" für Pick
    String SPECIAL_JOB = "robo/prod/spezialmove";//Wenn der Robi an der Cap Station ist und er nur seitlich weiter fahren muss. /Sender: ProductionControl Empfänger:Drive /"0", "1", "2", "3", "4", "5" ;"false"oder "true" für Pick
    //   String MOVE_BACKWARD = "robo/prod/movebackward";

    //Greiferdaten
    String GRIPPER_EVENT = "robo/gripper/event"; //Befehle an den Greifer. Definiert unter gripper.GripperEvents / Sender: ComController Empfänger: WAGO / int / -
    String GRIPPER_STATUS = "robo/gripper/status"; //Statusdaten vom Greifer. Definiert unter gripper.GripperStatus / Sender: WAGO Empfänger: ComController / - / int
    String GRIPPER_LOG = "robo/gripper/log"; //Log-Daten vom Greifer

    //Maintenance
    String ROBO_INFO = "field/master/inMaintenance";  //BEfindet sich der Rotino in der Maintenance wird dies auf dieses Topic gesendet    
    //Legende:
    //s=status (die validierte anforderung)
    //i=intent (anforderung)
    //e=event (Ereigniss des Services/Dives)detach

    //Drive
    String ACTPOS = "robo/drive/s/actual"; //Aktuelle Position / Sender: Positioncontroller Receiver: * / - / -
    String TARGET = "robo/drive/i/target"; //Senden einer neuen Koordinate, die als nächstes angefahren werden muss / Sender: * Receiver: Pathfinder / Coord Objekt / - (feedback auf inpos)
    String FINAL = "robo/drive/i/aligntarget"; //Senden der endügltigen Koordinate, zum Station anfahren
    String TOGGLEAVOID = "robo/drive/i/toggleavoid"; //WayAnalyser ein und ausschalten/ Sender: * Receiver: WayAnalyser / "true", "false"
    String BLOCKZONE = "robo/drive/i/blockzone"; //Eine Zone blockieren/ Sender: * Receiver: PathFinder / "true", "false" / "M35;true"
    String SENDNEWTARGET = "robo/drive/s/newtarget"; //Anfordern eines neuen Ziels/ Sender: PathFinder Receiver: Drive / "true", "false"
    String NEWPATH = "robo/drive/i/newpath"; //neuer Pfad wurde erstellt/ Sender:PathFinder Receiver:Drive /"true","false"
    String PIDCTRL = "robo/drive/i/setpid"; //Neue PID-Settings für Achse
    String DRIVE_STATE = "robo/drive/s/state"; //Aktueller Status des Drives
    String AUTO_RESET_POS = "robo/drive/i/autoResetPos";//PositionReset aufrufen
    String STATION_APPROACH = "robo/drive/i/approach"; //Annäherungsziel für blockierte Station(Zielkoordinate gleich wie die Koordinate des PICK or PLACE) passiert nur wenn Stationsseite geblockt ist

    //Status hinzufuegen
    String SUBTARGET = "robo/drive/i/nextturnpoint";//Senden des naechsten Wegpunkt. Sender: Pathfinder/ Receiver: Drive/ Coord Objekt/
    String INSUBPOS = "robo/drive/e/turnpointreached";//Senden wen er das auf der Höhe des nächsten Feldes ist./ Sender: Drive Receiver: Pathfinder/ String "true" "false"

    //Status hinzufuegen
    String INPOS = "robo/drive/e/inpos"; //Empfangen von Signal, dass der Robi in der Position ist /Sender: Pathfinder Receiver: * / - / "ready", "fal"
    String ACTIONS = "robo/drive/i/action"; //Aktionen die das Drive ausführen muss /Sender: * Receiver Drive / "initPos, follow, play, pause, stop, goToTarget, sideways, lineways, forwAppr, stationAlign, Go2MidMachine" / -

    //Status hinzufuegen
    //   String INFRADIST = "robo/infra/0/e/dist"; //Infrarotdistanz auf vordestem Sensor / Sender und Empfänger: WayAnalyzer, * / "get" / String[double]
    //Sensor 0 (neue Hierarchie)
    String RESETPOS = "robo/drive/i/resetpos"; //Setzt die Odometrie auf den gewÃ¼nschten Wert (Schlupf korrigieren) / Sender: * Receiver: Positioncontroller / Coord Objekt / -

    //Status hinzufuegen
    String SIDEWAYS = "robo/drive/i/sideways"; //SeitwÃ¤rts verfahren / Sender: * Receiver: Drive / left;distance[m], right; distance[m] / - (feedback auf inpos)
    String LINEWAYS = "robo/drive/i/lineways"; //Inline verfahren / Sender: * Receiver: Drive / forward;distance[m], backward; distance[m] / - (feedback auf inpos)
    String TURNAROUND = "robo/drive/i/turnaround";// die gewuenste Anzahl Grad, in eine Richtung drehen. Sender: * Receiver: Drive / distance[grad];left , distance[grad];/ - (feedback auf inpos)
    String EXPLOTURN = "robo/drive/i/exploturn";//Dreht sich komplett um eigene Achse (Richtung nicht definiert)

    //Redundant mit Actions
    String INIT = "robo/drive/pos/e/init"; //initPos finished / Sender: Drive Receiver: * / - / "ready"
    String LASER_ACTIONS = "robo/drive/laseractions"; //Actions an Laser / Sender * Receiver: Laserscanner / "filter", "calcDifPoint", "go2MidMachine" / -
    String LASER_STATUS = "robo/drive/laserstatus"; //Resultate von Laser / Sender: Laserscanner Receiver: * / - / "noLine", "lineDetected", "notDetected", "inMidStation"
    String LASER_DATA = "robo/drive/laserdata"; //berechnete x und y diferenz von Zielpos zu IstPos / Sender: Laserscanner Receiver: Drive / - / Coord Object
    String LOAD_CONFIG = "robo/drive/load"; //Die Properties werden erneut geladen.

    //Visualisierung
    String VISU = "visu/map";         //Visualierung der Map / Daten des PathFinder
    String VISU_PRODUCTS = "visu/products"; //Senden der Productliste
    String VISU_ROBI_JOB = "visu/robis/jobs"; //Senden der aktuellen Jobs des Robis

    //Distanzen des Laser
    String LASERDISTANCE = "robo/drive/laserDistance"; //Gibt ein String mit allen 270 Laserdistanzen zurück 
    String ENABLE_LASERDISTANCE = "robo/drive/enableLaserDistance"; //Freigabe das die Laserdistanzen gesendet werden /* true oder false senden
    //String RUN_LASER_360_GRAD ="robo/drive/runLaser360Grad";    //Schaltet den 360° Laser ein Payload: "true" / "false"
    
    //Nav Challange
    String NAV_CHALLENGE_ZONES = "robo/refbox/navZones"; //Zonen für die Navigation Challange
    
    
    //AR-Spielfeld
    String AR_MACHINES="robo/AR/machines";//Die Stationen werden auf dem AR-Spielfeld gesetzt.
    String AR_ROBIS_POSITION="robo/AR/robis/position";//Robis werden auf dem Feld gesetzt.
    String AR_GAME="robo/AR/game"; //Beinhaltet die Infos zum Game
    String AR_BATTERY="robo/AR/battery"; //Beinhaltet die Infos zur Batterie
    String AR_JOBS="robo/AR/jobs"; //Beinhaltet die Jobs mit ihrem dazugehörigen Produkt
}
