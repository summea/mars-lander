package samples;

public class SoilTestSolutionSample extends Sample {

    SampleElementFactory sampleElementFactory;
    
    public SoilTestSolutionSample(SampleElementFactory sampleElementFactory)
    {
        this.sampleElementFactory = sampleElementFactory;
    }
    
    void checkSupplies()
    {
        System.out.println("checking supplies for " + name);
        
    }

}
