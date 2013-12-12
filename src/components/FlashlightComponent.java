package components;
import behaviors.RecalibrateLens;

public class FlashlightComponent extends ComponentFramework {
    
    RecalibrateLens recalibrateLens = new RecalibrateLens();
    
    public void action()
    {
        System.out.println(">> shining...");
    }
    
    public void off()
    {
        System.out.println(">> turning flashlight off");
    }
    
    public void on()
    {
        System.out.println(">> turning flashlight on");
    }
    
    public Boolean recalibrate()
    {
        System.out.println(">> recalibrating flashlight");
        recalibrateLens.recalibrate();
        return true;
    }
    
    public void rotate(int degrees)
    {
        System.out.println(">> rotating flashlight " + String.valueOf(degrees) + " degrees");
    }
}
