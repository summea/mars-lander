package states;
import robot.Robot;

public class ProcessCommandState implements State {

    Robot robot;
    
    public ProcessCommandState(Robot robot)
    {
        this.robot = robot;
    }
    
    public void available()
    {
        System.out.println("I'm NOT available right now...");
    }
    
    public void getCommand()
    {
        System.out.println("I'm NOT getting a command right now...");
    }

    public void processCommand()
    {
        System.out.println("I'm processing a command right now...");
    }

    public void runCommand()
    {
        System.out.println("I'm NOT running a command right now...");
    }

}