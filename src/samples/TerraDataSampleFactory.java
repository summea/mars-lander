package samples;

public class TerraDataSampleFactory extends DataSampleFactory {

    protected Sample createSample(String item)
    {
        Sample sample = null;
        SampleElementFactory sampleElementFactory = new SoilSampleElementFactory();
        
        if (item.equals("quickscan")) {
            sample = new QuickScan(sampleElementFactory);
            sample.setName("Terra Sample Quick Scan");
        } else if (item.equals("fullscan")) {
            sample = new FullScan(sampleElementFactory);
            sample.setName("Terra Sample Full Scan");
        }
        
        return sample;                
    } 
    
}
