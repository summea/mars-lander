import robot.Robot;

import components.CameraComponent;
import components.Cannon;
import components.FlashlightComponent;
import components.ProjectorComponent;

import facades.RobotSelfieFacade;

public class RoboScript {
    
    Robot bot = new Robot();
    CameraComponent cameraComponent = new Cannon();
    FlashlightComponent flashlightComponent = new FlashlightComponent();
    ProjectorComponent projectorComponent = new ProjectorComponent();
    
    public RoboScript(Robot robot)
    {
        this.bot = robot;
    }
    
    public boolean runCommand(String command)
    {
        if (command.equals("moveNorth")) {
            System.out.println("\nmoveNorth command running...");
            System.out.println(bot.move(8));
        } else if (command.equals("moveSouth")) {
            System.out.println("\nmoveSouth command running...");
            System.out.println(bot.move(2));
        } else if (command.equals("moveEast")) {
            System.out.println("\nmoveEast command running...");
            System.out.println(bot.move(6));
        } else if (command.equals("moveWest")) {
            System.out.println("\nmoveWest command running...");
            System.out.println(bot.move(4));
        } else if (command.equals("selfie")) {
            System.out.println("\nselfie command running...");
            RobotSelfieFacade robotSelfie = new RobotSelfieFacade(
                    cameraComponent, flashlightComponent, projectorComponent);
            
            robotSelfie.takeSelfie();
        }
        
        return true;
    }
    
}
