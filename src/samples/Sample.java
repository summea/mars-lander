package samples;

public abstract class Sample {
    
    String name;

    TestStrip testStrip;
    TestSolution testSolution;

    abstract void checkSupplies();

    String getName() {
        return name;
    }
    
    public void prepareTestSolution()
    {
        System.out.println("preparing test solution");
    }
    
    public void prepareTestStrip()
    {
        System.out.println("preparing test strip");
    }

    void setName(String name) {
        this.name = name;
    }

    public String toString()
    {
        StringBuffer result = new StringBuffer();
        result.append("||| " + name + " \n");
        
        if (testStrip != null) {
            result.append(testStrip);
            result.append("\n");
        }
        
        if (testSolution != null) {
            result.append(testSolution);
            result.append("\n");
        }

        return result.toString();
    }
}
