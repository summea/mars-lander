import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import menus.Menu;
import menus.MenuComponent;
import menus.MenuItem;
import menus.MenuSystem;
import robot.Robot;
import russia.KlasperskySoftwareExtension;
import russia.LuxsoftSoftwareExtension;
import samples.AtmosDataSampleFactory;
import samples.DataSampleFactory;
import samples.Sample;
import samples.TerraDataSampleFactory;
import taiwan.ASISComponent;
import taiwan.PlastekComponent;
import users.AdminInvocationHandler;
import users.PresidentInvocationHandler;
import users.User;
import users.UserImpl;

import commands.CommandData;
import commands.CurrentCommandDisplay;
import commands.Move;
import commands.MoveEastCommand;
import commands.MoveNorthCommand;
import commands.MoveNorthEastCommand;
import commands.MoveNorthWestCommand;
import commands.MoveSouthCommand;
import commands.MoveSouthEastCommand;
import commands.MoveSouthWestCommand;
import commands.MoveWestCommand;
import commands.RobotRemoteControl;
import components.CameraComponent;
import components.CameraFilter;
import components.CameraLens;
import components.Cannon;
import components.Component;
import components.FlashlightComponent;
import components.ProjectorComponent;
import components.TaiwaneseComponentAdapter;

import database.Database;
import database.DatabaseConnection;
import extensions.NASASoftwareExtension;
import extensions.RussianSoftwareExtensionAdapter;
import extensions.SoftwareExtension;
import facades.RobotSelfieFacade;

// CREATIONAL PATTERNS:
//  x PN1: singleton: database connection
//  x PN2: factory method: robot sample types
//    DataSampleFactory             (PizzaStore)
//    |- AtmosDataSampleFactory     (NYPizzaStore)
//    |- TerraDataSampleFactory     (ChicagoPizzaStore)

//  x PN3: abstract factory: robot sample supplies
//    SampleElementFactory          (PizzaIngredientFactory)
//    |- SoilSampleElementFactory   (NYPizzaIngredientFactory)
//       |- createTestStrip()
//       |- createTestSolution()
//    |- AirSampleElementFactory    (ChicagoPizzaIngredientFactory)
//       |- createTestStrip()
//       |- createTestSolution()
//
//    Interface TestStrip
//    |- BigTestStrip
//    |- LittleTestStrip
//    Interface TestSolution
//    |- MicroTestSolution
//    |- MacroTestSolution

// BEHAVIORAL PATTERNS:
//  x PN4: template method: component framework
//  x PN5: iterator: loop through menu items (from the composite pattern)
//  x PN6: command: macro commands? mars lander system control panel
//  x PN7: observer: tracker (noting current location on a display... mars lander system -> robot)
//            -or- sending commands to robot from mars lander system
//  x PN8: state: robot current state (available/get-command/process-command/run-command)
//  x PN9: strategy: behavior of components? (lens/radar behavior)

// STRUCTURAL PATTERNS:
//  x PN10: decorator: adding additional functionality to a component?
//      lenses and filters for camera
//  x PN11: proxy: user permissions/authentication (admin/president)
//  x PN12: composite: some sort of menu system -- connect this to command pattern
//  x PN13: facade: robot selfie photo
//  x PN14: adapter: Russian software adapter / Taiwanese software adapter

// REQUIREMENTS:
// software will be uploaded to the robot in transit
//   * Robot.addExtension(), Robot.removeExtension(), Robot.listExtensions()
// do not know exactly what sequences we will want the robot to do,
//   so we need a framework for software design
//   * RoboScript  
// robot should be able to reconfigure itself for different tasks
//          using pieces from itself
//   * Robot.addComponent(), Robot.removeComponent(), Robot.listComponents(), Robot.equipComponent()
// robot should be able to remember the images of things it has seen
//   and use a behavioral Artificial Intelligence to make decisions
//   - whether or not to take pictures
//   - has it seen an object or not
//   - has it already covered an area
//   * Robot.look()
// decisions the Robot makes are based on its current state
//          (you must define this in the aforementioned framework and in the AI)
//   * State
// Some software has been purchased from the Russians and MUST be used
//   * RussianSoftwareExtensionAdapter
// Many of the components are built in Taiwan and have supplied software which also must be used
//   * TaiwaneseComponentAdapter
// robot must be able to check all of its components quickly in a diagnostic
//   * Robot.listComponents()
// NASA must be able to take control of the robot from a computer on Earth or the space station
//   * Robot.setCurrentController()
// The President wants to control the robot from a smartphone
//   * Robot.setCurrentController()
// the robot must be able to move along the Martian surface
//   * Robot.move()
// the robot must be able to take and send back images
//   * Robot.photoProcess()
// the robot must be able perform more commands that NASA thinks of later
//   * MarsLanderSystem.sendRemoteCommand(), Robot.getRemoteCommand()


public class MarsLanderSystem {
    
    private static String currentUserPermissionLevel = "guest";
    private static Hashtable<String, User> userDB = new Hashtable<String, User>();
    
    public static User getAdminProxy(User user) {
        
        return (User) Proxy.newProxyInstance(
                user.getClass().getClassLoader(),
                user.getClass().getInterfaces(),
                new AdminInvocationHandler(user));
    }
    
    public static String getCurrentUserPermissionLevel() {
        return currentUserPermissionLevel;
    }
    
    public static User getPresidentProxy(User user) {
        
        return (User) Proxy.newProxyInstance( 
                user.getClass().getClassLoader(),
                user.getClass().getInterfaces(),
                new PresidentInvocationHandler(user));
    }

    public static User getUserFromDatabase(String name) {
        return (User)userDB.get(name);
    }
    
    public static void initializeUserDatabase() {
        User admin = new UserImpl();
        admin.setName("Admin");
        admin.setPermissions("admin");
        userDB.put(admin.getName(), admin);
        
        User president = new UserImpl();
        president.setName("President");
        president.setPermissions("president");
        userDB.put(president.getName(), president);
    }

    public static void main(String[] args) {
        
        final int EMPTY = 0;
        final int ROCK = 1;
        final int SCULPTURE = 2;
        
        final int PLANET_WIDTH = 4;
        final int PLANET_HEIGHT = 4;
        
        // build planet
        int planet[][] = new int[PLANET_WIDTH][PLANET_HEIGHT];
        
        // init planet
        for (int i = 0; i < PLANET_WIDTH; i++)
            for (int j = 0; j < PLANET_HEIGHT; j++)
                planet[i][j] = EMPTY;
        
        planet[1][1] = ROCK;
        planet[2][2] = SCULPTURE;
        
        // display planet
        System.out.println("Mars: Planet View");
        System.out.println("-----------------");
        for (int i = 0; i < PLANET_WIDTH; i++) {
            for (int j = 0; j < PLANET_HEIGHT; j++) {
                System.out.print(planet[i][j]);
            }
            System.out.println();
        }
        
        System.out.println();
        
        // prepare default components
        ArrayList<String> components = new ArrayList<String>();
        components.add("camera");
        components.add("mapper");
        components.add("scanner");
        
        // init default components
        CameraComponent cameraComponent = new Cannon();
        FlashlightComponent flashlightComponent = new FlashlightComponent();
        ProjectorComponent projectorComponent = new ProjectorComponent();
        
        // init robot
        int startingX = 1;
        int startingY = 2;
        Robot bot = new Robot("Norris", components, startingX, startingY);

        
        System.out.println("Mars Lander System");
        System.out.println("------------------");
        System.out.println("Robot Name: " + bot.getName());
        
        System.out.println("\n ...............");
        System.out.println(": initial tests :");
        System.out.println(" ```````````````");

        System.out.println("\n+ test init bot localhost database connection:");
        bot.initDB();
        
        System.out.println("\n+ test bot controller change:");
        bot.setCurrentController(bot.getName());
        System.out.println("\ncurrently controlling robot: " + bot.getCurrentController());        
        
        System.out.println("\n+ test list installed software extensions:");
        bot.listExtensions();
        
        System.out.println("\n+ test adding software extensions:");
        bot.addExtension("additional software");
        bot.addExtension("even more software");
        
        System.out.println("\n+ test removing software extensions:");
        bot.removeExtension("additional software");
        
        System.out.println("\n+ test list installed software extensions:");
        bot.listExtensions();
        
        System.out.println("\n+ test list installed components:");
        bot.listComponents();
        
        System.out.println("\n+ test equip camera component:");
        bot.equipComponent("camera");
        
        System.out.println("\n+ test component becomes damaged:");
        bot.setComponentStatus("scanner", "damaged");
        
        System.out.println("\n+ test list installed components:");
        bot.listComponents();
        
        System.out.println("\n+ test equip damaged scanner component:");
        bot.equipComponent("scanner");
        
        System.out.println("\n+ test currently equipped component:");
        System.out.println(bot.getCurrentlyEquippedComponent());
        
        System.out.println("\n- test equip NON-included flashlight component:");
        bot.equipComponent("flashlight");
        
        System.out.println("\n+ test currently equipped component:");
        System.out.println(bot.getCurrentlyEquippedComponent());
        
        System.out.println("\n+ test remote commands:");
        sendRemoteCommand(bot, "test remote commands");
        
        System.out.println("\n+ test photo process:");
        bot.photoProcess();

        
        System.out.println("\n\n ...............");
        System.out.println(": pattern tests :");
        System.out.println(" ```````````````\n");
        
        // PN3: ABSTRACT FACTORY PATTERN
        // PN2: FACTORY METHOD
        System.out.println("== PN3: ABSTRACT FACTORY PATTERN ==");
        System.out.println("== PN2: FACTORY METHOD ==");
        System.out.println("\n+ test abstract factory:");
        DataSampleFactory atmosDataSampleFactory = new AtmosDataSampleFactory();
        DataSampleFactory terraDataSampleFactory = new TerraDataSampleFactory();
 
        Sample sample = atmosDataSampleFactory.collectSample("quickscan");
        System.out.println("collecting a sample: \n" + sample);
        
        sample = atmosDataSampleFactory.collectSample("fullscan");
        System.out.println("collecting a sample: \n" + sample);
        
        sample = terraDataSampleFactory.collectSample("fullscan");
        System.out.println("collecting a sample: \n" + sample);
        
        sample = terraDataSampleFactory.collectSample("fullscan");
        System.out.println("collecting a sample: \n" + sample);
        
        // PN10: DECORATOR PATTERN
        System.out.println("\n== PN10: DECORATOR PATTERN ==");
        System.out.println("\n+ test camera equipment:");
        System.out.println("camera type: " + cameraComponent.getDescription());
        
        cameraComponent = new CameraLens(cameraComponent);
        System.out.println("\nadding camera lens...");
        System.out.println("camera type: " + cameraComponent.getDescription());
        
        cameraComponent = new CameraFilter(cameraComponent);
        System.out.println("\nadding camera filter...");
        System.out.println("camera type: " + cameraComponent.getDescription());
        
        
        System.out.println("\n+ test moves:\n");
        
        // move northward to rock
        System.out.println(bot.move(8));
        System.out.println(">>> " + bot.look(planet[bot.getX()][bot.getY()]) + " <<<\n");
        
        // move eastward to empty space
        System.out.println(bot.move(6));
        System.out.println(">>> " + bot.look(planet[bot.getX()][bot.getY()]) + " <<<\n");
        
        // move southward to sculpture
        System.out.println(bot.move(2));
        System.out.println(">>> " + bot.look(planet[bot.getX()][bot.getY()]) + " <<<\n");
        
        // move westward to empty space
        System.out.println(bot.move(4));
        System.out.println(">>> " + bot.look(planet[bot.getX()][bot.getY()]) + " <<<\n");
        
        // move northward to same rock
        System.out.println(bot.move(8));
        System.out.println(">>> " + bot.look(planet[bot.getX()][bot.getY()]) + " <<<\n");
        
        System.out.println("\nlocations visited history:\n");
        System.out.println(bot.getLocationData());
        
        System.out.println("\n+ test set controller/operator:");
        bot.setCurrentController("NASA");
        
        System.out.println("\n+ test presidential BygoneBerry Phone control:");
        bot.setCurrentController("President");
        System.out.println("currently controlling robot: " + bot.getCurrentController());
        
        
        // PN14: ADAPTER PATTERN
        System.out.println("\n\n== PN14: ADAPTER PATTERN ==");
        System.out.println("\n+ test software extension break-out:");
        SoftwareExtension swex = new NASASoftwareExtension();
        swex.install();
        
        KlasperskySoftwareExtension rswexOne = new KlasperskySoftwareExtension();
        SoftwareExtension rswexAdapterOne = new RussianSoftwareExtensionAdapter(rswexOne);
        rswexAdapterOne.install();
        
        LuxsoftSoftwareExtension rswexTwo = new LuxsoftSoftwareExtension();
        SoftwareExtension rswexAdapterTwo = new RussianSoftwareExtensionAdapter(rswexTwo);
        rswexAdapterTwo.install();        
        
        System.out.println("\n+ test component break-out:");
        ASISComponent tcOne = new ASISComponent();
        Component tcAdapterOne = new TaiwaneseComponentAdapter(tcOne);
        tcAdapterOne.install();
        
        PlastekComponent tcTwo = new PlastekComponent();
        Component tcAdapterTwo = new TaiwaneseComponentAdapter(tcTwo);
        tcAdapterTwo.install();
        
        
        // PN1: SINGLETON PATTERN
        System.out.println("\n\n== PN1: SINGLETON PATTERN ==");
        
        Database db = new Database();
        DatabaseConnection dbConn = DatabaseConnection.getInstance();
        
        dbConn.init("db-server", db.getDB());
        
        List<String> newValues = new ArrayList<String>();
        newValues.add("test-data-a");
        newValues.add("test-data-b");
        newValues.add("test-data-c");
        db.insert(newValues);
        
        System.out.println(db.select(1));   // select first row of database
        
        
        // PN4: TEMPLATE METHOD PATTERN
        System.out.println("\n\n== PN4: TEMPLATE METHOD PATTERN ==");
        // PN9: STRATEGY PATTERN
        System.out.println("== PN9: STRATEGY PATTERN ==");
        System.out.println("\n+ test initializing component frameworks...");
        bot.initRobotFramework();
        
        
        // PN8: STATE PATTERN
        System.out.println("\n\n== PN8: STATE PATTERN ==");
        System.out.println("\n+ test current state...");        
        bot.available();
        bot.getCommand();
        bot.processCommand();
        bot.runCommand();
        
        
        // PN7: OBSERVER PATTERN
        System.out.println("\n\n== PN7: OBSERVER PATTERN ==");
        System.out.println("\n+ test current command display...");
        CommandData commandData = new CommandData();
        
        
        @SuppressWarnings("unused")
        CurrentCommandDisplay currentCommandDisplay = new CurrentCommandDisplay(commandData);
        
        // PN6: COMMAND PATTERN
        System.out.println("\n\n== PN6: COMMAND PATTERN ==\n");
        RobotRemoteControl remoteControlButtons = new RobotRemoteControl();
        Move move = new Move();
        MoveNorthCommand moveNorth = new MoveNorthCommand(move);
        MoveNorthEastCommand moveNorthEast = new MoveNorthEastCommand(move);
        MoveEastCommand moveEast = new MoveEastCommand(move);
        MoveSouthEastCommand moveSouthEast = new MoveSouthEastCommand(move);
        MoveSouthCommand moveSouth = new MoveSouthCommand(move);
        MoveSouthWestCommand moveSouthWest = new MoveSouthWestCommand(move);
        MoveWestCommand moveWest = new MoveWestCommand(move);
        MoveNorthWestCommand moveNorthWest = new MoveNorthWestCommand(move);
        
        remoteControlButtons.setCommand(8, moveNorth);
        remoteControlButtons.buttonWasPressed(8);
        commandData.setCommand("move n");
        
        remoteControlButtons.setCommand(9, moveNorthEast);
        remoteControlButtons.buttonWasPressed(9);
        commandData.setCommand("move ne");
        
        remoteControlButtons.setCommand(6, moveEast);
        remoteControlButtons.buttonWasPressed(6);
        commandData.setCommand("move e");
        
        remoteControlButtons.setCommand(3, moveSouthEast);
        remoteControlButtons.buttonWasPressed(3);
        commandData.setCommand("move se");
        
        remoteControlButtons.setCommand(2, moveSouth);
        remoteControlButtons.buttonWasPressed(2);
        commandData.setCommand("move s");
        
        remoteControlButtons.setCommand(1, moveSouthWest);
        remoteControlButtons.buttonWasPressed(1);
        commandData.setCommand("move sw");
        
        remoteControlButtons.setCommand(4, moveWest);
        remoteControlButtons.buttonWasPressed(4);
        commandData.setCommand("move w");
        
        remoteControlButtons.setCommand(7, moveNorthWest);
        remoteControlButtons.buttonWasPressed(7);
        commandData.setCommand("move nw");
     
        
        // PN11: PROXY PATTERN
        System.out.println("\n== PN11: PROXY PATTERN ==\n");
        initializeUserDatabase();
        
        User username = getUserFromDatabase("President");
        User presidentProxy = getPresidentProxy(username);
        setCurrentUserPermissionLevel("president");
        
        System.out.println("Logged in as: " + presidentProxy.getName());
        System.out.println("Current permissions level: " + getCurrentUserPermissionLevel());
        try {
            presidentProxy.getPermissions();
        } catch (Exception e) {
            System.out.println("We're sorry, that feature must be broken!");
        }
        
        System.out.println();
        
        username = getUserFromDatabase("Admin");
        User adminProxy = getAdminProxy(username);
        setCurrentUserPermissionLevel("admin");
        
        System.out.println("Logged in as: " + adminProxy.getName());
        System.out.println("Current permissions level: " + getCurrentUserPermissionLevel());
        try {
            adminProxy.getPermissions();
        } catch (Exception e) {
            System.out.println("Debugging...");
        }

        
        // PN12: COMPOSITE PATTERN
        System.out.println("\n\n== PN12: COMPOSITE PATTERN ==");
        MenuComponent deskOrderMenu = new Menu("Commands Menu", "");
        deskOrderMenu.add(new MenuItem("1) Move SW", "move robot southwest"));
        deskOrderMenu.add(new MenuItem("2) Move S", "move robot south"));
        deskOrderMenu.add(new MenuItem("3) Move SE", "move robot southeast"));
        deskOrderMenu.add(new MenuItem("4) Move W", "move robot west"));
        deskOrderMenu.add(new MenuItem("5) Stop Move", "stop robot from moving"));
        deskOrderMenu.add(new MenuItem("6) Move E", "move robot east"));
        deskOrderMenu.add(new MenuItem("7) Move NW", "move robot northwest"));
        deskOrderMenu.add(new MenuItem("8) Move N", "move robot north"));
        deskOrderMenu.add(new MenuItem("9) Move NE", "move robot northeast"));
        deskOrderMenu.add(new MenuItem("10) Scan", "use scanner"));
        
        
        // PN5: ITERATOR PATTERN
        System.out.println("== PN5: ITERATOR PATTERN ==");
        MenuSystem menuSystem = new MenuSystem(deskOrderMenu);
        menuSystem.printMenu();
        
        
        // PN13: FACADE PATTERN
        System.out.println("\n\n== PN13: FACADE PATTERN ==");
        System.out.println("\n+ test robot selfie");
        RobotSelfieFacade robotSelfie = new RobotSelfieFacade(
                cameraComponent, flashlightComponent, projectorComponent);
        
        robotSelfie.takeSelfie();
        
        // RoboScript scripting ability for future software design sequences
        System.out.println("\n+ test RoboScript...");
        
        List<String> commandList = new ArrayList<String>();        
        commandList.add("moveNorth");
        commandList.add("moveSouth");
        commandList.add("moveEast");
        commandList.add("moveWest");
        commandList.add("selfie");
        
        RoboScript rs = new RoboScript(bot);
        
        for (String command : commandList) {
            rs.runCommand(command);
        }
        
    }

    public static void sendRemoteCommand(Robot bot, String command)
    {
        bot.getRemoteCommand(command);
    }

    public static void setCurrentUserPermissionLevel(String newCurrentUserPermissionLevel) {
        currentUserPermissionLevel = newCurrentUserPermissionLevel;
    }

}