package components;
import behaviors.RecalibrateRadar;

public class MapperComponent extends ComponentFramework {
    
    RecalibrateRadar recalibrateRadar = new RecalibrateRadar();
    
    public void action()
    {
        System.out.println(">> mapping...");
    }
    
    public Boolean recalibrate()
    {
        System.out.println(">> recalibrating mapper");
        recalibrateRadar.recalibrate();
        return true;
    }
}
