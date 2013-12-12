package samples;

public class AtmosDataSampleFactory extends DataSampleFactory {

    protected Sample createSample(String item)
    {        
        Sample sample = null;
        SampleElementFactory sampleElementFactory = new SoilSampleElementFactory();
        
        if (item.equals("quickscan")) {
            sample = new QuickScan(sampleElementFactory);
            sample.setName("Atmos Sample Quick Scan");
        } else if (item.equals("fullscan")) {
            sample = new FullScan(sampleElementFactory);
            sample.setName("Atmos Sample Full Scan");
        }
        
        return sample;                
    } 
    
}
