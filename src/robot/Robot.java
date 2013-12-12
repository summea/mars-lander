package robot;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import states.AvailableState;
import states.GetCommandState;
import states.ProcessCommandState;
import states.RunCommandState;
import states.State;

import components.CameraComponent;
import components.Cannon;
import components.ComponentFramework;
import components.ScannerComponent;

import database.Database;
import database.DatabaseConnection;

public class Robot {
    
    private String name;
    private TreeMap<String, String> components = new TreeMap<String, String>();
    private ArrayList<String> extensions = new ArrayList<String>();
    private String currentlyEquippedComponent;
    private String currentController;
    
    Database travelledAreasDB = new Database();
    Database scannedObjectsDB = new Database();
    
    State availableState = new AvailableState(this);
    State getCommandState = new GetCommandState(this);
    State processCommandState = new ProcessCommandState(this);
    State runCommandState = new RunCommandState(this);
    
    State state = availableState;
    
    int x = 0;
    int y = 0;
    
    final int EMPTY = 0;
    final int ROCK = 1;
    final int SCULPTURE = 2;
    
    public Robot()
    {
    }
    
    public Robot(String name, ArrayList<String> components, int x, int y)
    {
        this.name = name;
        for (String str : components) {
            this.components.put(str, "OK");
        }
        
        availableState = new AvailableState(this);
        getCommandState = new GetCommandState(this);
        processCommandState = new ProcessCommandState(this);
        runCommandState = new RunCommandState(this);
        
        this.x = x;
        this.y = y;
    }
    
    public void addComponent(String component, String status)
    {
        this.components.put(component, status);
        System.out.println(":: adding " + component + "...");
    }
    
    public void addExtension(String extension)
    {
        this.extensions.add(extension);
        System.out.println(":: adding " + extension + "...");
    }
    
    public void available()
    {
        this.state.available();
    }
    
    public boolean equipComponent(String component)
    {
        System.out.println("checking if component exists...");
        boolean found = false;
        for (Map.Entry<String, String> entry : this.components.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();        
            if (key.equals(component)) {
                if (value.equals("OK")) {
                    System.out.println(component + " exists!");
                    System.out.println("equipping " + component + " for use...");
                    this.currentlyEquippedComponent = component;
                } else {
                    System.out.println(component + " exists!");
                    System.out.println("equipping " + component + " for use...");
                    System.out.println(">>ERROR<< Sorry... this component is having issues at the moment. " + component + " can not be equipped at this time.");
                }
                found = true;
            }
        }        
        if (!found)
            System.out.println(">>ERROR<< " + component + " component was not included with this robot.");
        
        return found;
    }
        
    public void getCommand()
    {
        state.getCommand();
    }
    
    public String getComponentStatus(String component)
    {
        return this.components.get(component);
    }
    
    public String getCurrentController()
    {
        return this.currentController;
    }
    
    public void getCurrentCoordinates()
    {
        System.out.println("x:" + this.x + " y:" + this.y);
    }

    public String getCurrentlyEquippedComponent()
    {
        return this.currentlyEquippedComponent;
    }
    
    public String getLocationData()
    {
        String output = "";
        TreeMap<Integer, List<String>> results = new TreeMap<Integer, List<String>>();
        results = this.travelledAreasDB.selectAll();
        
        for (Map.Entry<Integer, List<String>> entry : results.entrySet()) {
            List<String> value = entry.getValue();
            
            Iterator<String> itr = value.iterator();
            
            while (itr.hasNext()) {
                output += itr.next() + ",";
            }
            
            output = output.substring(0, output.length()-1);    // take off last comma
            output += "\n";
        }
        
        return output;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public void getRemoteCommand(String command)
    {
        System.out.println("getting remote command...");
        System.out.println("receving data... ");
        System.out.println("> " + command);
    }
    
    public State getState() {
        return state;
    }
    
    public int getX()
    {
        return this.x;
    }
    
    public int getY()
    {
        return this.y;
    }
    
    public boolean hasVisitedLocation(int x, int y)
    {
        boolean result = false;
        return result;
    }
    
    public boolean initDB()
    {
        boolean result = false; 
        DatabaseConnection dbConn = DatabaseConnection.getInstance();        
        dbConn.init("localhost");
        return result;
    }
    
    public void initRobotFramework()
    {
        ComponentFramework cfw;
        
        for (Map.Entry<String, String> entry : this.components.entrySet()) {
            String key = entry.getKey();            
            String value = entry.getValue();
            
            System.out.println("\n:: " + key + " ... status: " + value);
            System.out.println();
            
            // could use reflection/invocation here... but for safety's sake...
            if (key.equals("scanner")) {
                cfw = new ScannerComponent();
                cfw.init();
                if (!this.getComponentStatus(key).equals("OK")) {
                    if (cfw.recalibrate())
                        this.setComponentStatus("scanner", "OK");
                }
            } else if (key.equals("mapper")) {
                cfw = new ScannerComponent();
                cfw.init();
                if (!this.getComponentStatus(key).equals("OK")) {
                    if (cfw.recalibrate())
                        this.setComponentStatus("mapper", "OK");
                }
            } else if (key.equals("camera")) {
                cfw = new ScannerComponent();
                cfw.init();
                if (!this.getComponentStatus(key).equals("OK")) {
                    if (cfw.recalibrate())
                        this.setComponentStatus("camera", "OK");
                }
            }            
        }
        
        System.out.println("\nComponent Status:");
        for (Map.Entry<String, String> entry : this.components.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(":: " + key + " ... status: " + value);
        }        
    }
    
    public boolean insertIntoDB(Database db, String... items)
    {
        // insert into db
        List<String> newList = new ArrayList<String>();
        
        for (String item : items) {
            newList.add(item);
        }
        db.insert(newList);
        
        return true;
    }
    
    public boolean isInDatabase(Integer key)
    {
        boolean result = false;
        
        if (null != scannedObjectsDB.select(key))
            result = true;
        
        return result;
    }
    
    public void listComponents()
    {
        for (Map.Entry<String, String> entry : this.components.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(":: " + key + " ... status: " + value);
        }
    }

    public void listExtensions()
    {
        for (int i = 0; i < this.extensions.size(); i++)
            System.out.println(":: " + extensions.get(i));
    }

    public String look(int planetSquareVal)
    {
        String output;
        String planetSquareValItem = "";
        
        if (planetSquareVal > 0) {
            switch (planetSquareVal) {
                case 1:
                    output = "analyzing object...";
                    output += "found a rock";
                    planetSquareValItem = "rock";
                    break;
                case 2:
                    output = "analyzing object...";
                    output += "found a sculpture";
                    planetSquareValItem = "sculpture";
                    break;
                default:
                    output = "";
            }
            
            // check if found item is already in database
            if (isInDatabase(planetSquareVal)) {
                output += " ---> is already in database";
            } else {
                // take photo
                if (equipComponent("camera")) {
                    CameraComponent camera = new Cannon();
                    camera.on();
                    camera.rotate(30);
                    camera.action();    // camera takes photo and auto-saves
                    camera.off();
                }
                
                if (insertIntoDB(scannedObjectsDB, planetSquareValItem))
                    output += "\ninserting " + planetSquareValItem + " data into database";
            }            
        } else {
            output = "there is nothing here";
        }

        return output;
    }
    
    // move direction matches number pad on a keyboard
    public String move(Integer direction)
    {
        String output;
        
        switch (direction) {
            case 1:
                output = "moving SW";
                this.x = this.x - 1;
                this.y = this.y - 1;
                break;
            case 2:
                output = "moving S";
                this.y = this.y + 1;
                break;
            case 3:
                output = "moving SE";
                this.x = this.x + 1;
                this.y = this.y - 1;
                break;
            case 4:
                output = "moving W";
                this.x = this.x - 1;
                break;
            case 5:
                output = "standing still";
                break;
            case 6:
                output = "moving E";
                this.x = this.x + 1;
                break;
            case 7:
                output = "moving NW";
                this.x = this.x - 1;
                this.y = this.y + 1;
                break;
            case 8:
                output = "moving N";
                this.y = this.y - 1;
                break;
            case 9:
                output = "moving NE";
                this.x = this.x + 1;
                this.y = this.y + 1;
                break;
            default:
                output = "can't move that way";
        }
        
        String currentX = String.valueOf(this.getX());
        String currentY = String.valueOf(this.getY());
        if (insertIntoDB(travelledAreasDB, currentX, currentY))
            output += "\ninserting current location: " + currentX + "," + currentY + " data into database";
        return output;
    }
    
    public void photoProcess()
    {
        System.out.println(this.takePhoto());
        System.out.println(this.sendBackPhoto());
    }
    
    public void processCommand()
    {
        state.processCommand();
    }
    
    public void removeComponent(String component)
    {
        for (int i = 0; i < this.components.size(); i++) {
            if (this.components.get(i) == component) {
                components.remove(i);
                System.out.println(":: removing " + component + "...");
            }
        }
    }
    
    public void removeExtension(String extension)
    {
        for (int i = 0; i < this.extensions.size(); i++) {
            if (this.extensions.get(i) == extension) {
                extensions.remove(i);
                System.out.println(":: removing " + extension + "...");
            }
        }
    }
    
    public void runCommand()
    {
        state.runCommand();
    }
    
    public String sendBackPhoto()
    {
        return "sending back photo...";
    }
    
    public void setComponentStatus(String component, String status)
    {
        this.components.put(component, status);
    }
    
    public void setCurrentController(String controllerName)
    {
        this.currentController = controllerName;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setState(State state) {
        this.state = state;
    }
    
    public String takePhoto()
    {
        return "taking photo...";
    }
    
}