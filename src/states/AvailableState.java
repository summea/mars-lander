package states;
import robot.Robot;

public class AvailableState implements State {

    Robot robot;
    
    public AvailableState(Robot robot)
    {
        this.robot = robot;
    }
    
    public void available()
    {
        System.out.println("I'm available right now...");
    }
    
    public void getCommand()
    {
        System.out.println("I'm NOT getting a command right now...");
    }

    public void processCommand()
    {
        System.out.println("I'm NOT processing a command right now...");
    }

    public void runCommand()
    {
        System.out.println("I'm NOT running a command right now...");
    }

}
