package samples;

public class SoilTestStripSample extends Sample {

    SampleElementFactory sampleElementFactory;
    
    public SoilTestStripSample(SampleElementFactory sampleElementFactory)
    {
        this.sampleElementFactory = sampleElementFactory;
    }
    
    void checkSupplies()
    {
        System.out.println("checking supplies for " + name);
    }

}
