package samples;

public class FullScan extends Sample {

    SampleElementFactory sampleElementFactory;
    
    public FullScan(SampleElementFactory sampleElementFactory)
    {
        this.sampleElementFactory = sampleElementFactory;
    }
    
    void checkSupplies()
    {
        System.out.println("checking supplies for " + name);
    }

}
