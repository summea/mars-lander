package components;
import behaviors.RecalibrateLens;

public abstract class CameraComponent extends ComponentFramework {
    
    RecalibrateLens recalibrateLens = new RecalibrateLens();
    String description = "generic camera";
    
    public void action()
    {
        System.out.println(">> snapping...");
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public void off()
    {
        System.out.println(">> turning camera off");
    }
    
    public void on()
    {
        System.out.println(">> turning camera on");
    }
    
    public Boolean recalibrate()
    {
        System.out.println(">> recalibrating camera");
        recalibrateLens.recalibrate();
        return true;
    }

    public void rotate(int degrees)
    {
        System.out.println(">> rotating camera " + String.valueOf(degrees) + " degrees");
    }
    
    public void sendPhoto()
    {
        System.out.println(">> sending photo...");
    }
}
