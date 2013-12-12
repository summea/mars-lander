package components;
import taiwan.TaiwaneseComponent;

public class TaiwaneseComponentAdapter implements Component {
    
    TaiwaneseComponent tc;
    
    public TaiwaneseComponentAdapter(TaiwaneseComponent tc)
    {
        this.tc = tc;
    }
    
    public void install()
    {
        tc.installTW();
    }
    
    public void uninstall()
    {
        tc.uninstallTW();
    }
}
