package states;
import robot.Robot;

public class GetCommandState implements State {

    Robot robot;
    
    public GetCommandState(Robot robot)
    {
        this.robot = robot;
    }
    
    public void available()
    {
        System.out.println("I'm NOT available right now...");
    }
    
    public void getCommand()
    {
        System.out.println("getting command...");
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