package components;
import behaviors.RecalibrateLens;

public class ScannerComponent extends ComponentFramework {
    
    RecalibrateLens recalibrateLens = new RecalibrateLens();
    
    public void action()
    {
        System.out.println(">> scanning...");
    }
    
    public Boolean recalibrate()
    {
        System.out.println(">> recalibrating scanner");
        recalibrateLens.recalibrate();
        return true;
    }
}
