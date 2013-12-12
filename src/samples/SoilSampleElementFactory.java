package samples;

public class SoilSampleElementFactory implements SampleElementFactory {

    public TestSolution prepareTestSolution()
    {
        return new MacroTestSolution();
    }
    
    public TestStrip prepareTestStrip()
    {
        return new LittleTestStrip();
    }
    
}
