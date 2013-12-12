package samples;

public abstract class DataSampleFactory {
    
    protected abstract Sample createSample(String item);
 
    public Sample collectSample(String type) {
        Sample sample = createSample(type);
        System.out.println("Preparing " + sample.getName());
        sample.checkSupplies();
        sample.prepareTestStrip();
        sample.prepareTestSolution();
        return sample;
    }
}
