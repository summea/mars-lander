package extensions;
import russia.RussianSoftwareExtension;

public class RussianSoftwareExtensionAdapter implements SoftwareExtension {
    
    RussianSoftwareExtension swex;
    
    public RussianSoftwareExtensionAdapter(RussianSoftwareExtension swex)
    {
        this.swex = swex;
    }
    
    public void install()
    {
        swex.installRUS();
    }
    
    public void uninstall()
    {
        swex.uninstallRUS();
    }
}
