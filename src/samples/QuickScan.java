package samples;

public class QuickScan extends Sample {

    SampleElementFactory sampleElementFactory;
    
    public QuickScan(SampleElementFactory sampleElementFactory)
    {
        this.sampleElementFactory = sampleElementFactory;
    }
    
    void checkSupplies()
    {
        System.out.println("checking supplies for " + name);
        
    }

}
