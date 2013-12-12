package components;
import behaviors.RecalibrateLens;

public class ProjectorComponent extends ComponentFramework {
    
    RecalibrateLens recalibrateLens = new RecalibrateLens();
    
    public void action()
    {
        System.out.println(">> shining...");
    }
    
    public void off()
    {
        System.out.println(">> turning projector off");
    }
    
    public void on()
    {
        System.out.println(">> turning projector on");
    }
    
    public void projectFakeBackground(String location)
    {
        System.out.println(">> projecting fake " + location + " background");
    }
    
    public Boolean recalibrate()
    {
        System.out.println(">> recalibrating flashlight");
        recalibrateLens.recalibrate();
        return true;
    }
}
