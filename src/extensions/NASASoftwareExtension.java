package extensions;

public class NASASoftwareExtension implements SoftwareExtension {
    
    public void install()
    {
        System.out.println("installing NASA software extension...");
    }
    
    public void uninstall()
    {
        System.out.println("uninstalling NASA software extension...");
    }
    
}
